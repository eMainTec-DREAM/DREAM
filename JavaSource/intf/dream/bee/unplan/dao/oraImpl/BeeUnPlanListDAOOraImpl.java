package intf.dream.bee.unplan.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import intf.dream.bee.common_value.BeeCommonValues;
import intf.dream.bee.unplan.dao.BeeUnPlanListDAO;
import intf.dream.bee.unplan.dto.BeeUnPlanCommonDTO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeUnPlanListDAOTarget"
 * @spring.txbn id="beeUnPlanListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeUnPlanListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeUnPlanListDAO
{
	public List findUnPlanList(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map) throws Exception
    {
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
    	String wkorId = CommonUtil.convertString(String.valueOf(map.get("wkorId")));
    	
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
        query.append(this.getUnPlanWithWhere(beeUnPlanCommonDTO, map));
        query.append("		)							");
        query.append("SELECT a.comp_no																					AS COMPNO		");
    	query.append("		,a.wkor_id																					AS WKORID		");
    	query.append("		,a.description																				AS WODESC		");
    	query.append("		,a.wo_no																					AS WONO			");
    	query.append("		,a.wo_type																					AS WOTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_type,'WO_TYPE','SYS','','"+lang+"') FROM DUAL)				AS WOTYPEDESC	");
    	query.append("		,a.pm_type																					AS PMTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.pm_type,a.wo_type||'_TYPE','SYS','','"+lang+"') FROM DUAL)		AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.wo_status																				AS WO_STATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','','"+lang+"') FROM DUAL)			AS WOSTATUSDESC	");
    	query.append("		,(SELECT x.equip_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPID		");
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 					AS EQUIPNO		");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPDESC	");
    	query.append("		,a.start_date 																				AS STARTDATE	");
        query.append("		,a.start_time 																				AS STARTTIME	");
        query.append("		,a.end_date 																				AS ENDDATE		");
        query.append(" 		,a.end_time 																				AS ENDTIME		");
        query.append(" 		,a.dept_id 																					AS DEPTID		");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
        query.append(" 		,a.emp_id 																					AS EMPID		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
        query.append(" 		,a.perform 																					AS PERFORM		");
    	query.append("		,(SELECT x.eqloc_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCID		");
    	query.append("		,(SELECT x.eqloc_desc FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCDESC	");
    	query.append(" 		,a.is_cleaning 																				AS CLEANING		");
    	
    	if (!"".equals(wkorId)){
    		query.append(this.getUnPlanDetailColumn(beeUnPlanCommonDTO, map));
    	}
    	
    	query.append("FROM TAWORKORDER a																								");
    	query.append("WHERE 1=1																											");
    	query.append(this.getUnPlanWhere(beeUnPlanCommonDTO, map));
    	
    	return getJdbcTemplate().queryForList(query.toString(beeUnPlanCommonDTO.getIsLoadMaxCount(), beeUnPlanCommonDTO.getFirstRow()));
        
    } 
	public List findUnPlanCount(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map) throws Exception
    {
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
        query.append(this.getUnPlanWithWhere(beeUnPlanCommonDTO, map));
        query.append("		)							");
        query.append("SELECT COUNT(1) AS COUNT			");
    	query.append("FROM TAWORKORDER a				");
    	query.append("WHERE 1=1							");
    	query.append(this.getUnPlanWhere(beeUnPlanCommonDTO, map));

    	return getJdbcTemplate().queryForList(query.toString());
        
    } 
	private String getUnPlanDetailColumn(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map)
	{
		QueryBuffer query = new QueryBuffer();
    	query.append("		,(SELECT count(1) FROM TAWOCRAFT x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCRAFTCNT		");
    	query.append("		,(SELECT count(1) FROM TAWOPARTS x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOPARTSCNT		");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 	");
    	query.append("											AND x.object_id = a.wkor_id AND object_type='WORESULT'))	AS WOPHOTOCNT	");
    	return query.toString();
	}
	
	private String getUnPlanWithWhere(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
    	String eqctgType = CommonUtil.convertString(String.valueOf(map.get("eqctgType")));
    	String eqlocId = CommonUtil.convertString(String.valueOf(map.get("eqlocId")));
    	String equipDesc = CommonUtil.convertString(String.valueOf(map.get("equipDesc")));
     	String usageDeptId = CommonUtil.convertString(String.valueOf(map.get("usageDeptId")));
		
		QueryBuffer query = new QueryBuffer();
		
		query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("		AND b.is_deleted='N'		");
        query.append("		AND b.is_last_version='Y'	");
        query.getStringEqualQuery("b.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("b.eqloc_id", eqlocId, "", compNo);
		query.getLikeQuery("b.description||b.item_no", equipDesc);
		
    	return query.toString();
	}
	
	private String getUnPlanWhere(BeeUnPlanCommonDTO beeUnPlanCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
    	String startDate = CommonUtil.convertString(String.valueOf(map.get("startDate")));
    	String endDate = CommonUtil.convertString(String.valueOf(map.get("endDate")));
    	String deptId = CommonUtil.convertString(String.valueOf(map.get("deptId")));
    	String wkctrId = CommonUtil.convertString(String.valueOf(map.get("wkctrId")));
    	String wkorId = CommonUtil.convertString(String.valueOf(map.get("wkorId")));
    	String empId = CommonUtil.convertString(String.valueOf(map.get("empId")));
    	String plant = CommonUtil.convertString(String.valueOf(map.get("plant")));
     	String equipId = CommonUtil.convertString(String.valueOf(map.get("equipId")));
    	String rootPage = CommonUtil.convertString(String.valueOf(map.get("rootPage")));
		
		QueryBuffer query = new QueryBuffer();
		
		query.getStringEqualQuery("a.comp_no", compNo);
    	query.getAndQuery("a.emp_id", empId);
    	query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	if(!"BeeWoDailyUnplanListFragment".equals(rootPage)|| "".equals(rootPage)){
    		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
        	query.append("  and a.wo_type NOT IN ('PMW','PMI','PMC','PM')																		");
        	
    	}
    	if (!"".equals(equipId)){
    		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo where equip_id="+equipId+" )	");
    	}
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.plant = '"+plant+"' ");
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.getAndDateQuery("a.wkor_date", startDate, endDate);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
		
    	return query.toString();
	}
	
	public int deleteWoFail(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOFAIL			");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wofail_id", convertString(map.get("woFailId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	
	public int deleteUnPlan(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWORKORDER		");
        query.append("WHERE comp_no = ?				");
        query.append("AND wkor_id   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertUnPlan(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWORKORDER(                                      ");
        query.append("     COMP_NO, WKOR_ID, WO_NO,                                 ");
        query.append("     DESCRIPTION, WO_STATUS, WO_TYPE, PM_TYPE,                ");
        query.append("     START_DATE, START_TIME, END_DATE, END_TIME,              ");
        query.append("     WORK_TIME,                                               ");
        query.append("     DEPT_ID, EMP_ID, PERFORM,                                ");
        query.append("     PM_ID, PMSCHED_ID, WO_GEN_TYPE, WOPOINT_ID,              ");
        query.append("     UPD_DATE, UPD_BY, SELF_VENDOR_TYPE, VENDOR_ID,           ");
        query.append("     P_WKOR_ID, WKOR_DATE, SHIFT_TYPE, TOT_AMT,               ");
        query.append("     PMACTION, EQLOC_ID, WKCTR_ID, CLOSE_ID,                  ");
        query.append("     CLOSE_DATE,IS_DELETED,PLANT,IS_CLEANING                  ");
        query.append("      )                                                       ");
        query.append("VALUES (                                                      ");
        query.append("     ?,?,?,                                                   ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("          CASE WHEN TO_CHAR(CEIL((TO_DATE(?||?,'YYYYMMDDHH24MISS') - TO_DATE(?||?,'YYYYMMDDHH24MISS') ) *24*60))  < 0 ");
        query.append("            THEN '0'                                          ");
        query.append("            ELSE TO_CHAR(CEIL((TO_DATE(?||?,'YYYYMMDDHH24MISS') - TO_DATE(?||?,'YYYYMMDDHH24MISS') ) *24*60))         ");
        query.append("          END,                                                ");
        query.append("     ?,?,?,                                                   ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?                                                  ");
        query.append("     )                                                        ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woDesc"))
        		,"PRW"
        		,convertString(map.get("woType"))
        		,convertString(map.get("pmType"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
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
        		,convertString(map.get("cleaning"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateUnPlan(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWORKORDER SET                                       ");
        query.append("       DESCRIPTION            = ?                            ");
        query.append("       ,WO_TYPE               = ?                            ");
        query.append("       ,PM_TYPE               = ?                            ");
        query.append("       ,WKOR_DATE             = ?                            ");
        query.append("       ,START_DATE            = ?                            ");
        query.append("       ,START_TIME            = ?                            ");
        query.append("       ,END_DATE              = ?                            ");
        query.append("       ,END_TIME              = ?                            ");
        query.append("       ,WORK_TIME =                                          ");
        query.append("          CASE WHEN TO_CHAR(CEIL((TO_DATE(?||?,'YYYYMMDDHH24MISS') - TO_DATE(?||?,'YYYYMMDDHH24MISS') ) *24*60))  < 0 ");
        query.append("            THEN '0'                                         ");
        query.append("            ELSE TO_CHAR(CEIL((TO_DATE(?||?,'YYYYMMDDHH24MISS') - TO_DATE(?||?,'YYYYMMDDHH24MISS') ) *24*60))         ");
        query.append("          END                                                ");
        query.append("       ,PERFORM               = ?                            ");
        if(!"".equals(convertString(map.get("deptId")))){
        	query.append("       ,DEPT_ID                = "+convertString(map.get("deptId"))+"                            ");
        }
        query.append("       ,EMP_ID                = ?                            ");
        query.append("       ,WO_STATUS             = ?                            ");
        query.append("       ,IS_CLEANING           = ?                            ");
        query.append("WHERE COMP_NO    = ?                                         ");
        query.append("  AND WKOR_ID    = ?                                         ");
        
        objects = new Object[] {
        		convertString(map.get("woDesc"))
        		,convertString(map.get("woType"))
        		,convertString(map.get("pmType"))
        		,convertString(map.get("wkorDate"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("perform"))
        		,convertString(map.get("empId"))
        		,"PRW"
        		,convertString(map.get("cleaning"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public List findWoFailList(Map map) throws Exception
    {
		String compNo 				= String.valueOf(map.get("compNo"));
		String lang 				= String.valueOf(map.get("lang"));
    	String wkorId 				= String.valueOf(map.get("wkorId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("         a.wofail_id	AS ID								");
        query.append("         ,a.comp_no 	AS COMP_NO							");
        query.append("         ,a.wofail_id AS WOFAILID							");
        query.append("         ,(SELECT											");
        query.append("				'['||x.failure_no||'] '||(SELECT y.key_name FROM TALANG y		");
        query.append("                  WHERE y.key_type = x.key_type			");
        query.append("                  AND   y.key_no = x.key_no				");
        query.append("                  AND   y.lang = '" + lang + "'			");
        query.append("                  )										");
        query.append("              FROM TAFAILURE x							");
        query.append("              WHERE x.fail_type='CA'						");
        query.append("              AND x.is_use = 'Y'							");
        query.append("              AND x.failure_id = a.ca_cd) AS CACDDESC		");
        query.append("         ,a.ca_desc AS CADESC                             ");
        query.append("         ,a.re_cd AS RECD                                 ");
        query.append("         ,(SELECT                                      	");
        query.append("                  '['||x.failure_no||'] '||(SELECT y.key_name FROM TALANG y     ");
        query.append("                  WHERE y.key_type = x.key_type        	");
        query.append("                  AND   y.key_no = x.key_no            	");
        query.append("                  AND   y.lang = '" + lang + "'           ");
        query.append("                  )                                    	");
        query.append("              FROM TAFAILURE x                         	");
        query.append("              WHERE x.fail_type='RE'                   	");
        query.append("              AND x.is_use = 'Y'                       	");
        query.append("              AND x.failure_id = a.re_cd) AS RECDDESC     ");
        query.append("         ,a.re_desc AS REDESC                             ");
        query.append("         ,a.mo_cd AS MOCD                                 ");
        query.append("         ,(SELECT                                      	");
        query.append("                  '['||x.failure_no||'] '||(SELECT y.key_name FROM TALANG y     ");
        query.append("                  WHERE y.key_type = x.key_type        	");
        query.append("                  AND   y.key_no = x.key_no            	");
        query.append("                  AND   y.lang = '" + lang + "'           ");
        query.append("                  )                                    	");
        query.append("              FROM TAFAILURE x                         	");
        query.append("              WHERE x.fail_type='MO'                   	");
        query.append("              AND x.is_use = 'Y'                       	");
        query.append("              AND x.failure_id = a.mo_cd) AS MOCDDESC     ");
        query.append("         ,a.mo_desc AS MODESC                             ");
        query.append("         ,a.eqdn_start_date AS EQDNSTARTDATE				");
        query.append("         ,a.eqdn_start_time AS EQDNSTARTTIME				");
        query.append("         ,a.eqdn_end_date AS EQDNENDDATE					");
        query.append("         ,a.eqdn_end_time AS EQDNENDTIME					");
        query.append("         ,a.eqdn_work_time AS EQDNWORKTIME				");
        query.append("         ,a.lndn_start_date AS LNDNSTARTDATE				");
        query.append("         ,a.lndn_start_time AS LNDNSTARTTIME				");
        query.append("         ,a.lndn_end_date AS LNDNENDDATE					");
        query.append("         ,a.lndn_end_time AS LNDNENDTIME					");
        query.append("         ,a.lndn_work_time AS LNDNWORKTIME				");
        query.append("         ,a.eqasmb_id AS EQASMBID							");
        query.append("         ,(SELECT aa.full_desc FROM TAEQASMB aa WHERE aa.comp_no = a.comp_no AND aa.eqasmb_id = a.eqasmb_id) AS EQASMBDESC	");
        query.append("FROM TAWOFAIL a											");
        query.append("WHERE 1=1													");
        query.getAndQuery("a.comp_no",compNo);
        query.getAndQuery("a.wkor_id",wkorId);
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	public int insertWoFailList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("INSERT INTO TAWOFAIL(                                                           ");
        query.append("     COMP_NO, WOFAIL_ID, WKOR_ID, CA_CD, RE_CD                                  ");
        query.append("     ,MO_CD, EQDN_START_DATE, EQDN_START_TIME, EQDN_END_DATE, EQDN_END_TIME     ");
        query.append("     ,EQASMB_ID                                                                 ");
        query.append("     )                                                                          ");
        query.append("VALUES (                                                                        ");
        query.append("     ?,SQAWOFAIL_ID.nextval,?,?,?                                               ");
        query.append("    ,?,?,?,?,?                                                                  ");
        query.append("    ,?                                                                          ");
        query.append("         )                                                                      ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("caCd"))
        		,convertString(map.get("reCd"))
        		,convertString(map.get("moCd"))
        		,convertString(map.get("eqdnStartDate"))
        		,convertString(map.get("eqdnStartTime"))
        		,convertString(map.get("eqdnEndDate"))
        		,convertString(map.get("eqdnEndTime"))
        		,convertString(map.get("eqAsmbId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateWoFailList(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWOFAIL SET                                      ");
        query.append("       CA_CD                  = ?                        ");
        query.append("       ,RE_CD                 = ?                        ");
        query.append("       ,MO_CD                 = ?                        ");
        query.append("       ,EQDN_START_DATE       = ?                        ");
        query.append("       ,EQDN_START_TIME       = ?                        ");
        query.append("       ,EQDN_END_DATE         = ?                        ");
        query.append("       ,EQDN_END_TIME         = ?                        ");
        query.append("       ,EQASMB_ID             = ?                        ");
        query.append("WHERE COMP_NO    = ?                                     ");
        query.append("  AND WKOR_ID    = ?                                     ");
        
        objects = new Object[] {
        		convertString(map.get("caCd"))
        		,convertString(map.get("reCd"))
        		,convertString(map.get("moCd"))
        		,convertString(map.get("eqdnStartDate"))
        		,convertString(map.get("eqdnStartTime"))
        		,convertString(map.get("eqdnEndDate"))
        		,convertString(map.get("eqdnEndTime"))
        		,convertString(map.get("eqAsmbId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	public int updateWorkOrderEqAsmbId(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
		Object[] objects;
		
		query.append("UPDATE TAWORKORDER SET                                   ");
        query.append("       EQASMB_ID             = ?                         ");
        query.append("WHERE COMP_NO    = ?                                     ");
        query.append("  AND WKOR_ID    = ?                                     ");
        
        objects = new Object[] {
        		convertString(map.get("eqAsmbId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
}