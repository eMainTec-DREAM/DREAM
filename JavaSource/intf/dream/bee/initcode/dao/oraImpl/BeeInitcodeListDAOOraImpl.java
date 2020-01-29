package intf.dream.bee.initcode.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.initcode.dao.BeeInitcodeListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeInitcodeListDAOTarget"
 * @spring.txbn id="beeInitcodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeInitcodeListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeInitcodeListDAO
{
	public List findLangList(Map map)
    {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            		");
        query.append("      lang_id     		");
        query.append("      ,lang       		");
        query.append("      ,key_type   		");
        query.append("      ,key_no     		");
        query.append("      ,key_name   		");
        query.append("      ,upd_date   		");
        query.append("      ,is_comm_js_use   	");
        query.append("      ,remark   			");
        query.append("FROM   TALANG a   		");
        query.append("WHERE  1 = 1      		");
        query.append("  AND  use_android='Y'	");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findSyscodeList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            	");
        query.append("      cdsysd_id		");
        query.append("      ,cdsysm_id		");
        query.append("      ,cdsysd_no cdsysdno		");
        query.append("      ,description	");
        query.append("      ,ord_no			");
        query.append("      ,is_use			");
        query.append("      ,list_type		");
        query.append("      ,remark			");
        query.append("      ,param1			");
        query.append("      ,param2			");
        query.append("      ,key_type		");
        query.append("      ,key_no			");
        query.append("FROM   TACDSYSD a		");
        query.append("WHERE  1 = 1			");
        query.append("  AND  is_use='Y'		");
        query.getStringEqualQuery("list_type", String.valueOf(map.get("listType")));
        query.append("ORDER BY a.ord_no		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

}