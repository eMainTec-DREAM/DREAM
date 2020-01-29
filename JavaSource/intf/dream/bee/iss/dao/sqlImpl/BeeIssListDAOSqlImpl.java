package intf.dream.bee.iss.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import intf.dream.bee.iss.dao.BeeIssListDAO;
import intf.dream.bee.iss.dto.BeeIssCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeIssListDAOTarget"
 * @spring.txbn id="beeIssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeIssListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeIssListDAO
{
	public List findIssList(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception
    {
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.comp_no AS comp_no								");
    	query.append("		,a.ptisslist_id AS ptisslist_id						");
    	query.append("		,a.ptiss_status AS ptiss_status						");
    	query.append("		,a.ptiss_type as ptiss_type							");
    	query.append("		,a.wopart_id as wopart_id							");
    	query.append("		,a.wkor_id as wkor_id								");
    	query.append("		,a.wcode_id as wcode_id								");
    	query.append("		,(SELECT x.wname									");
    	query.append("			FROM TAWAREHOUSE x								");
    	query.append("			WHERE x.comp_no = a.comp_no						");
    	query.append("			AND x.wcode_id = a.wcode_id ) AS wcode_desc		");
    	query.append("		,a.issue_date as issue_date							");
    	query.append("		,a.issue_dept as issue_dept							");
    	query.append("		,a.issue_by as issue_by								");
    	query.append("		,a.rec_by as rec_by									");
    	query.append("		,(SELECT x.emp_name									");
    	query.append("			FROM TAEMP x									");
    	query.append("			WHERE x.comp_no = a.comp_no						");
    	query.append("			AND x.emp_id = a.rec_by ) AS rec_desc			");
    	query.append("		,a.part_id as part_id								");
    	query.append("		,c.part_no as part_no								");
    	query.append("		,c.description as part_desc							");
    	query.append("		,c.full_desc as full_desc							");
    	query.append("		,a.part_grade as part_grade							");
    	query.append("		,dbo.SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','ko')    AS part_grade_desc		");
    	query.append("		,c.uom as uom										");
    	query.append("		,a.issue_qty as issue_qty							");
    	query.append("		,(SELECT x.stock_qty								");
    	query.append("			FROM TAPTSTOCK x								");
    	query.append("			WHERE x.comp_no = a.comp_no						");
    	query.append("			AND x.part_id = a.part_id						");
    	query.append("			AND x.wcode_id = a.wcode_id						");
    	query.append("			AND x.part_grade = a.part_grade) AS stock_qty	");
    	query.append("		,a.remark as remark									");
    	query.append("FROM TAPTISSLIST a INNER JOIN TAPTISSUE b					");
    	query.append("ON a.comp_no = b.comp_no									");
    	query.append("AND a.ptissue_id = b.ptissue_id							");
    	query.append("INNER JOIN TAPARTS c										");
    	query.append("ON a.comp_no = c.comp_no									");
    	query.append("AND a.part_id = c.part_id									");
    	query.append("WHERE 1=1													");
    	query.append(this.getIssWhere(beeIssCommonDTO, map));
    	
    	return getJdbcTemplate().queryForList(query.toString(beeIssCommonDTO.getIsLoadMaxCount(), beeIssCommonDTO.getFirstRow()));
    } 
	
	public List findIssCount(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1) AS COUNT									");
    	query.append("FROM TAPTISSLIST a INNER JOIN TAPTISSUE b					");
    	query.append("ON a.comp_no = b.comp_no									");
    	query.append("AND a.ptissue_id = b.ptissue_id							");
    	query.append("INNER JOIN TAPARTS c										");
    	query.append("ON a.comp_no = c.comp_no									");
    	query.append("AND a.part_id = c.part_id									");
    	query.append("WHERE 1=1													");
    	query.append(this.getIssWhere(beeIssCommonDTO, map));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	private String getIssWhere(BeeIssCommonDTO beeIssCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
    	String startDate = CommonUtil.convertString(String.valueOf(map.get("startDate")));
    	String endDate = CommonUtil.convertString(String.valueOf(map.get("endDate")));
    	String deptId = CommonUtil.convertString(String.valueOf(map.get("deptId")));
    	String wcodeId = CommonUtil.convertString(String.valueOf(map.get("wcodeId")));
    	String ptIssListId = CommonUtil.convertString(String.valueOf(map.get("ptIssListId")));
    	String partDesc = CommonUtil.convertString(String.valueOf(map.get("partDesc")));
    	String empId = CommonUtil.convertString(String.valueOf(map.get("empId")));
    	String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
    	
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("c.is_deleted", "N");
    	query.append("AND a.ptiss_status IN ('W','X')							");
    	query.append("AND a.plant = '"+plant+"'									");
    	query.getLikeQuery("c.full_desc", partDesc);
    	query.getAndQuery("a.wcode_id", wcodeId);
    	query.getAndQuery("a.ptisslist_id", ptIssListId);
    	query.append("  and a.issue_date >= '"+startDate+"'						");
    	query.append("  and a.issue_date <=  '"+endDate+"'						");
    	query.getAndQuery("a.issue_by", empId);
    	query.getDeptLevelQuery("a.issue_dept", deptId, "", compNo);
		
		return query.toString();
	}
	
	public int deleteIss(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;
        
        //헤더도 삭제해야 함.

        query.append("DELETE FROM TAPTISSLIST					");
        query.append("WHERE comp_no 		  = ?				");
        query.append("AND ptisslist_id        = ?				");
        query.append("AND ptiss_status IN (?,?)					");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptIssListId"))
        		,"X"
        		,"W"
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int insertIss(Map map) throws Exception
    {
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
        		convertString(map.get("compNo"))
        		,convertString(map.get("ptIssListId"))
        		,convertString(map.get("wcodeId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("issDate"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("issueQty"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("issueQty"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,"W"
        		,convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
    			,convertString(map.get("recBy"))
        		,"WOISS"
        		,convertString(map.get("compNo"))
    			,convertString(map.get("recBy"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("plant"))
        		,""
        		,convertString(map.get("issueId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int insertIssHdr(Map map) throws Exception
    {
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
        		,convertString(map.get("issueId"))
        		,"W"
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,"WOISS"
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
	public int updateIss(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPTISSLIST SET                      ");
        query.append("        PART_ID           = ?               ");
        query.append("       ,PART_GRADE        = ?               ");
        query.append("       ,REC_BY            = ?               ");
        query.append("       ,ISSUE_QTY         = ?               ");
        query.append("       ,REMARK            = ?               ");
        query.append("WHERE COMP_NO             = ?               ");
        query.append("  AND PTISSLIST_ID         = ?               ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("recBy"))
        		,convertString(map.get("issueQty"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("ptIssListId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
}