package intf.dream.android.online.cal.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.online.cal.dao.AnOnCalListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnCalListDAOTarget"
 * @spring.txbn id="anOnCalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnCalListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnCalListDAO
{
	public List findCalList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String wkorId = String.valueOf(map.get("wkorId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH eqInfo AS (					");
        query.append("    SELECT a.equip_id,			");
        query.append("           a.wkor_id,				");
        query.append("           b.item_no,				");
        query.append("           a.comp_no,				");
        query.append("           b.sub_mng_id,			");
        query.append("           b.eqctg_id,			");
        query.append("           b.eqloc_id,			");
        query.append("           (SELECT x.full_desc FROM TAEQLOC x WHERE x.comp_no = b.comp_no AND x.eqloc_id = b.eqloc_id) eqloc_desc,	");
        query.append("           b.old_eq_no,			");
        query.append("           b.description ,		");
        query.append("           b.eqctg_type			");
        query.append("FROM  TAWOEQUIP a, TAEQUIPMENT b	");
        query.append("     WHERE a.comp_no = b.comp_no	");
        query.append("     AND a.equip_id = b.equip_id	");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("		AND b.is_deleted='N'		");
        query.append("     AND b.is_last_version='Y')	");
        query.append("SELECT a.comp_no																					AS COMPNO		");
    	query.append("		,a.wkor_id																					AS WKORID		");
    	query.append("		,a.description																				AS WODESC		");
    	query.append("		,a.wo_no																					AS WONO			");
    	query.append("		,a.wo_type																					AS WOTYPE		");
    	query.append("		,a.pm_type																					AS PMTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.pm_type,a.wo_type||'_TYPE','SYS','','"+lang+"') FROM DUAL)							AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.wo_status																				AS WO_STATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','','"+lang+"') FROM DUAL)								AS WOSTATUSDESC	");
    	query.append("		,(SELECT x.equip_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPID		");
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPNO		");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.wkor_id = b.wkor_id AND rownum=1) 				AS EQUIPDESC");
        query.append(" 		,a.dept_id 																					AS DEPTID		");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
        query.append(" 		,a.emp_id 																					AS EMPID		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
        query.append(" 		,a.perform 																					AS PERFORM		");
    	query.append("		,(SELECT count(1) FROM TAWOCALIBSTDEQ x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCALIBSTDEQCNT	");
        query.append("		,CASE a.pm_type WHEN 'GNL' THEN 																			");
    	query.append("			(SELECT count(1) FROM TAWOCALIBGNLVALUE x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 		");
        query.append("						WHEN 'PRS' THEN 																			");
    	query.append("			(SELECT count(1) FROM TAWOCALIBGNLVALUE x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 		");
        query.append("						WHEN 'SCL' THEN 																			");
    	query.append("			(SELECT count(1) FROM TAWOCALIBSCLVALUE x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 		");
        query.append("						ELSE 																						");
    	query.append("			(SELECT count(1) FROM TAWOCALIBETCVALUE x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 		");
        query.append("						END   AS WOCALIBVALUECNT																	");
    	query.append("		,(SELECT count(1) FROM TAWOCRAFT x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCRAFTCNT	");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 												");
    	query.append("											AND x.object_id = a.wkor_id AND object_type='WORESULT'))	AS WOPHOTOCNT	");
    	query.append("		,(SELECT x.eqloc_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCID		");
    	query.append("		,(SELECT x.eqloc_desc FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 			AS EQLOCDESC	");
        query.append("		,b.calib_env 																				AS CALIBENV		");
        query.append("		,b.calib_corp 																				AS CALIBCORP	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.calib_corp,'CALIB_CORP','USR',b.comp_no,'"+lang+"') FROM DUAL)					AS CALIBCORPDESC	");
        query.append("		,b.calib_type 																				AS CALIBTYPE	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.calib_type,'CALIB_TYPE','SYS','','"+lang+"') FROM DUAL)							AS CALIBTYPEDESC	");
        query.append("         ,b.calib_result_status CALIBRESULTSTATUS      ");
        query.append("		,(SELECT SFACODE_TO_DESC(b.calib_result_status,'CALIB_RSLT_STATUS','SYS','','"+lang+"') FROM DUAL)	AS CALIBRESULTSTATUSDESC	");
    	query.append("FROM TAWORKORDER a INNER JOIN TAWOCALIB b																			");
    	query.append("ON a.comp_no = b.comp_no																											");
    	query.append("AND a.wkor_id = b.wkor_id																											");
    	query.append("WHERE 1=1																											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.append("AND a.plant='"+plant+"'			");
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.getAndQuery("a.emp_id", empId);
    	query.getAndDateQuery("a.wkor_date", startDate, endDate);

    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	
    	if (!eqctgType.equals("null") && !"".equals(eqctgType))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!eqlocId.equals("null") && !"".equals(eqlocId))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	if (!equipDesc.equals("null") && !"".equals(equipDesc))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getLikeQuery("x.description||x.item_no", equipDesc);
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.append("			)								");
        }
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public int deleteWoCalib(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOCALIB			");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	
	public int deleteCal(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWORKORDER		");
        query.append("WHERE comp_no = ?				");
        query.append("AND wkor_id   = ?				");
        query.append("AND wo_type   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,"PMC"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertCal(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWORKORDER(                                      ");
        query.append("     COMP_NO, WKOR_ID, WO_NO,                                 ");
        query.append("     DESCRIPTION, WO_STATUS, WO_TYPE, PM_TYPE,                ");
        query.append("     START_DATE, START_TIME, END_DATE, END_TIME,              ");
        query.append("     WORK_TIME,DEPT_ID, EMP_ID, PERFORM,                      ");
        query.append("     PM_ID, PMSCHED_ID, WO_GEN_TYPE, WOPOINT_ID,              ");
        query.append("     UPD_DATE, UPD_BY, SELF_VENDOR_TYPE, VENDOR_ID,           ");
        query.append("     P_WKOR_ID, WKOR_DATE, SHIFT_TYPE, TOT_AMT,               ");
        query.append("     PMACTION, EQLOC_ID, WKCTR_ID, CLOSE_ID,                  ");
        query.append("     CLOSE_DATE,IS_DELETED,PLANT                              ");
        query.append("      )                                                       ");
        query.append("VALUES (                                                      ");
        query.append("     ?,?,?,                                                   ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?                                                    ");
        query.append("     )                                                        ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woDesc"))
        		,"P"
        		,convertString(map.get("woType"))
        		,convertString(map.get("pmType"))
        		,""
        		,""
        		,""
        		,""
        		,""
        		,convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("perform"))
        		,""
        		,""
                ,"WORSLT"
        		,""
        		,DateUtil.getDate()
        		,convertString(map.get("userId"))
        		,""
        		,""
        		,""
        		,convertString(map.get("wkorDate"))
        		,convertString(map.get("shiftType"))
        		,""
        		,""
        		,""
        		,convertString(map.get("wkCtrId"))
        		,""
        		,""
        		,"N"
        		,convertString(map.get("plant"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertWoCalib(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOCALIB(                                        ");
        query.append("     COMP_NO, WKOR_ID, CALIB_ENV, CALIB_CORP,                 ");
        query.append("     CALIB_TYPE, CALIB_RESULT_STATUS                          ");
        query.append("      )                                                       ");
        query.append("VALUES (                                                      ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?                                                      ");
        query.append("         )                                                    ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("calibEnv"))
        		,convertString(map.get("calibCorp"))
        		,convertString(map.get("calibType"))
        		,convertString(map.get("calibResultStatus"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCal(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWORKORDER SET			");
        query.append("       DESCRIPTION		= ?		");
        query.append("       ,WKOR_DATE			= ?		");
        query.append("       ,PERFORM			= ?		");
        query.append("       ,EMP_ID			= ?		");
        query.append("WHERE COMP_NO    = ?				");
        query.append("  AND WKOR_ID    = ?				");
        
        objects = new Object[] {
        		convertString(map.get("woDesc"))
        		,convertString(map.get("wkorDate"))
        		,convertString(map.get("perform"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int updateWoCalib(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWOCALIB SET                     ");
        query.append("       CALIB_ENV            = ?,         ");
        query.append("       CALIB_CORP           = ?,         ");
        query.append("       CALIB_TYPE           = ?,         ");
        query.append("       CALIB_RESULT_STATUS  = ?          ");
        query.append("WHERE COMP_NO    = ?                     ");
        query.append("  AND WKOR_ID    = ?                     ");

        objects = new Object[] {
        		convertString(map.get("calibEnv"))
        		,convertString(map.get("calibCorp"))
        		,convertString(map.get("calibType"))
        		,convertString(map.get("calibResultStatus"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public List findCalEtcValueList(Map map) throws Exception
    {
		String compNo 				= String.valueOf(map.get("compNo"));
    	String wkorId 				= String.valueOf(map.get("wkorId"));
    	String woCalibEtcValueId 	= String.valueOf(map.get("woCalibEtcValueId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                 ");
        query.append("         a.wocalibetcvalue_id id                       ");
        query.append("         ,a.comp_no comp_no                            ");
        query.append("         ,a.wocalibetcvalue_id wocalibetcvalue_id      ");
        query.append("         ,a.wkor_id wkor_id                            ");
        query.append("         ,a.set_value set_value                        ");
        query.append("         ,a.basis_value basis_value                    ");
        query.append("         ,a.before_value before_value                  ");
        query.append("         ,a.before_diff_value before_diff_value        ");
        query.append("         ,a.after_value after_value                    ");
        query.append("         ,a.after_diff_value after_diff_value          ");
        query.append("         ,a.remark remark                              ");
        query.append("FROM TAWOCALIBETCVALUE a                               ");
        query.append("WHERE 1=1                                              ");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.wkor_id",wkorId);
        query.getAndQuery("a.wocalibetcvalue_id",woCalibEtcValueId);
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int deleteCalEtcValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOCALIBETCVALUE	");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wocalibetcvalue_id", convertString(map.get("woCalibEtcValueId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	public int insertCalEtcValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("INSERT INTO TAWOCALIBETCVALUE (											");
        query.append("       comp_no, wocalibetcvalue_id, wkor_id, set_value					");
        query.append("       ,basis_value, before_value, before_diff_value, after_value			");
        query.append("       ,after_diff_value, remark											");
        query.append("       )																	");
        query.append("VALUES(																	");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?																	");
        query.append("      )																	");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woCalibEtcValueId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("setValue"))
        		,convertString(map.get("basisValue"))
        		,convertString(map.get("beforeValue"))
        		,convertString(map.get("beforeDiffValue"))
        		,convertString(map.get("afterValue"))
        		,convertString(map.get("afterDiffValue"))
        		,convertString(map.get("remark"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCalEtcValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOCALIBETCVALUE SET                                    ");
        query.append("       set_value                 = ?                            ");
        query.append("       ,basis_value              = ?                            ");
        query.append("       ,before_value             = ?                            ");
        query.append("       ,before_diff_value        = ?                            ");
        query.append("       ,after_value              = ?                            ");
        query.append("       ,after_diff_value         = ?                            ");
        query.append("       ,remark                   = ?                            ");
        query.append("WHERE comp_no    			= ?                                   ");
        query.append("  AND wkor_id    			= ?                                   ");
        query.append("  AND wocalibetcvalue_id	= ?                                   ");
        
        objects = new Object[] {
        		convertString(map.get("setValue"))
        		,convertString(map.get("basisValue"))
        		,convertString(map.get("beforeValue"))
        		,convertString(map.get("beforeDiffValue"))
        		,convertString(map.get("afterValue"))
        		,convertString(map.get("afterDiffValue"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woCalibEtcValueId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	

	public List findCalStdEqList(Map map) throws Exception
    {
		String compNo 				= String.valueOf(map.get("compNo"));
    	String wkorId 				= String.valueOf(map.get("wkorId"));
    	String equipId 				= String.valueOf(map.get("equipId"));
    	String woCalibStdEqId 		= String.valueOf(map.get("woCalibStdEqId"));
    	String lang 				= String.valueOf(map.get("lang"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("         a.wocalibstdeq_id id							");
        query.append("         ,a.comp_no comp_no							");
        query.append("         ,a.wocalibstdeq_id wocalibstdeq_id			");
        query.append("         ,a.wkor_id wkor_id							");
        query.append("         ,a.calib_wkor_id calib_wkor_id				");
        query.append("         ,a.wo_no wo_no								");
        query.append("         ,a.equip_id equip_id							");
        query.append("         ,a.description description					");
        query.append("         ,a.serial_no serial_no						");
        query.append("         ,a.next_plan_date next_plan_date				");
        query.append("         ,a.sopdoc_no sopdoc_no						");
        query.append("			,(SELECT SFACODE_TO_DESC(a.sopdoc_no,'CALIB_SOPDOC_NO','USR','"+compNo+"','"+lang+"') FROM DUAL)	sopdoc_desc	");
        query.append("FROM TAWOCALIBSTDEQ a									");
        query.append("WHERE 1=1												");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.wkor_id",wkorId);
        query.getAndQuery("a.equip_id",equipId);
        query.getAndQuery("a.wocalibstdeq_id",woCalibStdEqId);
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int deleteCalStdEqList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOCALIBSTDEQ	");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wocalibstdeq_id", convertString(map.get("woCalibStdEqId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	public int insertCalStdEqList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("INSERT INTO TAWOCALIBSTDEQ (												");
        query.append("       comp_no, wocalibstdeq_id, wkor_id, calib_wkor_id					");
        query.append("       , wo_no, equip_id, description, serial_no							");
        query.append("       , next_plan_date, sopdoc_no										");
        query.append("       )																	");
        query.append("VALUES(																	");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?																	");
        query.append("      )																	");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woCalibStdEqId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("calibWkorId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("description"))
        		,convertString(map.get("serialNo"))
        		,convertString(map.get("nextPlanDate"))
        		,convertString(map.get("sopDocNo"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCalStdEqList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOCALIBSTDEQ SET                                ");
        query.append("       calib_wkor_id      = ?                            ");
        query.append("       ,equip_id          = ?                            ");
        query.append("       ,description       = ?                            ");
        query.append("       ,sopdoc_no         = ?                            ");
        query.append("WHERE comp_no    			= ?                            ");
        query.append("  AND wkor_id    			= ?                            ");
        query.append("  AND wocalibstdeq_id     = ?                            ");
        
        objects = new Object[] {
        		convertString(map.get("calibWkorId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("description"))
        		,convertString(map.get("sopDocNo"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woCalibStdEqId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCalStdEqNextDateList(Map map) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOCALIBSTDEQ SET                             ");
		query.append("       next_plan_date     =                           ");
		
		query.append("	(select min(sched_date) 							");
		query.append("	from tapmsched x inner join tapmlst y				");
		query.append("	on x.comp_no = y.comp_no							");
		query.append("	and x.pm_id = y.pm_id								");
		query.append("	inner join tapmequip z								");
		query.append("	on y.comp_no = z.comp_no							");
		query.append("	and y.pm_id = z.pm_id								");
		query.append("	where 1=1											");
		query.append("	and x.comp_no = ?									");
		query.append("	and y.wo_type='PMC'									");
		query.append("	and z.equip_id=?									");
		query.append("	and sched_date >=?									");
    	query.append("  AND x.pmsched_status IN ('IRWDA','S','PRW')				");
		query.append("	)													");
		
		query.append("WHERE comp_no    			= ?                            ");
		query.append("  AND wocalibstdeq_id     = ?                            ");
		
		
		
		objects = new Object[] {
				convertString(map.get("compNo"))
				,convertString(map.get("equipId"))
				,DateUtil.getDate()
				,convertString(map.get("compNo"))
				,convertString(map.get("woCalibStdEqId"))
		};
		
		return getJdbcTemplate().update(query.toString(), objects);
	}

	public List findCalGnlValueList(Map map) throws Exception
    {
		String compNo 				= String.valueOf(map.get("compNo"));
    	String wkorId 				= String.valueOf(map.get("wkorId"));
    	String lang 				= String.valueOf(map.get("lang"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("         a.wocalibgnlvalue_id id						");
        query.append("         ,a.comp_no comp_no							");
        query.append("         ,a.wocalibgnlvalue_id wocalibgnlvalue_id		");
        query.append("         ,a.wkor_id wkor_id							");
        query.append("         ,a.calib_point_type calib_point_type			");
        query.append("		   ,(SELECT SFACODE_TO_DESC(a.calib_point_type,'CALIB_POINT_TYPE','SYS','','"+lang+"') FROM DUAL)	AS CALIB_POINT_TYPE_DESC	");
        query.append("         ,a.calib_point calib_point					");
        query.append("         ,a.allow_value allow_value					");
        query.append("         ,a.asf_std_value asf_std_value				");
        query.append("         ,a.asf_cal_value asf_cal_value				");
        query.append("         ,a.asf_diff_value asf_diff_value				");
        query.append("         ,a.asl_std_value asl_std_value				");
        query.append("         ,a.asl_cal_value asl_cal_value				");
        query.append("         ,a.asl_diff_value asl_diff_value				");
        query.append("         ,a.ord_no ord_no								");
        query.append("         ,a.remark remark								");
        query.append("FROM TAWOCALIBGNLVALUE a								");
        query.append("WHERE 1=1												");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.wkor_id",wkorId);
        query.append("ORDER bY a.calib_point_type, a.wocalibgnlvalue_id		");
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int deleteCalGnlValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOCALIBGNLVALUE	");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wocalibgnlvalue_id", convertString(map.get("woCalibGnlValueId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	public int insertCalGnlValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("INSERT INTO TAWOCALIBGNLVALUE (											");
        query.append("       comp_no, wocalibgnlvalue_id, wkor_id, calib_point_type				");
        query.append("       , calib_point, allow_value, asf_std_value, asf_cal_value			");
        query.append("       , asf_diff_value, asl_std_value, asl_cal_value, asl_diff_value		");
        query.append("       , ord_no, remark													");
        query.append("       )																	");
        query.append("VALUES(																	");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?																	");
        query.append("      )																	");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woCalibGnlValueId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("calibPointType"))
        		,convertString(map.get("calibPoint"))
        		,convertString(map.get("allowValue"))
        		,convertString(map.get("asfStdValue"))
        		,convertString(map.get("asfCalValue"))
        		,convertString(map.get("asfDiffValue"))
        		,convertString(map.get("aslStdValue"))
        		,convertString(map.get("aslCalValue"))
        		,convertString(map.get("aslDiffValue"))
        		,convertString(map.get("ordNo"))
        		,convertString(map.get("remark"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCalGnlValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOCALIBGNLVALUE SET                            ");
        query.append("       calib_point_type  = ?                            ");
        query.append("       ,calib_point      = ?                            ");
        query.append("       ,allow_value      = ?                            ");
        query.append("       ,asf_std_value    = ?                            ");
        query.append("       ,asf_cal_value    = ?                            ");
        query.append("       ,asf_diff_value   = ?                            ");
        query.append("       ,asl_std_value    = ?                            ");
        query.append("       ,asl_cal_value    = ?                            ");
        query.append("       ,asl_diff_value   = ?                            ");
        query.append("       ,ord_no           = ?                            ");
        query.append("       ,remark           = ?                            ");
        query.append("WHERE comp_no    		   = ?                            ");
        query.append("  AND wkor_id    		   = ?                            ");
        query.append("  AND wocalibgnlvalue_id = ?                            ");
        
        objects = new Object[] {
        		convertString(map.get("calibPointType"))
        		,convertString(map.get("calibPoint"))
        		,convertString(map.get("allowValue"))
        		,convertString(map.get("asfStdValue"))
        		,convertString(map.get("asfCalValue"))
        		,convertString(map.get("asfDiffValue"))
        		,convertString(map.get("aslStdValue"))
        		,convertString(map.get("aslCalValue"))
        		,convertString(map.get("aslDiffValue"))
        		,convertString(map.get("ordNo"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woCalibGnlValueId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	

	public List findCalSclValueList(Map map) throws Exception
    {
		String compNo 				= String.valueOf(map.get("compNo"));
    	String wkorId 				= String.valueOf(map.get("wkorId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("         a.wocalibsclvalue_id id						");
        query.append("         ,a.comp_no comp_no							");
        query.append("         ,a.wocalibsclvalue_id wocalibsclvalue_id		");
        query.append("         ,a.wkor_id wkor_id							");
        query.append("         ,a.li0_value li0_value						");
        query.append("         ,a.li25_value li25_value						");
        query.append("         ,a.li50_value li50_value						");
        query.append("         ,a.li75_value li75_value						");
        query.append("         ,a.li100_value li100_value					");
        query.append("         ,a.ld75_value ld75_value						");
        query.append("         ,a.ld50_value ld50_value						");
        query.append("         ,a.ld25_value ld25_value						");
        query.append("         ,a.ld0_value ld0_value						");
        query.append("         ,a.ecntr_value ecntr_value					");
        query.append("         ,a.ebef_value ebef_value						");
        query.append("         ,a.eaft_value eaft_value						");
        query.append("         ,a.elft_value elft_value						");
        query.append("         ,a.erig_value erig_value						");
        query.append("         ,a.degree1 degree1							");
        query.append("         ,a.degree2 degree2							");
        query.append("         ,a.degree3 degree3							");
        query.append("         ,a.remark remark								");
        query.append("FROM TAWOCALIBSCLVALUE a								");
        query.append("WHERE 1=1												");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.wkor_id",wkorId);
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int deleteCalSclValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOCALIBSCLVALUE	");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wocalibsclvalue_id", convertString(map.get("woCalibSclValueId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	public int insertCalSclValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("INSERT INTO TAWOCALIBSCLVALUE (											");
        query.append("       comp_no, wocalibsclvalue_id, wkor_id, li0_value					");
        query.append("       , li25_value, li50_value, li75_value, li100_value					");
        query.append("       , ld75_value, ld50_value, ld25_value, ld0_value					");
        query.append("       , ecntr_value, ebef_value, eaft_value, elft_value					");
        query.append("       , erig_value, degree1, degree2, degree3							");
        query.append("       , remark															");
        query.append("       )																	");
        query.append("VALUES(																	");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?,?,?,?,															");
        query.append("      ?																	");
        query.append("      )																	");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woCalibSclValueId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("li0Value"))
        		,convertString(map.get("li25Value"))
        		,convertString(map.get("li50Value"))
        		,convertString(map.get("li75Value"))
        		,convertString(map.get("li100Value"))
        		,convertString(map.get("ld75Value"))
        		,convertString(map.get("ld50Value"))
        		,convertString(map.get("ld25Value"))
        		,convertString(map.get("ld0Value"))
        		,convertString(map.get("ecntrValue"))
        		,convertString(map.get("ebefValue"))
        		,convertString(map.get("eaftValue"))
        		,convertString(map.get("elftValue"))
        		,convertString(map.get("erigValue"))
        		,convertString(map.get("degree1"))
        		,convertString(map.get("degree2"))
        		,convertString(map.get("degree3"))
        		,convertString(map.get("remark"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateCalSclValueList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOCALIBSCLVALUE SET                          ");
        query.append("       li0_value       = ?                            ");
        query.append("       ,li25_value     = ?                            ");
        query.append("       ,li50_value     = ?                            ");
        query.append("       ,li75_value     = ?                            ");
        query.append("       ,li100_value    = ?                            ");
        query.append("       ,ld75_value     = ?                            ");
        query.append("       ,ld50_value     = ?                            ");
        query.append("       ,ld25_value     = ?                            ");
        query.append("       ,ld0_value      = ?                            ");
        query.append("       ,ecntr_value    = ?                            ");
        query.append("       ,ebef_value     = ?                            ");
        query.append("       ,eaft_value     = ?                            ");
        query.append("       ,elft_value     = ?                            ");
        query.append("       ,erig_value     = ?                            ");
        query.append("       ,degree1        = ?                            ");
        query.append("       ,degree2        = ?                            ");
        query.append("       ,degree3        = ?                            ");
        query.append("       ,remark         = ?                            ");
        query.append("WHERE comp_no    		   = ?                          ");
        query.append("  AND wkor_id    		   = ?                          ");
        query.append("  AND wocalibsclvalue_id = ?                          ");
        
        objects = new Object[] {
        		
        		convertString(map.get("li0Value"))
        		,convertString(map.get("li25Value"))
        		,convertString(map.get("li50Value"))
        		,convertString(map.get("li75Value"))
        		,convertString(map.get("li100Value"))
        		,convertString(map.get("ld75Value"))
        		,convertString(map.get("ld50Value"))
        		,convertString(map.get("ld25Value"))
        		,convertString(map.get("ld0Value"))
        		,convertString(map.get("ecntrValue"))
        		,convertString(map.get("ebefValue"))
        		,convertString(map.get("eaftValue"))
        		,convertString(map.get("elftValue"))
        		,convertString(map.get("erigValue"))
        		,convertString(map.get("degree1"))
        		,convertString(map.get("degree2"))
        		,convertString(map.get("degree3"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woCalibSclValueId"))
        		
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}