package dream.consult.consult.menu.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.consult.menu.dao.McMenuListDAO;
import dream.consult.consult.menu.dto.McMenuCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McMenuListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="mcMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McMenuListDAOOraImpl extends BaseJdbcDaoSupportOra implements McMenuListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McMenuListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuCommonDTO
     * @return List
     */
    public List findMenuList(McMenuCommonDTO mcMenuCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();

        query.append("SELECT                                                    ");
        query.append("       '' seqNo,                                          ");
        query.append("         '' isDelCheck,                                   ");
        query.append("       x.ehmenu_no menuNo,                                ");
        query.append("       (SELECT (SELECT a.key_name                         ");
        query.append("          FROM TALANG a                                   ");
        query.append("         WHERE a.key_type=y.key_type                      ");
        query.append("           AND a.key_no = y.key_no                        ");
        query.append("           AND a.lang = '"+lang+"')                       ");
        query.append("          FROM TAEHMENU y                                 ");
        query.append("         WHERE y.ehmenu_id = x.p_ehmenu_id                ");
        query.append("          ) pMenuDesc,                                    ");
        query.append("       x.ehmenu_id menuId,                                ");
        query.append("       (SELECT a.key_name                                 ");
        query.append("          FROM TALANG a                                   ");
        query.append("         WHERE a.key_type=x.key_type                      ");
        query.append("           AND a.key_no = x.key_no                        ");
        query.append("           AND a.lang = '"+lang+"') menuDesc,             ");
        query.append("       (SELECT file_name                                  ");
        query.append("          FROM TAPAGE                                     ");
        query.append("         WHERE page_id = x.page_id) fileName,             ");
        query.append("       (SELECT description                                ");
        query.append("          FROM TAPAGE                                     ");
        query.append("         WHERE page_id = x.page_id) pageDesc,             ");
        query.append("       x.ord_no ordNo,                                    ");
        query.append("       x.is_use isUse                                     ");
        query.append("FROM   TAEHMENU x                                         ");
        query.append("WHERE  1=1                                                ");

        query.append(this.getWhere(mcMenuCommonDTO));
        
        query.append("ORDER by x.ord_no                  					");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: McMenuListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcMenuCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(McMenuCommonDTO mcMenuCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getLikeQuery("x.ehmenu_no", mcMenuCommonDTO.getFilterMenuNo());
        
        if (!"".equals(mcMenuCommonDTO.getMenuId()))
        {
            query.getAndQuery("x.ehmenu_id", mcMenuCommonDTO.getMenuId());
            return query.toString();
        }
        if(!"".equals(mcMenuCommonDTO.getFilterMenuDesc())&&mcMenuCommonDTO.getFilterMenuDesc()!=null){
        	query.append("AND key_no IN	( SELECT key_no						");
        	query.append("					FROM TALANG						");
        	query.append("					WHERE key_type='MENU'			");
        	query.getLikeQuery("key_name", mcMenuCommonDTO.getFilterMenuDesc());
        	query.append("				)									");
        }
        
        query.getStringEqualQuery("x.p_ehmenu_id", mcMenuCommonDTO.getFilterPMenuId());
        if("".equals(mcMenuCommonDTO.getFilterPMenuId())&&mcMenuCommonDTO.getFilterPMenuDesc() != "")
        {
        	query.append("AND x.p_ehmenu_id IN (SELECT a.ehmenu_id 	    	        ");
        	query.append("                    FROM   TAEHMENU a      				");
        	query.append("                    WHERE  a.key_no      					");
        	query.append("                       IN ( SELECT key_no                 ");
        	query.append("                            FROM   TALANG                 ");
        	query.append("                            WHERE  key_type='MENU'        ");
        	query.getLikeQuery("key_name", mcMenuCommonDTO.getFilterPMenuDesc());
        	query.append("				             )								");
        	query.append("				      )										");
        }
        //상위위치
//        query.getCodeLikeQuery("x.p_menu_id", "(SELECT a.description FROM  TAMENU a WHERE a.menu_id = x.p_menu_id)", 
//        		mcMenuCommonDTO.getFilterPMenuId(), mcMenuCommonDTO.getFilterPMenuDesc());
        
        if(!"".equals(mcMenuCommonDTO.getFilterPageDesc())){
        	query.append("  AND  page_id IN (SELECT page_id						");
            query.append("  				   FROM TAPAGE						");
            query.append("  				  WHERE description like			");
            query.append("  					'%"+mcMenuCommonDTO.getFilterPageDesc()+"%')	");
        }
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: McMenuListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMenu(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String menuId = id;
    	
    	query.append("DELETE FROM TAEHMENU				    ");
    	query.append("WHERE ehmenu_id = '"+menuId+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
}