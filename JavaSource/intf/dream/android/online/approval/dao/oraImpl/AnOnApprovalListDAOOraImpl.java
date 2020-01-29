package intf.dream.android.online.approval.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.android.online.approval.dao.AnOnApprovalListDAO;

/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnApprovalListDAOTarget"
 * @spring.txbn id="anOnApprovalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */

public class AnOnApprovalListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnApprovalListDAO {
    public List findApprovalList(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String apprType = String.valueOf(map.get("apprType"));
        String objectId = String.valueOf(map.get("objectId"));
        String lang = String.valueOf(map.get("lang"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT x.apprlist_id AS apprListId          ");
        query.append("    ,x.appr_type AS apprType            ");
        query.append("    ,x.object_id AS objectId            ");
        query.append("    ,x.appr_status AS apprStatusId          ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','" + lang + "') FROM DUAL) AS apprStatusDesc  ");
        query.append("    ,x.req_by AS reqBy                ");
        query.append("    ,(SELECT a.emp_name               ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqName      ");
        query.append("    ,(SELECT a.dept_id                 ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqDeptId    ");
        query.append("    ,(SELECT                      ");
        query.append("        (SELECT b.description          ");
        query.append("          FROM TADEPT b            ");
        query.append("          WHERE b.comp_no = a.comp_no      ");
        query.append("          AND b.dept_id = a.dept_id)      ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqDeptDesc    	");
        query.append("    ,x.req_date AS reqDate              			");
        query.append("    ,x.title AS TITLE                         	");
        query.append("FROM TAAPPRLIST x                             	");
        query.append("WHERE 1=1                                     	");
        query.append("AND x.comp_no = '" + compNo + "'              	");
        query.getStringEqualQuery("x.appr_type", apprType);
        query.getAndNumKeyQuery("x.object_id", objectId);
        query.append("ORDER BY x.req_date asc                        ");
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public List findApprovalUserList(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String apprListId = String.valueOf(map.get("apprListId"));
        String apprUsrId = String.valueOf(map.get("apprUsrId"));
        String lang = String.valueOf(map.get("lang"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT x.apprusr_id AS apprUsrId            ");
        query.append("    ,x.apprlist_id AS apprListId          ");
        query.append("    ,x.appr_seq AS apprSeq              ");
        query.append("    ,x.appr_by AS apprBy              ");
        query.append("    ,(SELECT a.emp_name               ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.appr_by) AS apprName    ");
        query.append("    ,(SELECT a.dept_id                 ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.appr_by) AS apprDeptId    ");
        query.append("    ,(SELECT                      ");
        query.append("        (SELECT b.description          ");
        query.append("          FROM TADEPT b            ");
        query.append("          WHERE b.comp_no = a.comp_no      ");
        query.append("          AND b.dept_id = a.dept_id)      ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.appr_by) AS apprDeptDesc  ");
        query.append("    ,x.apprusr_action AS apprUsrActionId      ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.apprusr_action,'APPRUSR_ACTION','SYS','','" + lang + "') FROM DUAL) AS apprUsrActionDesc  ");
        query.append("    ,x.apprusr_status AS apprUsrStatusId      ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.apprusr_status,'APPRUSR_STATUS','SYS','','" + lang + "') FROM DUAL) AS apprUsrStatusDesc  ");
        query.append("    ,x.appr_date AS apprDate            ");
        query.append("    ,x.appr_time AS apprTime            ");
        query.append("    ,x.apprusr_type AS apprUsrTypeId        ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','" + lang + "') FROM DUAL) AS apprUsrTypeDesc      ");
        query.append("FROM TAAPPRUSR x                    ");
        query.append("WHERE 1=1                        ");
        query.append("AND x.comp_no = '" + compNo + "'              ");
        query.getAndNumKeyQuery("x.apprlist_id", apprListId);
        query.getAndNumKeyQuery("x.apprusr_id", apprUsrId);
        query.append("ORDER BY x.appr_seq, x.appr_date, x.appr_time      ");
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public List findApprovalUserNextNo(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String apprListId = String.valueOf(map.get("apprListId"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT MAX(NVL(appr_seq,0))+1  AS apprSeq  ");
        query.append("FROM TAAPPRUSR x              ");
        query.append("WHERE 1=1                  ");
        query.append("AND x.comp_no = '" + compNo + "'        ");
        query.getAndNumKeyQuery("x.apprlist_id", apprListId);
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public int insertApproval(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRLIST (                                                  ");
        query.append("     COMP_NO, APPRLIST_ID, APPR_TYPE, OBJECT_ID, APPR_STATUS              ");
        query.append("     , REQ_BY, REQ_DATE, TITLE, REMARK                                    ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?                                                             ");
        query.append("         )                                                                ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("apprType"))
        		, this.convertString(map.get("objectId"))
        		, "W"
        		, this.convertString(map.get("reqBy"))
        		, DateUtil.getDate()
        		, this.convertString(map.get("title"))
        		, ""
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int updateApproval(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("UPDATE TAAPPRLIST               ");
        query.append("SET title = ?                   ");
        query.append("WHERE comp_no = ?               ");
        query.append("AND   apprlist_id = ?           ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("title"))
        		, this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int insertApprovalUser(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRUSR (                                                   ");
        query.append("     COMP_NO, APPRUSR_ID, APPRLIST_ID, APPR_SEQ, APPR_BY                  ");
        query.append("     , APPRUSR_ACTION, APPRUSR_STATUS, APPR_DATE, APPR_TIME, REMARK       ");
        query.append("     , APPRUSR_TYPE                                                       ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,?,?,?,?                                                           ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?                                                                   ");
        query.append("         )                                                                ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprUsrId"))
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("apprSeq"))
        		, this.convertString(map.get("apprBy"))
        		, "Z"
        		, "Z"
        		, DateUtil.getDate()
        		, ""
        		, ""
        		, this.convertString(map.get("apprUsrTypeId"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int updateApprovalUser(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("UPDATE TAAPPRUSR                ");
        query.append("SET appr_seq     = ?            ");
        query.append("   ,appr_by      = ?            ");
        query.append("   ,apprusr_type = ?            ");
        query.append("WHERE comp_no = ?               ");
        query.append("AND   apprlist_id = ?           ");
        query.append("AND   apprusr_id  = ?           ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("apprSeq"))
        		, this.convertString(map.get("apprBy"))
        		, this.convertString(map.get("apprUsrTypeId"))
        		, this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("apprUsrId"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int deleteApprovalUser(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("DELETE FROM  TAAPPRUSR          ");
        query.append("WHERE comp_no = ?               ");
        query.append("AND   apprlist_id = ?           ");
        query.append("AND   apprusr_id  = ?           ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("apprUsrId"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public List findApprovalLineList(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String empId = String.valueOf(map.get("empId"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT x.apprline_id AS apprLineId          ");
        query.append("    ,x.title AS title                ");
        query.append("    ,x.remark AS remark                ");
        query.append("FROM TAAPPRLINE x                    ");
        query.append("WHERE 1=1                        ");
        query.append("AND x.comp_no = '" + compNo + "'              ");
        query.append("AND x.emp_id = '" + empId + "'              ");
        query.append("ORDER BY x.apprline_id                ");
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public List findApprovalLineUserList(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String apprLineId = String.valueOf(map.get("apprLineId"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT x.apprlineusr_id AS apprLineUsrId        ");
        query.append("    ,x.apprline_id AS apprLineId          ");
        query.append("    ,x.appr_seq AS apprSeq              ");
        query.append("    ,x.appr_by AS apprBy              ");
        query.append("    ,'' AS remark                ");
        query.append("    ,(SELECT a.emp_name               ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.appr_by) AS apprName    ");
        query.append("    ,(SELECT a.grade                 ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.appr_by) AS apprGrade    ");
        query.append("FROM TAAPPRLINEUSR x                  ");
        query.append("WHERE 1=1                        ");
        query.append("AND x.comp_no = '" + compNo + "'              ");
        query.append("AND x.apprline_id = '" + apprLineId + "'          ");
        query.append("ORDER BY x.appr_seq                  ");
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public int addApprovalLine(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRUSR(                             ");
        query.append("   COMP_NO, APPRUSR_ID, APPRLIST_ID, APPR_SEQ, APPR_BY             ");
        query.append("   , APPRUSR_ACTION, APPRUSR_STATUS, APPR_DATE, APPR_TIME, REMARK  ");
        query.append("   , APPRUSR_TYPE                                 ");
        query.append("   )                                      ");
        query.append("SELECT x.comp_no, sqaapprusr_id.nextval,?              ");
        query.append("       ,(SELECT MAX(NVL(a.appr_seq,0))            ");
        query.append("          FROM TAAPPRUSR a                    ");
        query.append("          WHERE a.comp_no = x.comp_no                ");
        query.append("          AND a.apprlist_id = ?) + row_number() over(order by x.appr_seq)                  ");
        query.append("       ,x.appr_by , ?, ?, ?, ?, ?, x.apprusr_type          ");
        query.append("FROM TAAPPRLINEUSR x                        ");
        query.append("WHERE 1=1                              ");
        query.append("AND x.comp_no = ?                          ");
        query.append("AND x.apprline_id = ?                        ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("apprListId"))
        		, "Z"
        		, "Z"
        		, DateUtil.getDate()
        		, ""
        		, ""
        		, this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprLineId"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int addReqUser(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRUSR (                                                   ");
        query.append("     COMP_NO, APPRUSR_ID, APPRLIST_ID, APPR_SEQ, APPR_BY                  ");
        query.append("     , APPRUSR_ACTION, APPRUSR_STATUS, APPR_DATE, APPR_TIME, REMARK       ");
        query.append("     , APPRUSR_TYPE                                                       ");
        query.append("      )                                                                   ");
        query.append("VALUES (                                                                  ");
        query.append("      ?,sqaapprusr_id.nextval,?,?,?                                       ");
        query.append("     ,?,?,?,?,?                                                           ");
        query.append("     ,?                                                                   ");
        query.append("         )                                                                ");
        
        Object[] objects = new Object[]{
        		this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		, "0"
        		, this.convertString(map.get("apprBy"))
        		, "C"
        		, "A"
        		, DateUtil.getDate()
        		, ""
        		, ""
        		, "DF"
        		};
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int updateReqStatus(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("UPDATE TAAPPRLIST x                               ");
        query.append("SET    x.appr_status  = ?                         ");
        query.append("WHERE  x.apprlist_id  = ?                         ");
        query.append("  AND  x.comp_no  = ?                             ");
        
        Object[] objects = new Object[]{
        		"R"
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("compNo"))
        		};
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int updateNextUsrStatus(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("UPDATE TAAPPRUSR  set             ");
        query.append("    apprusr_action = ?            ");
        query.append("   ,apprusr_status = ?            ");
        query.append("WHERE apprlist_id = ?             ");
        query.append("    and  comp_no  = ?             ");
        query.append("    and  apprusr_action  != 'C'   ");
        
        Object[] objects = new Object[]{
        		"Z"
        		, "Z"
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("compNo"))
        		};
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public int initApprUser(Map map) throws Exception {
        QueryBuffer query = new QueryBuffer();
        query.append("UPDATE TAAPPRUSR                  ");
        query.append("SET apprusr_action = ?            ");
        query.append("WHERE appr_seq <=   (SELECT       ");
        query.append("                           nvl(min(appr_seq),1)        ");
        query.append("                    FROM   TAAPPRUSR                   ");
        query.append("                    WHERE  1=1                         ");
        query.append("                      AND  apprlist_id    = ?          ");
        query.append("                      AND  comp_no        = ?          ");
        query.append("                      AND  apprusr_type   = 'AP'       ");
        query.append("                      and  apprusr_action  != 'C'      ");
        query.append("                   )                                   ");
        query.append("      AND  apprlist_id    = ?                          ");
        query.append("      AND  comp_no        = ?                          ");
        query.append("      and  apprusr_action  != 'C'                      ");
        
        Object[] objects = new Object[]{
        		"P"
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("compNo"))
        		, this.convertString(map.get("apprListId"))
        		, this.convertString(map.get("compNo"))
        		};
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public List findApprovalReadyList(Map map) throws Exception {
        String compNo = String.valueOf(map.get("compNo"));
        String apprType = String.valueOf(map.get("apprType"));
        String objectId = String.valueOf(map.get("objectId"));
        String apprBy = String.valueOf(map.get("apprBy"));
        String lang = String.valueOf(map.get("lang"));
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT x.apprlist_id AS apprListId          ");
        query.append("    ,y.apprusr_id AS apprUsrId            ");
        query.append("    ,x.object_id AS objectId            ");
        query.append("    ,x.req_date AS reqDate              ");
        query.append("    ,x.req_by AS reqBy                ");
        query.append("    ,(SELECT a.emp_name               ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqName      ");
        query.append("    ,(SELECT a.dept_id                 ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqDeptId    ");
        query.append("    ,(SELECT                      ");
        query.append("        (SELECT b.description          ");
        query.append("          FROM TADEPT b            ");
        query.append("          WHERE b.comp_no = a.comp_no      ");
        query.append("          AND b.dept_id = a.dept_id)      ");
        query.append("      FROM TAEMP a                ");
        query.append("      WHERE a.comp_no = x.comp_no          ");
        query.append("      AND a.emp_id = x.req_by) AS reqDeptDesc    ");
        query.append("    ,x.title AS title                ");
        query.append("    ,x.appr_type AS apprTypeId            ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.appr_type,'APPR_TYPE','SYS','','" + lang + "') FROM DUAL) apprTypeDesc          ");
        query.append("    ,x.appr_status AS apprStatusId          ");
        query.append("    ,(SELECT SFACODE_TO_DESC(x.appr_status,'APPR_STATUS','SYS','','" + lang + "') FROM DUAL)  apprStatusDesc      ");
        query.append("    ,y.apprusr_type AS apprUsrTypeId        ");
        query.append("    ,(SELECT SFACODE_TO_DESC(y.apprusr_type,'APPRUSR_TYPE','SYS','','" + lang + "') FROM DUAL)  apprUsrTypeDesc    ");
        query.append("FROM  TAAPPRLIST x INNER JOIN TAAPPRUSR y        ");
        query.append("ON x.comp_no = y.comp_no                ");
        query.append("AND x.apprlist_id = y.apprlist_id       ");
        query.append("WHERE x.comp_no = '" + compNo + "'      ");
        query.append("AND x.appr_status IN ('R','P')          ");
        query.append("AND y.apprusr_action = 'P'              ");
        query.getStringEqualQuery("x.appr_type", apprType);
        query.getAndNumKeyQuery("x.object_id", objectId);
        query.getAndNumKeyQuery("y.appr_by", apprBy);
        query.append("ORDER BY x.req_date asc                ");
        return this.getJdbcTemplate().queryForList(query.toString());
    }
}
