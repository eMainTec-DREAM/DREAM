package intf.dream.cricket.finder.dept.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.cricket.finder.dept.dao.CricketFinderDeptListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderDeptListDAOTarget"
 * @spring.txbn id="cricketFinderDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements CricketFinderDeptListDAO
{
	public List findDeptList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String deptDesc = String.valueOf(map.get("deptDesc"));
		String deptNo = String.valueOf(map.get("deptNo"));
		String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                             ");
        query.append("        x.dept_id id                               ");
        query.append("       ,x.dept_no no                               ");
        query.append("       ,x.description                              ");
        query.append("       ,x.p_dept_id as pid                         ");
        query.append("       ,LEVEL AS LVL                               ");
        query.append("FROM   TADEPT x                                    ");
    	query.append("WHERE  1=1                                         ");
        query.getAndQuery("x.comp_no",compNo);
        query.append("AND x.plant='"+plant+"'							");
        query.getStringEqualQuery("x.dept_no", deptNo);
        query.getStringEqualQuery("x.is_use", "Y");
        query.getLikeQuery("x.dept_no||x.description", deptDesc);
        query.append("START WITH p_dept_id = '0'						");
        query.append("CONNECT BY PRIOR dept_id = p_dept_id				");
        query.append("ORDER SIBLINGS BY x.ord_no						");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}