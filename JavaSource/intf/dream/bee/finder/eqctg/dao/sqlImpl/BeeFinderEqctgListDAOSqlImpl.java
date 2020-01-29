package intf.dream.bee.finder.eqctg.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.finder.eqctg.dao.BeeFinderEqctgListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderEqctgListDAOTarget"
 * @spring.txbn id="beeFinderEqctgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderEqctgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeFinderEqctgListDAO
{
	public List findEqctgList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String eqCtgDesc = String.valueOf(map.get("eqCtgDesc"));
		String eqCtgNo = String.valueOf(map.get("eqCtgNo"));
		String plant = String.valueOf(map.get("plant"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        query.append("         x.eqctg_id id                                ");
        query.append("         ,x.eqctg_no no                               ");
        query.append("         ,x.full_desc description                     ");
        query.append("         ,x.p_eqctg_id as pid                         ");
        query.append("         ,CASE WHEN x.lvl IS NULL THEN 1 ELSE x.lvl END as lvl ");
        query.append("FROM TAEQCTG x                                        ");
        query.append("WHERE 1=1                                             ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.eqctg_no",eqCtgNo);
        query.getLikeQuery("x.eqctg_no+x.full_desc", eqCtgDesc);
        query.append("ORDER BY x.eqctg_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}