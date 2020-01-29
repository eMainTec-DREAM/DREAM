package intf.dream.cricket.finder.syscode.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.cricket.finder.syscode.dao.CricketFinderSyscodeListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderSyscodeListDAOTarget"
 * @spring.txbn id="cricketFinderSyscodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderSyscodeListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CricketFinderSyscodeListDAO
{
	public List findSyscodeList(Map map) throws Exception
    {
		String lang = String.valueOf(map.get("lang"));
		String listType = String.valueOf(map.get("listType"));
		String cdsysdNo = String.valueOf(map.get("cdsysdNo"));
		String keyName = String.valueOf(map.get("keyName"));
		String option = String.valueOf(map.get("option"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 										");
    	query.append("		x.cdsysd_no		AS CDSYSDNO				");
        query.append("		,(SELECT y.key_name FROM TALANG y		");
        query.append("			WHERE y.key_type = x.key_type		");
        query.append("			AND   y.key_no = x.key_no			");
        query.append("			AND   y.lang = '" + lang + "'		");
        query.append("		)	AS DESCRIPTION						");
        query.append("FROM TACDSYSD x								");
        query.append("WHERE 1=1										");
        query.append("AND x.cdsysd_no is not null					");
        query.getStringEqualQuery("x.is_use", "Y");
        query.getStringEqualQuery("x.list_type", listType);
        query.getStringEqualQuery("x.cdsysd_no", cdsysdNo);
        if("UNPLAN".equals(option)&&option!=null){
            query.append("AND (param2 is null or param2 !='PM')                                   ");
        }
        if(!"".equals(keyName)&&!"null".equals(keyName)){
        	query.append("AND (x.key_no IN (SELECT y.key_no FROM TALANG y   		");
            query.append("                  WHERE y.lang = '"+lang+"'        		");
            query.append("                 AND y.key_name like '%"+keyName+"%')		");
            query.append("     OR x.cdsysd_no like '%"+keyName+"%')            		");
        }
        query.append("ORDER BY ord_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 

}