package intf.dream.android.online.pmi.patrol.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.online.pmi.patrol.dao.AnOnPmiPatrolListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnPmiPatrolListDAOTarget"
 * @spring.txbn id="anOnPmiPatrolListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnPmiPatrolListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnPmiPatrolListDAO
{
	public List findPmiPatrolList(Map map) throws Exception
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
    	String pmptrlrsltlistId = String.valueOf(map.get("pmptrlrsltlistId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("WITH eqInfo AS (																								");
        query.append("		SELECT a.equip_id																							");
        query.append("				,a.pm_id																							");
        query.append("				,b.item_no																							");
        query.append("				,a.comp_no																							");
        query.append("				,b.sub_mng_id																						");
        query.append("				,b.eqctg_id																							");
        query.append("				,b.eqloc_id																							");
        query.append("				,b.old_eq_no																						");
        query.append("				,b.description																						");
        query.append("				,b.eqctg_type																						");
        query.append("FROM  TAPMEQUIP a INNER JOIN TAEQUIPMENT b																		");
        query.append("ON a.comp_no = b.comp_no																							");
        query.append("AND a.equip_id = b.equip_id																						");
        query.append("WHERE 1=1		");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("AND b.is_deleted='N'																						");
        query.append("AND b.is_last_version='Y')																						");
        query.append("SELECT a.comp_no																						AS COMPNO	");
    	query.append("		,a.pmptrlsched_id																		AS PMPTRLSCHEDID	");
    	query.append("		,a.pmptrlrsltlist_id																	AS PMPTRLRSLTLISTID	");
    	query.append("		,b.description																					AS WODESC	");
    	query.append("		,a.pmptrlrsltlist_id																			AS WONO		");
    	query.append("		,b.wo_type																						AS WOTYPE	");
        query.append("		,dbo.SFACODE_TO_DESC(b.wo_type,'WO_TYPE','SYS','','"+lang+"')									AS WOTYPEDESC	");
    	query.append("		,b.pm_type																						AS PMTYPE	");
        query.append("		,dbo.SFACODE_TO_DESC(b.pm_type,'PM_TYPE','SYS','','"+lang+"')									AS PMTYPEDESC	");
    	query.append("		,a.sched_date																				AS WKORDATE		");
    	query.append("		,a.pmsched_status																			AS WOSTATUS		");
        query.append("		,dbo.SFACODE_TO_DESC(a.pmsched_status,'PMSCHED_STATUS','SYS','','"+lang+"')						AS WOSTATUSDESC	");
    	query.append("		,b.schedule_type																			AS SCHEDULETYPE	");
        query.append("		,dbo.SFACODE_TO_DESC(b.schedule_type,'SCHEDULE_TYPE','SYS','','"+lang+"')					AS SCHEDULETYPEDESC	");
    	query.append("		,b.period_type																				AS PERIODTYPE	");
        query.append("		,dbo.SFACODE_TO_DESC(b.period_type,'PERIOD_TYPE','SYS','','"+lang+"')						AS PERIODTYPEDESC	");
    	query.append("		,b.cycle																						AS CYCLE	");
    	query.append("		,''																					AS EQUIPID	");
    	query.append("		,'' 						AS EQUIPNO	");
    	query.append("		,'' 				AS EQUIPDESC	");
    	query.append("		,(SELECT count(*) FROM TAPMPTRLRSLTPOINT x WHERE x.comp_no = a.comp_no AND x.pmptrlrsltlist_id = a.pmptrlrsltlist_id AND x.is_saved = 'Y') AS COMPLETEDCNT	");
    	query.append("		,(SELECT count(*) FROM TAPMPOINT x WHERE x.comp_no = a.comp_no AND x.pm_id = a.pm_id) AS POINTTOTALCNT		");
    	query.append("		,'' 																						AS STARTDATE	");
        query.append("		,'' 																						AS STARTTIME	");
        query.append("		,'' 																						AS ENDDATE		");
        query.append(" 		,'' 																						AS ENDTIME		");
    	query.append("		,b.pm_id																					AS PMID			");
    	query.append("		,b.dept_id																					AS DEPTID		");
    	query.append("		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = b.dept_id) AS DEPTDESC		");
    	query.append("		,b.emp_id																					AS EMPID		");
    	query.append("		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = b.emp_id) 		AS EMPDESC		");
    	query.append("FROM TAPMPTRLSCHED a, TAPMLST b																						");
    	query.append("WHERE a.comp_no = b.comp_no																						");
    	query.append("AND a.pm_id = b.pm_id																								");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND b.plant = '"+plant+"' ");
    	query.getStringEqualQuery("a.pm_id", pmId);
    	query.getStringEqualQuery("a.pmptrlrsltlist_id", pmptrlrsltlistId);
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "PINS");
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("  and a.SCHED_DATE >= '"+startDate+"'																												");
    	query.append("  and a.SCHED_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.append("AND a.pmptrlrsltlist_id IN ( SELECT x.pmptrlrsltlist_id					");
    		query.append("								FROM TAPMPTRLRSLTLIST x						");
    		query.append("								WHERE 1=1									");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getAndQuery("x.emp_id", empId);
    		query.append("								)											");
        }
    	query.getDeptLevelQuery("b.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("b.wkctr_id", wkctrId, "", compNo);
    	
        return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public List findPointList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String pmptrlrsltlistId = String.valueOf(map.get("pmptrlrsltlistId"));
		String pmId = String.valueOf(map.get("pmId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 																	");
        query.append("		x.comp_no 									AS COMPNO				");
        query.append("		,y.pmptrlrsltlist_id 					AS PMPTRLRSLTLISTID			");
        query.append("		,y.pmptrlrsltpoint_id 				AS PMPTRLRSLTPOINTID			");
        query.append("		,x.pm_point_id								AS PMPOINTID			");
        query.append("		,(SELECT a.full_desc												");
        query.append("			FROM TAEQASMB a													");
        query.append("			WHERE a.eqasmb_id = x.eqasmb_id 								");
        query.append("			AND a.comp_no = x.comp_no ) 			AS EQASMBDESC			");
        query.append("		,y.pm_point_rslt_status 					AS PMPOINTRSLTSTATUS	");
        query.append("		,dbo.SFACODE_TO_DESC(y.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+lang+"')	AS PMPOINTRSLTSTATUSDESC	");
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
        query.append("		,CASE WHEN y.pmptrlrsltpoint_id is null THEN 'P' ELSE 'C' END			AS STATUS				");
        query.append("		,dbo.SFACODE_TO_DESC(CASE WHEN y.pmptrlrsltpoint_id is null THEN 'P' ELSE 'C' END,'WO_STATUS','SYS','','"+lang+"')	AS STATUSDESC	");
        query.append("		,y.remark 									AS REMARK				");
    	query.append("		,(SELECT count(1)																							");
    	query.append("				FROM TADOCDATA aa INNER JOIN TADOCUMENT bb															");
    	query.append("				ON aa.comp_no = bb.comp_no																			");
    	query.append("				AND aa.doc_id = bb.doc_id																			");
    	query.append("				WHERE 1=1																							");
    	query.append("				AND aa.file_ext IN (SELECT value											");
    	query.append("						FROM dbo.SPLIT_STR_TO_TABLE((select value$ from taconfig where comp_no = '"+compNo+"'		");
    	query.append("														AND name='IMG_FILE_TYPE'),',') )											");
    	query.getStringEqualQuery("aa.comp_no", compNo);
    	query.append("				AND aa.doc_id IN (																					");
    	query.append("						SELECT doc_id																				");
    	query.append("						FROM TAOBJDOC																				");
    	query.append("						WHERE 1=1																					");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", "PM_PATROL_POINT");
    	query.append("						AND object_id = y.pmptrlrsltpoint_id															");
    	query.append("			))																						AS PHOTOCNT		");
        query.append("FROM TAPMPOINT x LEFT OUTER JOIN 											");
        query.append("(SELECT * FROM TAPMPTRLRSLTPOINT WHERE 1=1								");
        query.getAndQuery("pmptrlrsltlist_id",pmptrlrsltlistId);
        query.append(") y																		");
        query.append("ON x.comp_no = y.comp_no													");
        query.append("AND x.pm_point_id = y.pm_point_id											");
        query.append("WHERE 1=1																	");
        query.append("AND x.is_deleted='N'												        ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pm_id",pmId);
        query.append("ORDER BY case when y.pm_point_rslt_status is null then 1  else 2 end,x.step_num														");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
	public List findPointHistList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
		String pmPointId = String.valueOf(map.get("pmPointId"));
		String pmptrlrsltlistId = String.valueOf(map.get("pmptrlrsltlistId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 																	");
        query.append("		x.comp_no 									AS COMPNO				");
        query.append("		,x.pmptrlrsltlist_id 					AS PMPTRLRSLTLISTID			");
        query.append("		,x.pmptrlrsltpoint_id 				AS PMPTRLRSLTPOINTID			");
        query.append("		,y.pm_point_id								AS PMPOINTID			");
        query.append("		,(SELECT a.full_desc												");
        query.append("			FROM TAEQASMB a													");
        query.append("			WHERE a.eqasmb_id = y.eqasmb_id 								");
        query.append("			AND a.comp_no = y.comp_no ) 			AS EQASMBDESC			");
        query.append("		,x.pm_point_rslt_status 					AS PMPOINTRSLTSTATUS	");
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','','"+lang+"')	AS PMPOINTRSLTSTATUSDESC	");
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
        query.append("		,CASE WHEN x.pmptrlrsltpoint_id is null THEN 'P' ELSE 'C' END	AS STATUS				");
        query.append("		,dbo.SFACODE_TO_DESC(CASE WHEN x.pmptrlrsltpoint_id is null THEN 'P' ELSE 'C' END,'WO_STATUS','SYS','','"+lang+"')	AS STATUSDESC	");
        query.append("		,x.remark 									AS REMARK				");
        query.append("		,(SELECT a.dept_id													");
        query.append("			FROM TAPMPTRLRSLTLIST a											");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pmptrlrsltlist_id = x.pmptrlrsltlist_id) AS DEPTID		");
        query.append("		,(SELECT (SELECT b.description 										");
        query.append("					FROM TADEPT b											");
        query.append("					WHERE b.comp_no = a.comp_no								");
        query.append("					AND b.dept_id = a.dept_id) DEPTDESC						");
        query.append("			FROM TAPMPTRLRSLTLIST a											");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pmptrlrsltlist_id = x.pmptrlrsltlist_id) AS DEPTDESC		");
        query.append("		,(SELECT a.emp_id													");
        query.append("			FROM TAPMPTRLRSLTLIST a											");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pmptrlrsltlist_id = x.pmptrlrsltlist_id) AS EMPID			");
        query.append("		,(SELECT (SELECT b.emp_name 										");
        query.append("					FROM TAEMP b											");
        query.append("					WHERE b.comp_no = a.comp_no								");
        query.append("					AND b.emp_id = a.emp_id) EMPDESC						");
        query.append("			FROM TAPMPTRLRSLTLIST a											");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pmptrlrsltlist_id = x.pmptrlrsltlist_id) AS EMPDESC		");
        query.append("		,(SELECT a.wkor_date												");
        query.append("			FROM TAPMPTRLRSLTLIST a											");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND a.pmptrlrsltlist_id = x.pmptrlrsltlist_id) AS WKORDATE		");
        query.append("FROM TAPMPTRLRSLTPOINT x INNER JOIN 										");
        query.append(" TAPMPOINT y 																");
        query.append("ON x.comp_no = y.comp_no													");
        query.append("AND x.pm_point_id = y.pm_point_id											");
        query.append("WHERE 1=1																	");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("y.pm_point_id",pmPointId);
        query.getAndQuery("x.pmptrlrsltlist_id",pmptrlrsltlistId);
        query.append("AND x.pmptrlrsltlist_id IN (SELECT a.pmptrlrsltlist_id 					");
        query.append("							FROM TAPMPTRLRSLTLIST a							");
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
		String pmptrlrsltlistId = String.valueOf(map.get("pmptrlrsltlistId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 																	");
        query.append("		count(*) AS count													");
        query.append("FROM TAPMPTRLRSLTPOINT													");
        query.append("WHERE 1=1																	");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pmptrlrsltlist_id",pmptrlrsltlistId);
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findStatus(Map map) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pmptrlrsltlistId = convertString(map.get("pmptrlrsltlistId"));
		String pmId   = convertString(map.get("pmId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT 												");
        query.append("		CASE	(SELECT count(1)  						");
        query.append("				FROM TAPMPOINT							");
        query.append("				WHERE 1=1								");
        query.append("				AND comp_no = '"+compNo+"'				");
        query.append("				AND pm_id = "+pmId+")					");
        query.append("			WHEN	(SELECT count(1)  					");
        query.append("				FROM TAPMPTRLRSLTPOINT					");
        query.append("				WHERE 1=1								");
        query.append("				AND comp_no = '"+compNo+"'				");
        query.append("				AND pmptrlrsltlist_id = "+pmptrlrsltlistId+"	");
        query.append("				AND is_saved = 'Y'						");
        query.append("				AND pm_point_rslt_status is not null)	");
        query.append("			THEN 'C'									");
        query.append("			ELSE 'P'									");
        query.append("			END STATUS									");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public int insertPoint(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAPMPTRLRSLTPOINT (                                                   ");
        query.append("       COMP_NO, PMPTRLRSLTPOINT_ID, PMPTRLRSLTLIST_ID,PMPTRLSCHED_ID PM_POINT_ID, ");
        query.append("       PM_POINT_RSLT_STATUS, RESULT_VALUE, REMARK,IS_SAVED                        ");
        query.append("       )                                                                          ");
        query.append("VALUES                                                                            ");
        query.append("(                                                                                 ");
        query.append("      ?,?,?,?,                                                                    ");
        query.append("      ?,?,?,?                                                                     ");
        query.append(")                                                                                 ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("pmptrlrsltpointId"))
        		,convertString(map.get("pmptrlrsltlistId"))
        		,convertString(map.get("pmptrlschedId"))
        		,convertString(map.get("pmPointId"))
        		,convertString(map.get("resultStatus"))
        		,convertString(map.get("resultVal"))
        		,convertString(map.get("remark"))
        		,"Y"
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updatePoint(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPMPTRLRSLTPOINT SET                               ");
        query.append("       PM_POINT_RSLT_STATUS = ?                            ");
        query.append("       ,RESULT_VALUE        = ?                            ");
        query.append("       ,REMARK              = ?                            ");
        query.append("       ,IS_SAVED            = ?                            ");
        query.append("WHERE COMP_NO    = ?                                       ");
        query.append("  AND PMPTRLRSLTLIST_ID    = ?                             ");
        query.append("  AND PMPTRLRSLTPOINT_ID = ?                               ");
        query.append("  AND PM_POINT_ID = ?                                      ");
        
        objects = new Object[] {
        		convertString(map.get("resultStatus"))
        		,convertString(map.get("resultVal"))
        		,convertString(map.get("remark"))
        		,"Y"
        		,convertString(map.get("compNo"))
        		,convertString(map.get("pmptrlrsltlistId"))
        		,convertString(map.get("pmptrlrsltpointId"))
        		,convertString(map.get("pmPointId"))
        };

        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
public int mergeAbnormalRslt(Map map) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DECLARE @t1 TABLE(												");
		query.append("	COMP_NO NVARCHAR(1000)											");
		query.append("	,PMPTRLRSLTLIST_ID NVARCHAR(1000)								");
		query.append("	,PM_POINT_ID NVARCHAR(1000)										");
		query.append("	,PM_POINT_RSLT_STATUS NVARCHAR(1000)							");
		query.append("	,RESULT_VALUE NVARCHAR(1000)									");
		query.append("	,REMARK NVARCHAR(1000)											");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?)										");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWONGPOINT a, @t1 b									");
		query.append("		WHERE 	a.COMP_NO = b.COMP_NO								");
    	query.append("		AND 	a.PMPTRLRSLTLIST_ID = b.PMPTRLRSLTLIST_ID			");
    	query.append("		AND 	a.PM_POINT_ID = b.PM_POINT_ID						");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWONGPOINT SET										");
		query.append("			pm_point_rslt_status = b.PM_POINT_RSLT_STATUS,			");
    	query.append("			pm_point_rep_status = b.PM_POINT_RSLT_STATUS,			");
    	query.append("			result_value = CAST(b.RESULT_VALUE AS numeric),			");
    	query.append("			remark = b.REMARK										");
		query.append("		FROM TAWONGPOINT a, @t1 b									");
		query.append("		WHERE 	a.COMP_NO = b.COMP_NO								");
    	query.append("		AND 	a.PMPTRLRSLTLIST_ID = b.PMPTRLRSLTLIST_ID			");
    	query.append("		AND 	a.PM_POINT_ID = b.PM_POINT_ID						");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWONGPOINT										");
		query.append("			(comp_no,	wongpoint_id,			pm_point_id,	pmi_type,		pmptrlrsltpoint_id,							");
    	query.append("			pmptrlrsltlist_id,	pm_point_rslt_status,	pm_point_rep_status,	actual_date,	result_value,	remark	)	");
		query.append("		SELECT b.comp_no,	next value for sqawongpoint_id, b.pm_point_id,	?,				?,								");
    	query.append("		b.pmptrlrsltlist_id,	b.pm_point_rslt_status,	b.pm_point_rslt_status,	?,b.result_value, b.remark					");
		query.append("		FROM @t1 b													");
		query.append("END																");
    	
        Object[] objects = new Object[] {
                convertString(map.get("compNo"))
                ,convertString(map.get("pmptrlrsltlistId"))
                ,convertString(map.get("pmPointId"))
                ,convertString(map.get("resultStatus"))
                ,convertString(map.get("resultVal"))
                ,convertString(map.get("remark"))
                ,"PINS"
                ,convertString(map.get("pmptrlrsltpointId"))
                ,DateUtil.getDateTime("yyyyMMdd")
        };
        
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}
	public int updateStartDate(Map map) throws Exception
    {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPMPTRLRSLTLIST SET	");
        query.append("       start_date		= ?		");
        query.append("       ,start_time	= ?		");
        query.append("WHERE COMP_NO	= ?				");
        query.append("  AND pmptrlrsltlist_id	= ? ");
        
        objects = new Object[] {
        		DateUtil.getDate()
        		,DateUtil.getDateTime("HH")+""+DateUtil.getDateTime("mm")
        		,convertString(map.get("compNo"))
        		,convertString(map.get("pmptrlrsltlistId"))
        };

        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updateEndDate(Map map) throws Exception
    {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPMPTRLRSLTLIST SET					");
        query.append("       end_date		= ?						");
        query.append("       ,end_time		= ?						");
        query.append("       ,work_time		= CASE len(start_date+start_time) WHEN 12 THEN datediff(MI, CONVERT(DATETIME,STUFF(STUFF(STUFF(start_date+start_time+'00',13,0,':'),11,0,':'),9,0,' ')), getDate())	");
        query.append("       					ELSE ''  END		");
        query.append("WHERE COMP_NO	= ?								");
        query.append("  AND pmptrlrsltlist_id	= ?					");
        
        objects = new Object[] {
        		DateUtil.getDate()
        		,DateUtil.getDateTime("HH")+""+DateUtil.getDateTime("mm")
        		,convertString(map.get("compNo"))
        		,convertString(map.get("pmptrlrsltlistId"))
        };

        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public String getWopointCount(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pmptrlrsltlistId = convertString(map.get("pmptrlrsltlistId"));
		String pmId   = convertString(map.get("pmId"));
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)				");
        query.append("FROM TAPMPTRLRSLTPOINT x		");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.is_saved","Y");
        query.getAndQuery("x.pmptrlrsltlist_id",pmptrlrsltlistId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
	public String getPmpointCount(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String pmptrlrsltlistId = convertString(map.get("pmptrlrsltlistId"));
		String pmId   = convertString(map.get("pmId"));
		
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT COUNT(*)				");
        query.append("FROM TAPMPOINT x				");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.pm_id",pmId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
	
	public void updatePmSched(Map map) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMPTRLSCHED	 SET							");
    	query.append("	pmsched_status		= ?								");
    	query.append("	,actual_date		= ?								");
    	query.append("	,actual_time		= ?								");
    	query.append("	,check_by			= ?								");
    	query.append("WHERE pmptrlsched_id	= ?								");
    	query.append("  AND comp_no			= ?								");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("woStatus")),
    			"".equals(convertString(map.get("actualDate")))?convertString(map.get("startDate")):convertString(map.get("actualDate")),
    			"".equals(convertString(map.get("actualTime")))?convertString(map.get("startTime")):convertString(map.get("actualTime")),
                convertString(map.get("userId")),
                convertString(map.get("pmptrlschedId")),
                convertString(map.get("compNo"))
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	public void updatePmPointSchedStatus(Map map) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAPMPTRLSCHED	 SET							");
    	query.append("	pmsched_status		= ?								");
    	query.append("WHERE pmptrlsched_id	= ?								");
    	query.append("  AND comp_no			= ?								");
    	
    	Object[] objects = new Object[] {
                convertString(map.get("woStatus")),
                convertString(map.get("pmptrlschedId")),
                convertString(map.get("compNo"))
    	};
    	
    	rtnValue = getJdbcTemplate().update(query.toString(), getObject(objects));
	}

    public int updatePminsList(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAPMPTRLRSLTLIST SET                 ");
        query.append("  pmsched_status    = ?,                    ");
        query.append("  start_date        = ?,                    ");
        query.append("  start_time        = ?,                    ");
        query.append("  end_date          = ?,                    ");
        query.append("  end_time          = ?,                    ");
        query.append("  work_time         = ?,                    ");
        query.append("  shift_type        = ?,                    ");
        query.append("  emp_id            = (SELECT emp_id FROM TAUSER WHERE user_id = ? AND comp_no = ?),                    ");
        query.append("  close_id          = (SELECT emp_id FROM TAUSER WHERE user_id = ? AND comp_no = ?),                    ");
        query.append("  close_date        = ?                     ");
        query.append("WHERE pmptrlrsltlist_id  = ?                ");
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
                convertString(map.get("pmptrlrsltlistId")),
                convertString(map.get("compNo"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

	public int executeSP_PM_UPDATE_LASTCPLT_DATE(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = convertString(map.get("compNo"));
        String pmId = convertString(map.get("pmId"));
        String pmptrlrsltlistId = convertString(map.get("pmptrlrsltlistId"));

        query.append("exec SP_PM_UPDATE_LASTCPLT_DATE '"+compNo+"', '"+pmId+"','"+pmptrlrsltlistId+"' ;		");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
}