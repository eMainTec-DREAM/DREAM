package intf.dream.android.online.iss.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.iss.dao.AnOnIssListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnIssListDAOTarget"
 * @spring.txbn id="anOnIssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnIssListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnIssListDAO
{
	public List findIssList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String ptIssListId = String.valueOf(map.get("ptIssListId"));
    	String partDesc = String.valueOf(map.get("partDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
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
    	query.append("		,NVL(c.description,'') ||','|| NVL(c.pt_size,'')||','||NVL(c.model,'')||','||NVL(c.maker,'') as full_desc	");
    	query.append("		,a.part_grade as part_grade							");
    	query.append("		,(SELECT SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+lang+"') FROM DUAL)    AS part_grade_desc		");
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
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.ptiss_status IN ('W','X')							");
    	query.append("AND a.plant = '"+plant+"'									");
    	query.getLikeQuery("NVL(c.description,'') ||','|| NVL(c.pt_size,'')||','||NVL(c.model,'')||','||NVL(c.maker,'')||NVL(c.part_no,'')", partDesc);
    	query.getAndQuery("a.wcode_id", wcodeId);
    	query.getAndQuery("a.ptisslist_id", ptIssListId);
    	query.append("  and a.issue_date >= '"+startDate+"'						");
    	query.append("  and a.issue_date <=  '"+endDate+"'						");
    	query.getAndQuery("a.issue_by", empId);
    	query.getDeptLevelQuery("a.issue_dept", deptId, "", compNo);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	
	public int deleteIss(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertIss(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int insertIssHdr(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateIss(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPTISSLIST SET                      ");
        query.append("        PART_ID           = ?               ");
        query.append("       ,PART_GRADE        = ?               ");
        query.append("       ,REC_BY            = ?               ");
        query.append("       ,ISSUE_QTY         = ?               ");
        query.append("       ,REMARK            = ?               ");
        query.append("WHERE COMP_NO             = ?               ");
        query.append("  AND PTISSLIST_ID        = ?               ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("recBy"))
        		,convertString(map.get("issueQty"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("ptIssListId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
}