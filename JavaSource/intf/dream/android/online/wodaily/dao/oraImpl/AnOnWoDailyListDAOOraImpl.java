package intf.dream.android.online.wodaily.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.wodaily.dao.AnOnWoDailyListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnWoDailyListDAOTarget"
 * @spring.txbn id="anOnWoDailyListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnWoDailyListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnWoDailyListDAO
{
	public List findWoDailyList(Map map) throws Exception
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
     	String equipId = String.valueOf(map.get("equipId"));
     	String wodaylistId = String.valueOf(map.get("wodaylistId"));
    	String startTime = String.valueOf(map.get("startTime"));
    	String endTime = String.valueOf(map.get("endTime"));
        String startFdateTime = ("".equals(startDate)||"null".equals(startDate)?"000000":startDate.replaceAll("-", ""))
                +("".equals(startTime)||"null".equals(startTime)?"0000":startTime.replaceAll(":", ""));
        String startEdateTime = ("".equals(endDate)||"null".equals(endDate)?"000000":endDate.replaceAll("-", ""))
                +("".equals(endTime)||"null".equals(endTime)?"0000":endTime.replaceAll(":", ""));

    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT          																					");
        query.append("        a.comp_no 																AS COMPNO       ");
        query.append("        ,a.wodaylist_id 															AS WODAYLISTID  ");
        query.append("        ,a.wo_date 																AS WODATE       ");
        query.append("        ,a.wo_dept 																AS DEPTID       ");
        query.append("        ,(SELECT x.description        															");
        query.append("        FROM tadept x        																		");
        query.append("        WHERE x.comp_no = a.comp_no        														");
        query.append("        AND x.dept_id =a.wo_dept) AS deptDesc        												");
        query.append("        ,a.wodaylist_status 														AS STATUS       ");
        query.append("        ,SFACODE_TO_DESC(a.wodaylist_status,'WODAYLIST_STATUS','SYS','','ko') 	AS STATUSDESC   ");
        query.append("        ,a.upd_by  																AS EMPID        ");
        query.append("        ,(SELECT x.emp_name        																");
        query.append("        FROM taemp x        																		");
        query.append("        WHERE x.comp_no = a.comp_no        														");
        query.append("        AND x.emp_id = a.upd_by) 													AS EMPDESC      ");
        query.append("        ,a.remark 																AS REMARK       ");
        query.append("        ,a.plant 																	AS PLANTID      ");
        query.append("        ,(SELECT x.description        															");
        query.append("        FROM taplant x        																	");
        query.append("        WHERE x.comp_no= a.comp_no        														");
        query.append("        AND x.plant = a.plant) 													AS PLANTDESC    ");
        query.append("        ,a.description 															AS WODESC	    ");
        query.append("        ,a.start_fdate 															AS STARTDATE    ");
        query.append("        ,a.start_ftime 															AS STARTTIME    ");
        query.append("        ,a.start_edate 															AS ENDDATE      ");
        query.append("        ,a.start_etime 															AS ENDTIME      ");
        query.append("        ,a.wkctr_id 																AS WKCTRID      ");
        query.append("        ,(SELECT x.description        															");
        query.append("        FROM tawkctr x        																	");
        query.append("        WHERE x.comp_no=a.comp_no        															");
        query.append("        AND x.wkctr_id =a.wkctr_id)       										AS WKCTRDESC    ");
        query.append("        ,a.equip_id 																AS EQUIPID      ");
        query.append("        ,(SELECT x.description        															");
        query.append("        FROM taequipment x        																");	
        query.append("        WHERE x.comp_no =a.comp_no        														");
        query.append("        AND x.equip_id = a.equip_id)												AS EQUIPDESC    ");
        query.append("        ,(SELECT count(*)         																");
        query.append("        FROM TAWORKORDER b INNER JOIN tawoequip c         										");
        query.append("        ON b.comp_no = c.comp_no        															");
        query.append("        AND b.wkor_id = c.wkor_id         														");
        query.append("        WHERE b.comp_no = a.comp_no         														");
        query.append("        AND B.DEPT_ID = a.wo_dept        															");
        query.append("        AND b.plant = a.plant        																");
        query.append("        AND (c.equip_id = a.equip_id OR 1 = (CASE WHEN a.equip_id IS NULL THEN 1 ELSE 2 END ))	");
        query.getAndQuery("b.wkctr_id", wkctrId);
        query.getAndDateQuery("NVL(b.start_date,'000000')||NVL(b.start_time,'0000')", startFdateTime, startEdateTime);
        query.append("        ) 																		AS UNPLANCNT    ");
        query.append("        ,(SELECT count(*)        																	");
        query.append("        FROM TAPMINSLIST b        																");
        query.append("        WHERE b.comp_no = a.comp_no        														");
        query.append("        AND (b.equip_id = a.equip_id OR 1 = (CASE WHEN a.equip_id IS NULL THEN 1 ELSE 2 END ))	");
        query.append("        AND B.DEPT_ID = a.wo_dept        															");
        query.append("        AND b.plant = a.plant        																");
        query.getStringEqualQuery("b.IS_DELETED", "N");
        query.getAndQuery("b.wkctr_id", wkctrId);
        query.getAndDateQuery("NVL(b.start_date,'000000')||NVL(b.start_time,'0000')", startFdateTime, startEdateTime);
        query.append("        )																			AS ROUTINECNT   ");
        query.append("        ,(SELECT count(*)        																	");
        query.append("        FROM TADOCUMENT x LEFT OUTER JOIN TAOBJDOC y        										");
        query.append("        ON x.comp_no = y.comp_no        															");
        query.append("        AND x.doc_id = y.doc_id        															");
        query.append("        WHERE 1=1        																			");
        query.append("        AND x.comp_no = a.comp_no        															");
        query.append("        AND y.object_id =wodaylist_id        														");
        query.append("        AND y.object_type = 'WODAY'        														");
        query.append("        ) 																		AS DOCCNT		");       
        query.append(" ,(SELECT COUNT(*)                                                                                ");
        query.append("        FROM TAIMAGE x INNER JOIN TAIMGDATA y                                                     ");
        query.append("        ON x.comp_no = y.comp_no                                                                  ");
        query.append("        AND x.image_id = y.image_id                                                               ");
        query.append("        WHERE x.comp_no = a.comp_no                                                               ");
        query.append("        AND  x.object_type='WODAY'                 												");
        query.append("        AND x.sub_img_type = 'APPRSIGN'                                                           ");
        query.append("        AND  x.object_id =a.wodaylist_id)                                         AS SIGNCNT      ");
        query.append("        ,(SELECT imgdata_id FROM (        														");
        query.append("        SELECT x.comp_no, x.image_id, x.object_id ,y.imgdata_id                                   ");
        query.append("        FROM TAIMAGE x INNER JOIN TAIMGDATA y                                                     ");
        query.append("        ON x.comp_no = y.comp_no                                                                  ");
        query.append("        AND x.image_id = y.image_id                                                               ");
        query.append("        WHERE 1=1                                                              					");
        query.append("        AND  x.object_type='WODAY'                 												");
        query.append("        AND x.sub_img_type = 'APPRSIGN'         													");
        query.append("        ORDER BY y.imgdata_id desc ) x        													");
        query.append("        WHERE 1=1        																			");
        query.append("        AND x.comp_no = a.comp_no                     											");
        query.append("        AND  x.object_id =a.wodaylist_id        													");
        query.append("        AND ROWNUM=1        																		");
        query.append("        )                                   										AS IMGDATAID    ");
        query.append("        ,        																					");
        query.append("        (SELECT image_id FROM (        															");
        query.append("        SELECT x.comp_no, x.image_id, x.object_id                                                 ");
        query.append("        FROM TAIMAGE x INNER JOIN TAIMGDATA y                                                     ");
        query.append("        ON x.comp_no = y.comp_no                                                                  ");
        query.append("        AND x.image_id = y.image_id                                                               ");
        query.append("        WHERE 1=1                                                              					");
        query.append("        AND  x.object_type='WODAY'                 												");
        query.append("        AND x.sub_img_type = 'APPRSIGN'         													");
        query.append("        ORDER BY y.imgdata_id desc ) x        													");
        query.append("        WHERE 1=1        																			");
        query.append("        AND x.comp_no = a.comp_no                     											");
        query.append("        AND  x.object_id =a.wodaylist_id        													");
        query.append("        AND ROWNUM=1        																		");
        query.append("        )         																				");
        query.append("                                     												AS IMAGEID      ");
        query.append("FROM   TAWODAYLIST a                                                                              ");
        query.append("WHERE 1=1        																					");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.getAndQuery("a.wodaylist_id", wodaylistId);
        query.append("ORDER BY wo_date DESC																				");
	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public int deleteWoDaily(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWODAYLIST		");
        query.append("WHERE comp_no = ?				");
        query.append("AND wodaylist_id   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wodaylistId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int insertWoDaily(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWODAYLIST                            ");
        query.append("  (comp_no,      wodaylist_id,    wodaylist_status,");
        query.append("   wo_dept,      upd_by,          equip_id,        ");
        query.append("   remark,       wo_date,         plant,           ");
        query.append("   description,  start_fdate,     start_ftime,     ");
        query.append("   start_edate,  start_etime,     wkctr_id         ");
        query.append("  )   VALUES                                       ");
        query.append("  (?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?,               ");
        query.append("   ?,             ?,              ?                ");
        query.append("  )                                                ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wodaylistId"))
        		,convertString(map.get("status"))
        		,convertString(map.get("deptId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("wkorDate"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("woDesc"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("wkcrtId"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	

	public int updateWoDaily(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

        query.append("UPDATE TAWODAYLIST SET                          ");
        query.append("       description              = ?             ");
        query.append("       ,wo_date                 = ?             ");
        query.append("       ,wo_dept                 = ?             ");
        query.append("       ,start_fdate             = ?             ");
        query.append("       ,start_ftime             = ?             ");
        query.append("       ,start_edate             = ?             ");
        query.append("       ,start_etime             = ?             ");
        query.append("       ,plant                   = ?             ");
        query.append("       ,remark                  = ?             ");
        query.append("       ,wkctr_id                = ?             ");
        query.append("       ,equip_id                = ?             ");
        query.append("WHERE  wodaylist_id             = ?             ");
        query.append("AND  comp_no                    = ?             ");
        
        objects = new Object[] {
        		convertString(map.get("woDesc"))
        		,convertString(map.get("wkorDate"))
        		,convertString(map.get("deptId"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("plant"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("wkcrtId"))
        		,convertString(map.get("equipId"))
        		,convertString(map.get("wodaylistId"))
        		,convertString(map.get("compNo"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int updateStatus(Map map) throws Exception
    {
		QueryBuffer query = new QueryBuffer();
        Object[] objects;

    	query.append("UPDATE TAWODAYLIST SET                          ");
        query.append("       wodaylist_status        = ?              ");
        query.append("WHERE  wodaylist_id            = ?              ");
        query.append("AND  comp_no                   = ?              ");
        
        objects = new Object[] {
        		convertString(map.get("status"))
        		,convertString(map.get("wodaylistId"))
        		,convertString(map.get("compNo"))
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public List findDailyUnPlanList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String startTime = String.valueOf(map.get("startTime"));
    	String endTime = String.valueOf(map.get("endTime"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String wkorId = String.valueOf(map.get("wkorId"));
    	String equipDesc = String.valueOf(map.get("equipDesc"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
     	String equipId = String.valueOf(map.get("equipId"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH eqInfo AS (				");
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
        query.append("		AND b.is_last_version='Y'	");
        query.getStringEqualQuery("b.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("b.eqloc_id", eqlocId, "", compNo);
		query.getLikeQuery("b.description||b.item_no", equipDesc);
        query.append("		)							");
        query.append("SELECT a.comp_no																					AS COMPNO		");
    	query.append("		,a.wkor_id																					AS WKORID		");
    	query.append("		,a.description																				AS WODESC		");
    	query.append("		,a.wo_no																					AS WONO			");
    	query.append("		,a.wo_type																					AS WOTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_type,'WO_TYPE','SYS','','"+lang+"') FROM DUAL)									AS WOTYPEDESC	");
    	query.append("		,a.pm_type																					AS PMTYPE		");
        query.append("		,(SELECT SFACODE_TO_DESC(a.pm_type,a.wo_type||'_TYPE','SYS','','"+lang+"') FROM DUAL)									AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.wo_status																				AS WO_STATUS	");
        query.append("		,(SELECT SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','','"+lang+"') FROM DUAL)								AS WOSTATUSDESC	");
    	query.append("		,(SELECT x.equip_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPID		");
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQUIPNO		");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 			AS EQUIPDESC	");
    	query.append("		,a.start_date 																				AS STARTDATE	");
        query.append("		,a.start_time 																				AS STARTTIME	");
        query.append("		,a.end_date 																				AS ENDDATE		");
        query.append(" 		,a.end_time 																				AS ENDTIME		");
        query.append(" 		,a.dept_id 																					AS DEPTID		");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
        query.append(" 		,a.emp_id 																					AS EMPID		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
        query.append(" 		,a.perform 																					AS PERFORM		");
    	query.append("		,(SELECT count(1) FROM TAWOCRAFT x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCRAFTCNT	");
    	query.append("		,(SELECT count(1) FROM TAWOPARTS x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOPARTSCNT	");
    	query.append("		,(SELECT count(*) FROM tadocdata WHERE doc_id IN (SELECT x.doc_id FROM TAOBJDOC x WHERE x.comp_no = a.comp_no 												");
    	query.append("											AND x.object_id = a.wkor_id AND object_type='WORESULT'))	AS WOPHOTOCNT	");
    	query.append("		,(SELECT x.eqloc_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCID		");
    	query.append("		,(SELECT x.eqloc_desc FROM eqInfo x WHERE x.wkor_id = a.wkor_id AND rownum=1) 				AS EQLOCDESC	");
    	query.append("FROM TAWORKORDER a  LEFT OUTER JOIN TAWOEQUIP y                                        							");
    	query.append("ON a.comp_no = y.comp_no AND a.wkor_id = y.wkor_id                   												");
    	query.append("WHERE 1=1																											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
        query.getAndQuery("a.dept_id", deptId);
        query.getAndQuery("a.wkctr_id", wkctrId);
        query.getAndQuery("a.plant", plant);
        query.getAndQuery("y.equip_id", equipId);
        String startFdateTime = ("".equals(startDate)?"000000":startDate.replaceAll("-", ""))
                +("".equals(startTime)?"0000":startTime.replaceAll(":", ""));
        String startEdateTime = ("".equals(endDate)?"000000":endDate.replaceAll("-", ""))
                +("".equals(endTime)?"0000":endTime.replaceAll(":", ""));
        query.getAndDateQuery("NVL(a.start_date,'000000')||NVL(a.start_time,'0000')", startFdateTime, startEdateTime);

    	if (!"".equals(wkorId))
        {
            query.getAndQuery("a.wkor_id", wkorId);
        }
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
	public List findDailyRoutineList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String lang = String.valueOf(map.get("lang"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String startTime = String.valueOf(map.get("startTime"));
    	String endTime = String.valueOf(map.get("endTime"));
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
     	String equipId = String.valueOf(map.get("equipId"));
    	
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
        query.append("				,b.old_eq_no																						");
        query.append("				,b.description																						");
        query.append("				,b.eqctg_type																						");
        query.append("FROM  TAPMEQUIP a INNER JOIN TAEQUIPMENT b																		");
        query.append("ON a.comp_no = b.comp_no																							");
        query.append("AND a.equip_id = b.equip_id																						");
        query.append("WHERE 1=1 ");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
    	query.getAndQuery("b.equip_id", equipId);
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
    	query.append("		,(SELECT x.item_no FROM eqInfo x WHERE x.equip_id = a.equip_id and rownum=1) 								AS EQUIPNO	");
    	query.append("		,(SELECT x.description FROM eqInfo x WHERE x.equip_id = a.equip_id and rownum=1) 						AS EQUIPDESC	");
    	query.append("		,(SELECT count(*) FROM TAPMINSPOINT x WHERE x.comp_no = a.comp_no AND x.pminslist_id = a.pminslist_id AND x.is_saved = 'Y') AS COMPLETEDCNT	");
    	query.append("		,(SELECT count(*) FROM TAPMPOINT x WHERE x.comp_no = a.comp_no AND x.pm_id = a.pm_id) AS POINTTOTALCNT		");
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
    	query.append("FROM TAPMINSLIST a, TAPMLST b																						");
    	query.append("WHERE a.comp_no = b.comp_no																						");
    	query.append("AND a.pm_id = b.pm_id																								");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
        query.getAndQuery("a.dept_id", deptId);
        query.getAndQuery("a.equip_id", equipId);
        query.getAndQuery("a.plant", plant);
        query.getAndQuery("a.wkctr_id", wkctrId);
        String startFdateTime = ("".equals(startDate)?"000000":startDate.replaceAll("-", ""))
                +("".equals(startTime)?"0000":startTime.replaceAll(":", ""));
        String startEdateTime = ("".equals(endDate)?"000000":endDate.replaceAll("-", ""))
                +("".equals(endTime)?"0000":endTime.replaceAll(":", ""));
        query.getAndDateQuery("NVL(a.start_date,'000000')||NVL(a.start_time,'0000')", startFdateTime, startEdateTime);

        return getJdbcTemplate().queryForList(query.toString());
    } 
}