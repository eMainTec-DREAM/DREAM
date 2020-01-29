package intf.dream.bee.finder.failure.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.finder.failure.dao.BeeFinderFailureListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderFailureListDAOTarget"
 * @spring.txbn id="beeFinderFailureListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderFailureListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeFinderFailureListDAO
{
	public List findFailureList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String failureType = String.valueOf(map.get("failureType"));
		String failureDesc = String.valueOf(map.get("failureDesc"));
		String failureNo = String.valueOf(map.get("failureNo"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.failure_id id                             ");
        query.append("         ,x.failure_no code                          ");
        query.append("         ,'['||x.failure_no||'] '                    ");
        query.append("         ||(SELECT y.key_name FROM TALANG y          ");
        query.append("             WHERE y.key_type = x.key_type           ");
        query.append("               AND   y.key_no = x.key_no             ");
        query.append("               AND   y.lang = '"+lang+"'             ");
        query.append("             ) description                           ");
        query.append("FROM TAFAILURE x                                     ");
        query.append("WHERE x.is_use='Y'                                   ");
        query.getAndQuery("x.fail_type",failureType);
        query.getAndQuery("x.failure_no",failureNo);
        if(!"".equals(failureDesc)&&!"null".equals(failureDesc)){
            query.append("AND (x.key_no IN (SELECT y.key_no FROM TALANG y   ");
            query.append("                  WHERE y.lang = '"+lang+"'       ");
            query.append("                 AND y.key_name like '%"+failureDesc+"%')");
            query.append("     OR x.failure_no like '%"+failureDesc+"%')           ");
        }
        query.append("ORDER BY x.ord_no                                     ");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}