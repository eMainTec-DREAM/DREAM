package dream.consult.consult.menu.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.consult.menu.dao.McMenuDetailDAO;
import dream.consult.consult.menu.dto.McMenuDetailDTO;


/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="mcMenuDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class McMenuDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements McMenuDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public McMenuDetailDTO findDetail(String menuId, String lang)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("        x.ehmenu_id        menuId                 ");
        query.append("       ,x.ehmenu_no        menuNo                 ");
        query.append("       ,(SELECT key_name                           ");
        query.append("        FROM TALANG                               ");
        query.append("        WHERE key_type=x.key_type                 ");
        query.append("           AND key_no = x.key_no                  ");
        query.append("           AND lang = '"+lang+"') menuDesc       ");
        query.append("       ,CASE when x.p_ehmenu_id = 0 THEN null ELSE x.p_ehmenu_id END AS pMenuId          ");
        query.append("       ,(SELECT (SELECT key_name                   ");
        query.append("        FROM TALANG                               ");
        query.append("        WHERE key_type=a.key_type                 ");
        query.append("           AND key_no = a.key_no                  ");
        query.append("           AND lang = '"+lang+"')                 ");
        query.append("          FROM TAEHMENU a                         ");
        query.append("         WHERE a.ehmenu_id = x.p_ehmenu_id        ");
        query.append("           ) pMenuDesc                           ");
        query.append("       ,x.page_id            pageId               ");
        query.append("       ,x.key_no             keyNo                ");
        query.append("       ,x.key_type           keyType              ");
        query.append("       ,(SELECT description                        ");
        query.append("          FROM TAPAGE                             ");
        query.append("         WHERE page_id = x.page_id) pageDesc     ");
        query.append("       ,x.ord_no            ordNo                 ");
        query.append("       ,x.is_use            isUse                 ");
        query.append("       ,x.param            paramValue             ");
        query.append("       ,x.remark                                   ");
        query.append("FROM   TAEHMENU x                                 ");
        query.append("WHERE  x.ehmenu_id = ?                             ");
        
        Object[] objects = new Object[] {
        		menuId
   	    };

    
        McMenuDetailDTO DetailDTO = 
        		(McMenuDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new McMenuDetailDTO()));
        
        return DetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(McMenuDetailDTO DetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	String lang = loginUser.getLangId();
    	int rtnValue  = 0;
    	String pMenuId = DetailDTO.getPmenuId();
    	if("".equals(pMenuId)){
    		pMenuId = "0";
    	}

    	query.append("INSERT INTO TAEHMENU(		");
    	query.append("	 ehmenu_id				");
    	query.append("	 ,key_no				");
    	query.append("	 ,p_ehmenu_id			");
    	query.append("	 ,page_id			    ");
    	query.append("	 ,key_type			    ");
    	query.append("	 ,ord_no				");
    	query.append("	 ,param				    ");
    	query.append("	 ,ehmenu_no           	");
    	query.append("	 ,description           ");
    	query.append("	 ,is_use           		");
    	query.append("	 ,remark           		");
    	query.append(")	VALUES (		        ");
    	query.append("	 ?				    ");
    	query.append("	 ,?				    ");
    	query.append("	 ,?			        ");
    	query.append("	 ,?			        ");
    	query.append("	 ,?			        ");
    	query.append("	 ,?				    ");
    	query.append("	 ,?				    ");
    	query.append("	 ,?           	    ");
    	query.append("	 ,(SELECT key_name FROM TALANG WHERE key_no = ? AND key_type=? AND lang='"+lang+"') ");
    	query.append("	 ,?           		");
    	query.append("	 ,?           		");
    	query.append("	 )           		");
    	
    	Object[] objects = new Object[] {
    			DetailDTO.getMenuId()
    			,DetailDTO.getKeyNo()
    			,pMenuId
    			,DetailDTO.getPageId()
    			,DetailDTO.getKeyType()
    			,DetailDTO.getOrdNo()
    			,DetailDTO.getParamValue()
    			,DetailDTO.getMenuNo()
    			,DetailDTO.getKeyNo()
    			,DetailDTO.getKeyType()
    			,DetailDTO.getIsUse()
    			,DetailDTO.getRemark()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	return rtnValue;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: McMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(McMenuDetailDTO DetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String pMenuId = DetailDTO.getPmenuId();
    	String lang = loginUser.getLangId();
    	int rtnValue  = 0;
    	if("".equals(pMenuId)){
    		pMenuId = "0";
    	}

    	query.append("UPDATE TAEHMENU SET   ");
    	query.append("	p_ehmenu_id	= ?	");
    	query.append("	,ord_no		= ?	");
    	query.append("	,page_id		= ?	");
    	query.append("	,key_no		= ?	");
    	query.append("	,key_type	= ?	");
    	query.append("	,is_use		= ?	");
    	query.append("	,param		= ?	");
    	query.append("	,ehmenu_no	= ?    ");
    	query.append("	,remark	    = ?    ");
    	query.append("	,description =  (SELECT key_name FROM TALANG WHERE key_no = ? AND key_type=? AND lang='"+lang+"') ");
    	query.append("WHERE ehmenu_id = ?		");
    	
    	Object[] objects = new Object[] {
    			pMenuId,
    			DetailDTO.getOrdNo()
    			,DetailDTO.getPageId()
    			,DetailDTO.getKeyNo()
    			,DetailDTO.getKeyType()
    			,DetailDTO.getIsUse()
    			,DetailDTO.getParamValue()
    			,DetailDTO.getMenuNo()
    			,DetailDTO.getRemark()
    			,DetailDTO.getKeyNo()
    			,DetailDTO.getKeyType()
    			,DetailDTO.getMenuId()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}