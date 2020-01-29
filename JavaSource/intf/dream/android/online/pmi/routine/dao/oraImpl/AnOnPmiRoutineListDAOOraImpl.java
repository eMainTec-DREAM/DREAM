package intf.dream.android.online.pmi.routine.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.online.pmi.routine.dao.AnOnPmiRoutineListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnPmiRoutineListDAOTarget"
 * @spring.txbn id="anOnPmiRoutineListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnPmiRoutineListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnPmiRoutineListDAO
{
	public List findPmiRoutineList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String pmId = String.valueOf(map.get("pmId"));
    	String pminslistId = String.valueOf(map.get("pminslistId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
     	String rootPage = String.valueOf(map.get("rootPage"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH eqInfo AS (																								");
        query.append("		SELECT a.equip_id																							");
        query.append("				,a.pm_id																							");
        query.append("				,b.item_no																							");
        query.append("				,a.comp_no																							");
        query.append("				,b.sub_mng_id																						");
        query.append("				,b.eqctg_id																							");
        query.append("				,b.eqloc_id																							");
        query.append("              ,(SELECT x.full_desc FROM TAEQLOC x WHERE x.comp_no = b.comp_no AND x.eqloc_id = b.eqloc_id) eqloc_desc	");
        query.append("              ,(SELECT x.description FROM TAEQLOC x WHERE x.comp_no = b.comp_no AND x.eqloc_id = b.eqloc_id) eqloc_name	");
        query.append("				,b.old_eq_no																						");
        query.append("				,b.description																						");
        query.append("				,b.eqctg_type																						");
        query.append("FROM  TAPMEQUIP a INNER JOIN TAEQUIPMENT b																		");
        query.append("ON a.comp_no = b.comp_no																							");
        query.append("AND a.equip_id = b.equip_id																						");
        query.append("WHERE 1=1 ");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("AND b.is_deleted='N'																								");
        query.append("AND b.is_last_version='Y'																							");
        query.getStringEqualQuery("b.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("b.eqloc_id", eqlocId, "", compNo);
		query.getLikeQuery("b.description||b.item_no", equipDesc);
        query.append("		)																											");
        query.append("SELECT a.comp_no																						AS COMPNO	");
    	query.append("		,a.pminssched_id																			AS PMINSSCHEDID	");
    	query.append("		,a.pminslist_id																				AS PMINSLISTID	");
    	query.append("		,b.description																					AS WODESC	");
    	query.append("		,a.pminslist_id																					AS WONO		");
    	query.append("		,b.wo_type																						AS WOTYPE	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.wo_type,'WO_TYPE','SYS','','"+lang+"') FROM DUAL)									AS WOTYPEDESC	");
    	query.append("		,b.pm_type																						AS PMTYPE	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.pm_type,'PM_TYPE','SYS','','"+lang+"') FROM DUAL)									AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.pmsched_status																			AS WOSTATUS		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.pmsched_status,'PMSCHED_STATUS','SYS','','"+lang+"') FROM DUAL)						AS WOSTATUSDESC	");
    	query.append("		,b.schedule_type																			AS SCHEDULETYPE	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.schedule_type,'SCHEDULE_TYPE','SYS','','"+lang+"') FROM DUAL)					AS SCHEDULETYPEDESC	");
    	query.append("		,b.period_type																				AS PERIODTYPE	");
        query.append("		,(SELECT SFACODE_TO_DESC(b.period_type,'PERIOD_TYPE','SYS','','"+lang+"') FROM DUAL)						AS PERIODTYPEDESC	");
    	query.append("		,b.cycle																						AS CYCLE	");
    	query.append("		,a.equip_id 																					AS EQUIPID	");
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.equip_id = a.equip_id and rownum=1) 								AS EQUIPNO	");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.equip_id = a.equip_id and rownum=1) 						AS EQUIPDESC	");
    	query.append("		,(SELECT count(*) FROM TAPMINSPOINT x WHERE x.comp_no = a.comp_no AND x.pminslist_id = a.pminslist_id AND x.is_saved = 'Y') AS COMPLETEDCNT	");
    	query.append("		,(SELECT count(*) FROM TAPMPOINT x WHERE x.comp_no = a.comp_no AND x.pm_id = a.pm_id AND x.is_deleted = 'N' ) AS POINTTOTALCNT		");
    	query.append("		,'' 																						AS STARTDATE	");
        query.append("		,'' 																						AS STARTTIME	");
        query.append("		,'' 																						AS ENDDATE		");
        query.append(" 		,'' 																						AS ENDTIME		");
    	query.append("		,b.pm_id																					AS PMID			");
    	query.append("		,a.dept_id																					AS DEPTID		");
    	query.append("		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
    	query.append("		,a.emp_id																					AS EMPID		");
    	query.append("		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
    	query.append("		,a.MEASURE_TIME																				AS MEASURE_TIME	");
    	query.append("		,(SELECT x.eqloc_name FROM eqInfo x WHERE x.equip_id = a.equip_id and rownum=1) 			AS EQLOCNAME	");
    	query.append("FROM TAPMINSLIST a, TAPMLST b																						");
    	query.append("WHERE a.comp_no = b.comp_no																						");
    	query.append("AND a.pm_id = b.pm_id																								");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND b.plant = '"+plant+"' ");
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.pm_id", pmId);
    	query.getStringEqualQuery("a.pminslist_id", pminslistId);
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "RINS");
    	if(!"BeeWoDailyRoutineListFragment".equals(rootPage)|| "null".equals(rootPage)){
        	query.append("  and a.WKOR_DATE >= '"+startDate+"'																												");
        	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	}
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
        }
    	if (!deptId.equals("null") && !"".equals(deptId))
        {
        	query.append("   and a.dept_id in ( select x.dept_id									");
        	query.append("                             from tadept x								");
        	query.append("                             where 1=1									");
        	query.getStringEqualQuery("x.comp_no", compNo);
        	query.append("                              start with x.dept_id = "+deptId+"			");
        	query.append("                              connect by prior x.dept_id = x.p_dept_id	");
        	query.append("                          )												");
        }
    	if (!wkctrId.equals("null") && !"".equals(wkctrId))
        {
        	query.append("   and NVL(a.wkctr_id,'1') in ( select NVL2(b.wkctr_id, x.wkctr_id,'1')	");
        	query.append("                             from TAWKCTR x								");
        	query.append("                             where 1=1									");
        	query.getStringEqualQuery("x.comp_no", compNo);
        	query.append("                              start with x.wkctr_id = "+wkctrId+"			");
        	query.append("                              connect by prior x.wkctr_id = x.p_wkctr_id	");
        	query.append("                          )												");
        }
        return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public List findPointList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String pminslistId = String.valueOf(map.get("pminslistId"));
		String pmId = String.valueOf(map.get("pmId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 																	");
        query.append("		x.comp_no 									AS COMPNO				");
        query.append("		,y.pminslist_id 							AS PMINSLISTID			");
        query.append("		,y.pminspoint_id 							AS PMINSPOINTID			");
        query.append("		,x.pm_point_id								AS PMPOINTID			");
        query.append("		,(SELECT a.full_desc												");
        query.append("			FROM TAEQASMB a													");
        query.append("			WHERE a.eqasmb_id = x.eqasmb_id 								");
        query.append("			AND a.comp_no = x.comp_no ) 			AS EQASMBDESC			");
        query.append("		,y.pm_point_rslt_status 					AS PMPOINTRSLTSTATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(y.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+lang+"') FROM DUAL)	AS PMPOINTRSLTSTATUSDESC	");
        query.append("		,y.result_value 							AS RESULTVALUE			");
        query.append("		,x.step_num									AS STEPNUM				");
        query.append("		,x.check_point 								AS CHECK_POINT			");
        query.append("		,x.check_method 							AS CHECKMETHOD			");
        query.append("		,x.fit_basis 								AS FITBASIS				");
        query.append("		,x.check_type 								AS CHECKTYPE			");
        query.append("		,x.check_min 								AS CHECKMIN				");
        query.append("		,x.check_basis_val 							AS CHECKBASISVAL		");
        query.append("		,x.check_max 								AS CHECKMAX				");
        query.append("		,x.uom 										AS UOM					");
        query.append("		,DECODE(y.pminspoint_id,null,'P','C')			AS STATUS				");
        query.append("		,(SELECT SFACODE_TO_DESC(DECODE(y.pminspoint_id,null,'P','C'),'WO_STATUS','SYS','','"+lang+"') FROM DUAL)	AS STATUSDESC	");
        query.append("		,y.remark 									AS REMARK				");
    	query.append("		,(SELECT count(1)																							");
    	query.append("				FROM TADOCDATA aa INNER JOIN TADOCUMENT bb															");
    	query.append("				ON aa.comp_no = bb.comp_no																			");
    	query.append("				AND aa.doc_id = bb.doc_id																			");
    	query.append("				WHERE 1=1																							");
    	query.append("				AND aa.file_ext IN ((SELECT TRIM(REGEXP_SUBSTR(value$, '[^,]+', 1, LEVEL))							");
    	query.append("						FROM (SELECT value$ FROM TACONFIG WHERE comp_no = '"+compNo+"' AND NAME='IMG_FILE_TYPE')			");
    	query.append("						CONNECT BY  INSTR(value$, ',', 1, LEVEL - 1) > 0))											");
    	query.getStringEqualQuery("aa.comp_no", compNo);
    	query.append("				AND aa.doc_id IN (																					");
    	query.append("						SELECT doc_id																				");
    	query.append("						FROM TAOBJDOC																				");
    	query.append("						WHERE 1=1																					");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", "PM_ROUTINE_POINT");
    	query.append("						AND object_id = y.pminspoint_id																");
    	query.append("			))																							AS PHOTOCNT	");
        query.append("FROM TAPMPOINT x LEFT OUTER JOIN 											");
        query.append("(SELECT * FROM TAPMINSPOINT WHERE 1=1										");
        query.getAndQuery("pminslist_id",pminslistId);
        query.append(") y																		");
        query.append("ON x.comp_no = y.comp_no													");
        query.append("AND x.pm_point_id = y.pm_point_id											");
        query.append("WHERE 1=1																	");
        query.append("AND x.is_deleted='N'												        ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pm_id",pmId);
        query.append("ORDER BY case when y.pm_point_rslt_status is null then 1  else 2 end,x.step_num	");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
	public List findPointHistList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String pmPointId = String.valueOf(map.get("pmPointId"));
		String pminslistId = String.valueOf(map.get("pminslistId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 																	");
        query.append("		x.comp_no 									AS COMPNO				");
        query.append("		,x.pminslist_id 							AS PMINSLISTID			");
        query.append("		,x.pminspoint_id 							AS PMINSPOINTID			");
        query.append("		,y.pm_point_id								AS PMPOINTID			");
        query.append("		,(SELECT a.full_desc												");
        query.append("			FROM TAEQASMB a													");
        query.append("			WHERE a.eqasmb_id = y.eqasmb_id 								");
        query.append("			AND a.comp_no = y.comp_no ) 			AS EQASMBDESC			");
        query.append("		,x.pm_point_rslt_status 					AS PMPOINTRSLTSTATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+lang+"') FROM DUAL)	AS PMPOINTRSLTSTATUSDESC	");
        query.append("		,x.result_value 							AS RESULTVALUE			");
        query.append("		,y.step_num									AS STEPNUM				");
        query.append("		,y.check_point 								AS CHECK_POINT			");
        query.append("		,y.check_method 							AS CHECKMETHOD			");
        query.append("		,y.fit_basis 								AS FITBASIS				");
        query.append("		,y.check_type 								AS CHECKTYPE			");
        query.append("		,y.check_min 								AS CHECKMIN				");
        query.append("		,y.check_basis_val 							AS CHECKBASISVAL		");
        query.append("		,y.check_max 								AS CHECKMAX				");
        query.append("		,y.uom 										AS UOM					");
        query.append("		,DECODE(x.pminspoint_id,null,'P','C')		AS STATUS				");
        query.append("		,(SELECT SFACODE_TO_DESC(DECODE(x.pminspoint_id,null,'P','C'),'WO_STATUS','SYS','','"+lang+"') FROM DUAL)	AS STATUSDESC	");
        query.append("		,x.remark 									AS REMARK				");
        query.append("		,(SELECT a.dept_id													");
        query.append("			FROM TAPMINSLIST a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pminslist_id = x.pminslist_id) AS DEPTID					");
        query.append("		,(SELECT (SELECT b.description 										");
        query.append("					FROM TADEPT b											");
        query.append("					WHERE b.comp_no = a.comp_no								");
        query.append("					AND b.dept_id = a.dept_id) DEPTDESC						");
        query.append("			FROM TAPMINSLIST a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pminslist_id = x.pminslist_id) AS DEPTDESC				");
        query.append("		,(SELECT a.emp_id													");
        query.append("			FROM TAPMINSLIST a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pminslist_id = x.pminslist_id) AS EMPID					");
        query.append("		,(SELECT (SELECT b.emp_name 										");
        query.append("					FROM TAEMP b											");
        query.append("					WHERE b.comp_no = a.comp_no								");
        query.append("					AND b.emp_id = a.emp_id) EMPDESC						");
        query.append("			FROM TAPMINSLIST a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pminslist_id = x.pminslist_id) AS EMPDESC					");
        query.append("		,(SELECT a.wkor_date												");
        query.append("			FROM TAPMINSLIST a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pminslist_id = x.pminslist_id) AS WKORDATE				");
        query.append("FROM TAPMINSPOINT x INNER JOIN 											");
        query.append("	TAPMPOINT y																");
        query.append("ON x.comp_no = y.comp_no													");
        query.append("AND x.pm_point_id = y.pm_point_id											");
        query.append("WHERE 1=1																	");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("y.pm_point_id",pmPointId);
        query.getAndQuery("x.pminslist_id",pminslistId);
        query.append("AND x.pminslist_id IN (SELECT a.pminslist_id 								");
        query.append("							FROM TAPMINSLIST a								");
        query.append("							WHERE 1=1										");
        query.getAndQuery("a.comp_no",compNo);
        query.getStringEqualQuery("a.pmsched_status", "C");
        query.append("							)												");
        query.append("ORDER BY y.step_num														");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
	public List findWoPointCount(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String pminslistId = String.valueOf(map.get("pminslistId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 																	");
        query.append("		count(*) AS count													");
        query.append("FROM TAPMINSPOINT															");
        query.append("WHERE 1=1																	");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pminslist_id",pminslistId);
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findStatus(Map map) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pminslistId = convertString(map.get("pminslistId"));
		String pmId   = convertString(map.get("pmId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 												");
        query.append("		CASE	(SELECT count(1)  						");
        query.append("				FROM TAPMPOINT							");
        query.append("				WHERE 1=1								");
        query.append("				AND comp_no = '"+compNo+"'				");
        query.append("				AND is_deleted = 'N'					");
        query.append("				AND pm_id = "+pmId+")					");
        query.append("			WHEN	(SELECT count(1)  					");
        query.append("				FROM TAPMINSPOINT						");
        query.append("				WHERE 1=1								");
        query.append("				AND comp_no = '"+compNo+"'				");
        query.append("				AND pminslist_id = "+pminslistId+"		");
        query.append("				AND is_saved = 'Y'						");
        query.append("				AND pm_point_rslt_status is not null)	");
        query.append("			THEN 'C'									");
        query.append("			ELSE 'P'									");
        query.append("			END STATUS									");
        query.append("FROM DUAL												");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public int insertPoint(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAPMINSPOINT (                                                  ");
        query.append("       COMP_NO, PMINSPOINT_ID, PMINSLIST_ID,PMINSSCHED_ID, PM_POINT_ID,     ");
        query.append("       PM_POINT_RSLT_STATUS, RESULT_VALUE, REMARK,IS_SAVED                  ");
        query.append("       )                                                                    ");
        query.append("VALUES                                                                      ");
        query.append("(                                                                           ");
        query.append("      ?,?,?,?,                                                              ");
        query.append("      ?,?,?,?,?                                                             ");
        query.append(")                                                                           ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("pminspointId"))
        		,convertString(map.get("pminslistId"))
        		,convertString(map.get("pminsschedId"))
        		,convertString(map.get("pmPointId"))
        		,convertString(map.get("resultStatus"))
        		,convertString(map.get("resultVal"))
        		,convertString(map.get("remark"))
        		,"Y"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updatePoint(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPMINSPOINT SET                                    ");
        query.append("       PM_POINT_RSLT_STATUS = ?                            ");
        query.append("       ,RESULT_VALUE        = ?                            ");
        query.append("       ,REMARK              = ?                            ");
        query.append("       ,IS_SAVED            = ?                            ");
        query.append("WHERE COMP_NO    = ?                                       ");
        query.append("  AND PMINSLIST_ID    = ?                                  ");
        query.append("  AND PMINSPOINT_ID = ?                                    ");
        query.append("  AND PM_POINT_ID = ?                                      ");
        
        objects = new Object[] {
        		convertString(map.get("resultStatus"))
        		,convertString(map.get("resultVal"))
        		,convertString(map.get("remark"))
        		,"Y"
        		,convertString(map.get("compNo"))
        		,convertString(map.get("pminslistId"))
        		,convertString(map.get("pminspointId"))
        		,convertString(map.get("pmPointId"))
        };

        return getJdbcTemplate().update(query.toString(), objects);
    }
public int mergeAbnormalRslt(Map map) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("MERGE INTO TAWONGPOINT a																						");
    	query.append("USING(	SELECT 	? COMP_NO,																					");
    	query.append("					? PMINSLIST_ID,																				");
    	query.append("					? PM_POINT_ID,																				");
    	query.append("					? PM_POINT_RSLT_STATUS,																		");
    	query.append("					? RESULT_VALUE,																				");
    	query.append("					? REMARK																					");
    	query.append("			FROM DUAL) b																						");
    	query.append("ON(	a.COMP_NO = b.COMP_NO																					");
    	query.append("	AND a.PMINSLIST_ID = b.PMINSLIST_ID																			");
    	query.append("	AND a.PM_POINT_ID = b.PM_POINT_ID	)																		");
    	query.append("WHEN MATCHED THEN																								");
    	query.append("	UPDATE SET 	a.pm_point_rslt_status = b.PM_POINT_RSLT_STATUS,												");
    	query.append("				a.pm_point_rep_status = b.PM_POINT_RSLT_STATUS,													");
    	query.append("				a.result_value = b.RESULT_VALUE,																");
    	query.append("				a.remark = b.REMARK																				");
    	query.append("WHEN NOT MATCHED THEN																							");
    	query.append("	INSERT (a.comp_no,	a.wongpoint_id,			a.pm_point_id,	a.pmi_type,		a.pminspoint_id,				");
    	query.append("			a.pminslist_id,	a.pm_point_rslt_status,	a.pm_point_rep_status,	a.actual_date,	a.result_value,	a.remark				)	");
    	query.append("	VALUES (b.comp_no,	sqawongpoint_id.nextval,b.pm_point_id,	?,				?,								");
    	query.append("			b.pminslist_id,	b.pm_point_rslt_status,	b.pm_point_rslt_status,	to_char(sysdate,'yyyymmdd'),b.result_value, b.remark	)	");
    	
        Object[] objects = new Object[] {
                convertString(map.get("compNo"))
                ,convertString(map.get("pminslistId"))
                ,convertString(map.get("pmPointId"))
                ,convertString(map.get("resultStatus"))
                ,convertString(map.get("resultVal"))
                ,convertString(map.get("remark"))
                ,"RINS"
                ,convertString(map.get("pminspointId"))
        };
        
    	return getJdbcTemplate().update(query.toString(), objects);

	}
	public int updateStartDate(Map map) throws Exception
    {
		
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPMINSLIST SET									");
        query.append("       start_date		= to_char(sysdate,'YYYYMMDD')		");
        query.append("       ,start_time	= to_char(sysdate,'HH24MI')			");
        query.append("WHERE COMP_NO	= ?											");
        query.append("  AND pminslist_id	= ?									");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("pminslistId"))
        };

        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateEndDate(Map map) throws Exception
    {
		
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPMINSLIST SET									");
        query.append("       end_date		= to_char(sysdate,'YYYYMMDD')		");
        query.append("       ,end_time		= to_char(sysdate,'HH24MI')			");
        query.append("       ,work_time		= CASE length(start_date||start_time) WHEN 12 THEN TO_CHAR(CEIL((sysdate - TO_DATE(start_date||start_time,'YYYYMMDDHH24MISS') ) *24*60))		");
        query.append("       					ELSE ''  END					");
        query.append("WHERE COMP_NO	= ?											");
        query.append("  AND pminslist_id	= ?									");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("pminslistId"))
        };

        return getJdbcTemplate().update(query.toString(), objects);
    }
	public String getWopointCount(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pminslistId = convertString(map.get("pminslistId"));
		String pmId   = convertString(map.get("pmId"));
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)				");
        query.append("FROM TAPMINSPOINT x			");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.is_saved","Y");
        query.getAndQuery("x.pminslist_id",pminslistId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
	public String getPmpointCount(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pminslistId = convertString(map.get("pminslistId"));
		String pmId   = convertString(map.get("pmId"));
		
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(*)				");
        query.append("FROM TAPMPOINT x				");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pm_id",pmId);
        query.getAndQuery("x.is_deleted","N");
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
	
	public void updatePmSched(Map map) 
	{
		QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSSCHED	 SET							");
    	query.append("	pmsched_status		= ?								");
    	query.append("	,actual_date		= ?								");
    	query.append("	,actual_time		= ?								");
    	query.append("	,check_by			= ?								");
    	query.append("WHERE pminssched_id	= ?								");
    	query.append("  AND comp_no			= ?								");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("woStatus")),
    			"".equals(convertString(map.get("actualDate")))?convertString(map.get("startDate")):convertString(map.get("actualDate")),
    			"".equals(convertString(map.get("actualTime")))?convertString(map.get("startTime")):convertString(map.get("actualTime")),
                convertString(map.get("userId")),
                convertString(map.get("pminsschedId")),
                convertString(map.get("compNo"))
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
	}
	public void updatePmPointSchedStatus(Map map) 
	{
		QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMINSPOINT	 SET							");
    	query.append("	pmsched_status		= ?								");
    	query.append("WHERE pminssched_id	= ?								");
    	query.append("  AND comp_no			= ?								");
    	
    	Object[] objects = new Object[] {
                convertString(map.get("woStatus")),
                convertString(map.get("pminsschedId")),
                convertString(map.get("compNo"))
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), objects);
	}

    public int updatePminsList(Map map)
    {
        QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAPMINSLIST SET                      ");
        query.append("  pmsched_status    = ?,                    ");
        query.append("  start_date        = ?,                    ");
        query.append("  start_time        = ?,                    ");
        query.append("  end_date          = ?,                    ");
        query.append("  end_time          = ?,                    ");
        query.append("  work_time         = ?,                    ");
        query.append("  shift_type        = ?,                    ");
        query.append("  emp_id            = (SELECT emp_id FROM TAUSER WHERE user_id = ? AND comp_no = ?),                    ");
        query.append("  close_id          = (SELECT emp_id FROM TAUSER WHERE user_id = ? AND comp_no = ?),                    ");
        query.append("  close_date        = ?,                    ");
        query.append("  upd_date          = ?,                    ");
        query.append("  upd_by            = ?                     ");
        query.append("WHERE pminslist_id  = ?                     ");
        query.append("  AND comp_no       = ?                     ");

        
        objects = new Object[] {
        		convertString(map.get("woStatus")),
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
        		convertString(map.get("endDate")),
        		convertString(map.get("endTime")),
        		convertString(map.get("workTime")),
                convertString(map.get("shiftType")),
                convertString(map.get("userId")),
                convertString(map.get("compNo")),
                "Y".equals(convertString(map.get("isConfirm")))?convertString(map.get("userId")):"0",
                convertString(map.get("compNo")),
                "Y".equals(convertString(map.get("isConfirm")))?DateUtil.getDate():"",
                DateUtil.getDateTime(),
                convertString(map.get("userId")),
                convertString(map.get("pminslistId")),
                convertString(map.get("compNo"))
        };

        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    

	public int executeSP_PM_UPDATE_LASTCPLT_DATE(Map map)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = convertString(map.get("compNo"));
        String pmId = convertString(map.get("pmId"));
        String pminsschedId = convertString(map.get("pminsschedId"));

        query.append("begin																			");
        query.append("SP_PM_UPDATE_LASTCPLT_DATE('"+compNo+"', '"+pmId+"','"+pminsschedId+"' );		");
        query.append("end;																			");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
}