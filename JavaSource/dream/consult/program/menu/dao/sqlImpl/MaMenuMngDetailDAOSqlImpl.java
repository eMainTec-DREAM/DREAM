package dream.consult.program.menu.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.menu.dao.MaMenuMngDetailDAO;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;

/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maMenuMngDetailDAOTarget"
 * @spring.txbn id="maMenuMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMenuMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaMenuMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaMenuMngDetailDTO findDetail(String menuId, String lang)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT								        ");
        query.append("       x.menu_id		menuId	                ");
        query.append("       ,x.menu_no		menuNo	                ");
        query.append("       ,(SELECT key_name						");
        query.append("        FROM TALANG							");
        query.append("        WHERE key_type=x.key_type				");
        query.append("       	AND key_no = x.key_no				");
        query.append("       	AND lang = '"+lang+"') menuDesc	");
        query.append("       ,case when x.p_menu_id = '0' then null else x.p_menu_id end as pMenuId		");
        query.append("       ,(SELECT (SELECT key_name				");
        query.append("        FROM TALANG							");
        query.append("        WHERE key_type=a.key_type				");
        query.append("       	AND key_no = a.key_no				");
        query.append("       	AND lang = '"+lang+"')              ");
        query.append("          FROM TAMENU a	                    ");
        query.append("         WHERE a.menu_id = x.p_menu_id	    ");
        query.append("           ) pMenuDesc					    ");
        query.append("       ,x.page_id			pageId		        ");
        query.append("       ,x.key_no			keyNo		        ");
        query.append("       ,x.key_type			keyType	        ");
        query.append("       ,(SELECT description                    ");
        query.append("          FROM TAPAGE	                        ");
        query.append("         WHERE page_id = x.page_id) pageDesc	");
        query.append("       ,x.ord_no			ordNo	            ");
        query.append("       ,x.is_system		isSystem           ");
        query.append("       ,x.remark		    remark             ");
        query.append("       ,x.is_use    		isUse              ");
        query.append("       ,x.service_type     serviceType        ");
        query.append("       ,dbo.SFACODE_TO_DESC(x.service_type, 'SERVICE_TYPE', 'SYS', '','"+lang+"') serviceTypeDesc");
        query.append("       ,x.param			paramValue          ");
        query.append("       ,x.is_external_link isExternalLink     ");
        query.append("       ,x.external_link externalLink    		");
        query.append("       ,x.is_get_link isGetLink	    		");
        query.append("FROM   TAMENU x						        ");
        query.append("WHERE  x.menu_id = ?           	            ");
        
        Object[] objects = new Object[] {
        		menuId
   	    };
    
        MaMenuMngDetailDTO maMenuMngDetailDTO = 
        		(MaMenuMngDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaMenuMngDetailDTO()));
        
        return maMenuMngDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     */
    public int insertDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String lang = loginUser.getLangId();
    	int rtnValue  = 0;
    	String pMenuId = maMenuMngDetailDTO.getPmenuId();
    	if("".equals(pMenuId)){
    		pMenuId = "0";
    	}

    	query.append("INSERT INTO TAMENU					");
    	query.append("	(menu_id,		key_no,				");
    	query.append("	 p_menu_id,		page_id,			");
    	query.append("	 is_system,		key_type,			");
    	query.append("	 ord_no,        param,				");
    	query.append("	 menu_no,       is_external_link,	");
    	query.append("	 description,   is_use,				");
    	query.append("	 service_type,  remark,	        	");
    	query.append("	 external_link, is_get_link			");
    	
    	query.append("	 )	VALUES		");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?,  				");
    	query.append("	 ?,             ?,    				");
    	query.append("	 (SELECT key_name FROM TALANG WHERE key_no = ? AND key_type=? AND lang='"+lang+"'), ");
    	query.append("	 ?,                    				");
    	query.append("	 ?,             ?,    				");
    	query.append("	 ?,				?  )     			");
    	
    	Object[] objects = new Object[] {
    			maMenuMngDetailDTO.getMenuId()
    			,maMenuMngDetailDTO.getKeyNo()
    			,pMenuId
    			,maMenuMngDetailDTO.getPageId()
    			,maMenuMngDetailDTO.getIsSystem()
    			,maMenuMngDetailDTO.getKeyType()
    			,maMenuMngDetailDTO.getOrdNo()
    			,maMenuMngDetailDTO.getParamValue()
    			,maMenuMngDetailDTO.getMenuNo()
    			,maMenuMngDetailDTO.getIsExternalLink()
    			,maMenuMngDetailDTO.getKeyNo()
    			,maMenuMngDetailDTO.getKeyType()
    			,maMenuMngDetailDTO.getIsUse()
    			,maMenuMngDetailDTO.getServiceType()
    			,maMenuMngDetailDTO.getRemark()
    			,maMenuMngDetailDTO.getExternalLink()
    			,maMenuMngDetailDTO.getIsGetLink()
    	};
    	
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	return rtnValue;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMenuMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMenuMngDetailDTO
     * @return
     */
    public int updateDetail(MaMenuMngDetailDTO maMenuMngDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String pMenuId = maMenuMngDetailDTO.getPmenuId();
    	String lang = loginUser.getLangId();
    	int rtnValue  = 0;
    	if("".equals(pMenuId)){
    		pMenuId = "0";
    	}

    	query.append("UPDATE TAMENU SET		");
    	query.append("	p_menu_id	= ?	    ");
    	query.append("	,ord_no		= ?	    ");
    	query.append("	,page_id	= ?	    ");
    	query.append("	,is_system	= ?	    ");
    	query.append("	,key_no		= ?	    ");
    	query.append("	,key_type	= ?	    ");
    	query.append("	,remark	    = ?	    ");
    	query.append("	,is_use		= ?	    ");
    	query.append("	,is_external_link	= ?	    ");
    	query.append("	,external_link		= ?	    ");
    	query.append("	,is_get_link		= ?	    ");
    	query.append("	,param		= ?	    ");
    	query.append("	,menu_no		= ?	");
    	query.append("	,service_type		= ?	");
    	query.append("	,description = 		");
    	query.append("	 (SELECT key_name FROM TALANG WHERE key_no = ? AND key_type=? AND lang='"+lang+"') ");
    	query.append("WHERE menu_id = ?		");
    	
    	Object[] objects = new Object[] {
    			pMenuId,
    			maMenuMngDetailDTO.getOrdNo(),
    			maMenuMngDetailDTO.getPageId(),
    			maMenuMngDetailDTO.getIsSystem(),
    			maMenuMngDetailDTO.getKeyNo(),
    			maMenuMngDetailDTO.getKeyType(),
    			maMenuMngDetailDTO.getRemark(),
    			maMenuMngDetailDTO.getIsUse(),
    			maMenuMngDetailDTO.getIsExternalLink(),
    			maMenuMngDetailDTO.getExternalLink(),
    			maMenuMngDetailDTO.getIsGetLink(),
    			maMenuMngDetailDTO.getParamValue(),
    			maMenuMngDetailDTO.getMenuNo(),
    			maMenuMngDetailDTO.getServiceType(),
    			maMenuMngDetailDTO.getKeyNo(),
    			maMenuMngDetailDTO.getKeyType(),
    			maMenuMngDetailDTO.getMenuId()
    	};
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}