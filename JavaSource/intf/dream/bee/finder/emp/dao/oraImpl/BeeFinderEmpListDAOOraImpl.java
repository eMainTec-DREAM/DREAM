package intf.dream.bee.finder.emp.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.finder.emp.dao.BeeFinderEmpListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeFinderEmpListDAOTarget"
 * @spring.txbn id="beeFinderEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeFinderEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeFinderEmpListDAO
{
	public List findEmpList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String empName = String.valueOf(map.get("empName"));
		String empNo = String.valueOf(map.get("empNo"));
		String plant = String.valueOf(map.get("plant"));
		String deptIdOption = String.valueOf(map.get("deptIdOption"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 										");
    	query.append("		x.emp_id		AS ID					");
        query.append("		,x.emp_no		AS NO					");
        query.append("		,x.emp_name		AS DESCRIPTION			");
        query.append("FROM TAEMP x									");
        query.append("WHERE 1=1										");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("x.is_join", "Y");
        query.append("AND x.plant='"+plant+"'						");
        query.getStringEqualQuery("x.emp_no", empNo);
        query.getDeptLevelQuery("x.dept_id", deptIdOption, "", compNo);
        query.getLikeQuery("x.emp_no||x.emp_name", empName);
        query.append("ORDER BY emp_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}