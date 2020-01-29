package intf.dream.cricket.finder.eqloc.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.cricket.finder.eqloc.dao.CricketFinderEqlocListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderEqlocListDAOTarget"
 * @spring.txbn id="cricketFinderEqlocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderEqlocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CricketFinderEqlocListDAO
{
	public List findEqlocList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String eqLocDesc = String.valueOf(map.get("eqLocDesc"));
		String eqLocNo = String.valueOf(map.get("eqLocNo"));
		String plant = String.valueOf(map.get("plant"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("         x.eqloc_id id                                ");
        query.append("         ,x.eqloc_no no                               ");
        query.append("         ,x.full_desc description                     ");
        query.append("         ,x.p_eqloc_id as pid                         ");
        query.append("         ,x.lvl as lvl                                ");
        query.append("FROM TAEQLOC x                                        ");
        query.append("WHERE 1=1                                             ");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.plant='"+plant+"'								");
        query.getAndQuery("x.eqloc_no",eqLocNo);
        query.getLikeQuery("x.eqloc_no+x.full_desc", eqLocDesc);
        query.append("ORDER BY x.eqloc_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}