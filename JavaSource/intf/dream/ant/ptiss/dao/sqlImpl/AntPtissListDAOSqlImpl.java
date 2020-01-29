package intf.dream.ant.ptiss.dao.sqlImpl;

import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.ant.ptiss.dao.AntPtissListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antPtissListDAOTarget"
 * @spring.txbn id="antPtissListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntPtissListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AntPtissListDAO
{
    public int savePtissList(Map map, String ptIssListId, String ptIssueId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAPTISSLIST (                               ");
        query.append("       COMP_NO, PTISSLIST_ID, WCODE_ID, PART_GRADE      ");
        query.append("       ,ISSUE_DATE,PART_ID, ISSUE_QTY, UNIT_PRICE       ");
        query.append("       ,TOT_PRICE, PTISS_STATUS, ISSUE_DEPT, ISSUE_BY   ");
        query.append("       ,REC_BY, PTISS_TYPE, REC_DEPT,REMARK             ");
        query.append("       ,PLANT, USER_PTISS_TYPE,PTISSUE_ID               ");
        query.append("       )                                                ");
        query.append("VALUES(                                                 ");
        query.append("      ?,?,?,?,                                          ");
        query.append("      ?,?,?,(SELECT a.last_price FROM TAPARTS a WHERE comp_no = ? AND part_id = ?), ");
        query.append("      (SELECT a.last_price * ? FROM TAPARTS a WHERE comp_no = ? AND part_id = ?), ");
        query.append("      ?,?,?,                                            ");
        query.append("		?,?,(SELECT a.dept_id FROM TAEMP a WHERE comp_no = ? AND emp_id = ?),?,	");
        query.append("		?, ?,?		                                      ");
        query.append("      )                                                 ");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("compNo")),
    			ptIssListId,
    			convertString(map.get("wcodeId")),
    			convertString(map.get("partGrade")),
    			convertString(map.get("issDate")),
    			convertString(map.get("partId")),
    			convertString(map.get("issQty")),
    			convertString(map.get("compNo")),
    			convertString(map.get("partId")),
    			convertString(map.get("issQty")),
    			convertString(map.get("compNo")),
    			convertString(map.get("partId")),
    			"W",
    			convertString(map.get("issDept")),
    			convertString(map.get("issBy")),
    			convertString(map.get("recBy")),
    			convertString(map.get("ptIssType")),
    			convertString(map.get("compNo")),
    			convertString(map.get("recBy")),
    			convertString(map.get("remark")),
    			convertString(map.get("plant")),
    			"",
    			ptIssueId
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
    
public int savePtissue(Map map, String ptIssueId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("INSERT INTO TAPTISSUE (                                 ");
        query.append("       COMP_NO, PTISSUE_ID, PTISSUE_STATUS, DESCRIPTION ");
        query.append("       ,PTISS_TYPE,USER_PTISS_TYPE, PLANT, WCODE_ID     ");
        query.append("       ,ISSUE_DATE, ISSUE_DEPT, ISSUE_BY, REC_DEPT      ");
        query.append("       ,REC_BY, REMARK                                  ");
        query.append("       )                                                ");
        query.append("VALUES(                                                 ");
        query.append("      ?,?,?,(SELECT a.description FROM TAPARTS a WHERE comp_no = ? AND part_id = ?), ");
        query.append("      ?,?,?,?,                                          ");
        query.append("      ?,?,?,(SELECT a.dept_id FROM TAEMP a WHERE comp_no = ? AND emp_id = ?),	");
        query.append("		?,?		                                          ");
        query.append("      )                                                 ");
        
        Object[] objects = new Object[] {
        		convertString(map.get("compNo"))
        		,ptIssueId
        		,"W"
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
    			,convertString(map.get("ptIssType"))
        		,""
        		,convertString(map.get("plant"))
        		,convertString(map.get("wcodeId"))
        		,convertString(map.get("issDate"))
        		,convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("compNo"))
    			,convertString(map.get("recBy"))
    			,convertString(map.get("recBy"))
        		,convertString(map.get("remark"))
        };
        
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
}