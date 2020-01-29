package dream.consult.program.menu.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.menu.dao.MaMenuMngListDAO;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;

/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaMenuMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMenuMngListDAOTarget"
 * @spring.txbn id="maMenuMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMenuMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMenuMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMenuMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngCommonDTO
     * @return List
     */
    public List findMenuList(MaMenuMngCommonDTO maMenuMngCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                        		");
        query.append("       '' SEQNO,                                      ");
        query.append("       '' ISDELCHECK,                                 ");
        query.append("       menu_id AS ID,                                 ");
        query.append("       x.menu_no menuNo,                              ");
        query.append("       x.service_type serviceTypeId,                              ");
        query.append("		SFACODE_TO_DESC(x.service_type,'SERVICE_TYPE','SYS','','"+lang+"') serviceTypeDesc,	");
        query.append("       (SELECT (SELECT a.key_name                    	");
        query.append("          FROM TALANG a                           	");
        query.append("         WHERE a.key_type=y.key_type                 	");
        query.append("           AND a.key_no = y.key_no                  	");
        query.append("           AND a.lang = '"+lang+"')                  	");
        query.append("          FROM TAMENU y	                        	");
        query.append("         WHERE y.menu_id = x.p_menu_id	        	");
        query.append("          ) pMenuDesc,					    		");
        query.append("       x.menu_id menuId,                             	");
        query.append("       x.p_menu_id pMenuId,                          	");
        query.append("       (SELECT a.key_name                          	");
        query.append("          FROM TALANG a                           	");
        query.append("         WHERE a.key_type=x.key_type                 	");
        query.append("           AND a.key_no = x.key_no                  	");
        query.append("           AND a.lang = '"+lang+"') menuDesc,        	");
        query.append("       (SELECT file_name                    			");
        query.append("          FROM TAPAGE	                        		");
        query.append("         WHERE page_id = x.page_id) fileName,			");
        query.append("       (SELECT description                    		");
        query.append("          FROM TAPAGE	                        		");
        query.append("         WHERE page_id = x.page_id) pageDesc,			");
        query.append("       x.ord_no ordNo,                                ");
        query.append("       x.is_use isUse                                 ");
        query.append("FROM   TAMENU x                               		");
        query.append("WHERE  1=1                                    		");
        query.append(this.getWhere(maMenuMngCommonDTO,loginUser));
        query.append("ORDER by x.ord_no                                     ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaMenuMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaMenuMngCommonDTO maMenuMngCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getLikeQuery("x.menu_no", maMenuMngCommonDTO.getFilterMenuNo());
        //query.getAndQuery("x.service_type", "WEB");
        
        if (!"".equals(maMenuMngCommonDTO.getMenuId()))
        {
            query.getAndQuery("x.menu_id", maMenuMngCommonDTO.getMenuId());
            return query.toString();
        }
        query.getSysCdQuery("x.service_type", maMenuMngCommonDTO.getFilterServiceTypeId(), maMenuMngCommonDTO.getFilterServiceTypeDesc(), "SERVICE_TYPE", "",loginUser.getLangId());
        
        if(!"".equals(maMenuMngCommonDTO.getFilterMenuDesc())&&maMenuMngCommonDTO.getFilterMenuDesc()!=null){
        	query.append("AND x.key_no IN	( SELECT key_no						");
        	query.append("					FROM TALANG						");
        	query.append("					WHERE key_type='MENU'			");
        	query.getLikeQuery("key_name", maMenuMngCommonDTO.getFilterMenuDesc());
        	query.append("				)									");
        }
        
        query.getStringEqualQuery("x.p_menu_id", maMenuMngCommonDTO.getFilterPMenuId());
        if("".equals(maMenuMngCommonDTO.getFilterPMenuId())&&maMenuMngCommonDTO.getFilterPMenuDesc() != "")
        {
        	query.append("AND x.p_menu_id IN (SELECT a.menu_id 	    				");
        	query.append("                    FROM   TAMENU a      					");
        	query.append("                    WHERE  a.key_no      					");
        	query.append("                       IN ( SELECT key_no                 ");
        	query.append("                            FROM   TALANG                 ");
        	query.append("                            WHERE  key_type='MENU'        ");
        	query.getLikeQuery("key_name", maMenuMngCommonDTO.getFilterPMenuDesc());
        	query.append("				             )								");
        	query.append("				      )										");
        }
        //상위위치
//        query.getCodeLikeQuery("x.p_menu_id", "(SELECT a.description FROM  TAMENU a WHERE a.menu_id = x.p_menu_id)", 
//        		maMenuMngCommonDTO.getFilterPMenuId(), maMenuMngCommonDTO.getFilterPMenuDesc());
        
        if(!"".equals(maMenuMngCommonDTO.getFilterPageDesc())){
        	query.append("  AND  x.page_id IN (SELECT page_id						");
            query.append("  				   FROM TAPAGE						");
            query.append("  				  WHERE description like			");
            query.append("  					'%"+maMenuMngCommonDTO.getFilterPageDesc()+"%')	");
        }
        
        // 화면 ID
        if(!"".equals(maMenuMngCommonDTO.getFilterFileName())){
        	query.append("  AND  x.page_id IN (SELECT a.page_id		");
        	query.append("  				     FROM TAPAGE	a	");
        	query.append("  				    WHERE 1=1			");
        	query.getLikeQuery("a.file_name", maMenuMngCommonDTO.getFilterFileName());
        	query.append("					   ) 					");
        }
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMenuMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMenu(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String menuId = id;
    	
    	query.append("DELETE FROM TAMENU				");
    	query.append("WHERE menu_id = '"+menuId+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
}