package intf.dream.cricket.finder.dept.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.cricket.finder.dept.dao.CricketFinderDeptListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderDeptListDAOTarget"
 * @spring.txbn id="cricketFinderDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements CricketFinderDeptListDAO
{
	public List findDeptList(Map map) throws Exception
    {
		String compNo   = String.valueOf(map.get("compNo"));
		String deptDesc = String.valueOf(map.get("deptDesc"));
		String deptNo   = String.valueOf(map.get("deptNo"));
		String plant    = String.valueOf(map.get("plant"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;         ");
        query.append("SELECT                                                    ");
        query.append("         x.dept_id id                                     ");
        query.append("         ,x.dept_no no                                    ");
        query.append("         ,x.description                                   ");
        query.append("        ,x.p_dept_id as pid                               ");
        query.append("       ,y.lvl AS LVL                                      ");
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+compNo+"','0')) y                                           ");
    	query.append("WHERE x.dept_id = y.dept_id                                                ");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.plant='"+plant+"'								");
        query.getStringEqualQuery("x.dept_no", deptNo);
        query.getStringEqualQuery("x.is_use", "Y");
        query.getLikeQuery("x.dept_no+x.description", deptDesc);
    	query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
}