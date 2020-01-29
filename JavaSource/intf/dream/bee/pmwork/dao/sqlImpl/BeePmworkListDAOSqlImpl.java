package intf.dream.bee.pmwork.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.bee.common_value.BeeCommonValues;
import intf.dream.bee.pmwork.dao.BeePmworkListDAO;
import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beePmworkListDAOTarget"
 * @spring.txbn id="beePmworkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeePmworkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeePmworkListDAO
{
	public List findPmworkList(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception
    {
		String lang = CommonUtil.convertString(String.valueOf(map.get("lang")));
		String wkorId = CommonUtil.convertString(String.valueOf(map.get("wkorId")));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
        query.append(this.getPmworkWithWhere(beePmworkCommonDTO, map));
        query.append("		)							");
        query.append("SELECT a.comp_no																					AS COMPNO		");
    	query.append("		,a.wkor_id																					AS WKORID		");
    	query.append("		,a.description																				AS WODESC		");
    	query.append("		,a.wo_no																					AS WONO			");
    	query.append("		,a.wo_type																					AS WOTYPE		");
        query.append("		,dbo.SFACODE_TO_DESC(a.wo_type,'WO_TYPE','SYS','','"+lang+"')								AS WOTYPEDESC	");
    	query.append("		,a.pm_type																					AS PMTYPE		");
        query.append("		,dbo.SFACODE_TO_DESC(a.pm_type,a.wo_type+'_TYPE','SYS','','"+lang+"')						AS PMTYPEDESC	");
    	query.append("		,a.wkor_date																				AS WKORDATE		");
    	query.append("		,a.wo_status																				AS WO_STATUS	");
        query.append("		,dbo.SFACODE_TO_DESC(a.wo_status,'WO_STATUS','SYS','','"+lang+"')							AS WOSTATUSDESC	");
    	query.append("		,(SELECT top 1 x.equip_id FROM eqInfo x WHERE x.wkor_id = a.wkor_id) 						AS EQUIPID		");
    	query.append("		,(SELECT top 1 x.item_no FROM eqInfo x WHERE x.wkor_id = a.wkor_id ) 						AS EQUIPNO		");
    	query.append("		,(SELECT top 1 x.description FROM eqInfo x WHERE x.wkor_id = a.wkor_id ) 					AS EQUIPDESC	");
    	query.append("		,a.start_date 																				AS STARTDATE	");
        query.append("		,a.start_time 																				AS STARTTIME	");
        query.append("		,a.end_date 																				AS ENDDATE		");
        query.append(" 		,a.end_time 																				AS ENDTIME		");
        query.append(" 		,a.dept_id 																					AS DEPTID		");
        query.append(" 		,(SELECT x.description FROM TADEPT x WHERE x.comp_no = a.comp_no AND x.dept_id = a.dept_id) AS DEPTDESC		");
        query.append(" 		,a.emp_id 																					AS EMPID		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMPDESC		");
        query.append(" 		,a.perform 																					AS PERFORM		");
        if (!"".equals(wkorId)){
    		query.append(this.getPmworkDetailColumn(beePmworkCommonDTO, map));
    	}
    	query.append("FROM TAWORKORDER a																								");
    	query.append("WHERE 1=1																											");
    	query.append(this.getPmworkWhere(beePmworkCommonDTO, map));
    	
    	return getJdbcTemplate().queryForList(query.toString(beePmworkCommonDTO.getIsLoadMaxCount(), beePmworkCommonDTO.getFirstRow()));
    } 
	
	private String getPmworkDetailColumn(BeePmworkCommonDTO beePmworkCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("		,(SELECT count(1) FROM TAWOCRAFT x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOCRAFTCNT	");
    	query.append("		,(SELECT count(1) FROM TAWOPARTS x WHERE x.comp_no = a.comp_no AND x.wkor_id = a.wkor_id) 	AS WOPARTSCNT	");
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
    	query.getStringEqualQuery("object_type", "WORESULT");
    	query.append("						AND object_id = a.wkor_id																	");
    	query.append("			))																						AS WOPHOTOCNT	");
		
		return query.toString();
	}
	
	public List findPmworkCount(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
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
        query.append(this.getPmworkWithWhere(beePmworkCommonDTO, map));
        query.append("		)							");
        query.append("SELECT COUNT(1) AS COUNT			");
    	query.append("FROM TAWORKORDER a				");
    	query.append("WHERE 1=1							");
    	query.append(this.getPmworkWhere(beePmworkCommonDTO, map));
        
        return getJdbcTemplate().queryForList(query.toString());
    }
	
	private String getPmworkWithWhere(BeePmworkCommonDTO beePmworkCommonDTO, Map map)
	{
		String compNo = CommonUtil.convertString(String.valueOf(map.get("compNo")));
    	String eqctgType = CommonUtil.convertString(String.valueOf(map.get("eqctgType")));
    	String eqlocId = CommonUtil.convertString(String.valueOf(map.get("eqlocId")));
    	String equipDesc = CommonUtil.convertString(String.valueOf(map.get("equipDesc")));
     	String usageDeptId = CommonUtil.convertString(String.valueOf(map.get("usageDeptId")));
     	
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("		AND b.is_deleted='N'		");
        query.append("		AND b.is_last_version='Y'	");
        query.getStringEqualQuery("b.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("b.eqloc_id", eqlocId, "", compNo);
		query.getLikeQuery("b.description+b.item_no", equipDesc);
		
		return query.toString();
	}
	
	private String getPmworkWhere(BeePmworkCommonDTO beePmworkCommonDTO, Map map)
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
     	
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	
    	
    	if (!"".equals(equipId)){
    		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo where equip_id="+equipId+" )	");
    	}
    	
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.plant = '"+plant+"' ");
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.getAndQuery("a.emp_id", empId);
    	query.getAndDateQuery("a.wkor_date", startDate, endDate);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
		
		return query.toString();
	}
		
	
	public int deletePmwork(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWORKORDER		");
        query.append("WHERE comp_no = ?				");
        query.append("AND wkor_id   = ?				");
        query.append("AND wo_type   = ?				");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,"PMW"
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int insertPmwork(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
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
        query.append("     CLOSE_DATE,IS_DELETED,PLANT                              ");
        query.append("      )                                                       ");
        query.append("VALUES (                                                      ");
        query.append("     ?,?,?,                                                   ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     ?,?,?,?,                                                 ");
        query.append("     datediff(MI, CONVERT(DATETIME,STUFF(STUFF(STUFF(?+?+''+'00',13,0,':'),11,0,':'),9,0,' ')),CONVERT(DATETIME,STUFF(STUFF(STUFF(?+?+''+'00',13,0,':'),11,0,':'),9,0,' '))),	");
        query.append("     ?,?,?,                                                   ");
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
        		,"PRW"
        		,convertString(map.get("woType"))
        		,convertString(map.get("pmType"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
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
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public int insertWoequip(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOEQUIP(                         ");
        query.append("     COMP_NO, WOEQUIP_ID, WKOR_ID, EQUIP_ID    ");
        query.append("      )                                        ");
        query.append("VALUES (                                       ");
        query.append("     ?,next value for SQAWOEQUIP_ID,?,?        ");
        query.append("     )                                         ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("equipId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updateWoequip(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOEQUIP SET              ");
        query.append("       EQUIP_ID  = ?              ");
        query.append("WHERE COMP_NO    = ?              ");
        query.append("  AND WKOR_ID    = ?              ");
        
        objects = new Object[] {
        		convertString(map.get("equipId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int deleteWoequip(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("DELETE FROM TAWOEQUIP             ");
        query.append("WHERE COMP_NO    = ?              ");
        query.append("  AND WKOR_ID    = ?              ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updatePmwork(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
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
        query.append("       ,WORK_TIME =            datediff(MI, CONVERT(DATETIME,STUFF(STUFF(STUFF(?+?+''+'00',13,0,':'),11,0,':'),9,0,' ')),CONVERT(DATETIME,STUFF(STUFF(STUFF(?+?+''+'00',13,0,':'),11,0,':'),9,0,' ')))	");
        query.append("       ,PERFORM               = ?                            ");
        
        if(!"".equals(convertString(map.get("deptId")))){
        	query.append("       ,DEPT_ID                = "+convertString(map.get("deptId"))+"                            ");
        }
        
        query.append("       ,EMP_ID                = ?                            ");
        query.append("       ,WO_STATUS             = ?                            ");
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
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("perform"))
        		,convertString(map.get("empId"))
        		,"PRW"
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
	public List findWoCraftList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String wkorId 		= String.valueOf(map.get("wkorId"));
    	String woCraftId 	= String.valueOf(map.get("woCraftId"));
    	String empId	 	= String.valueOf(map.get("empId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.wocraft_id																				AS ID			");
    	query.append("		,a.comp_no																					AS COMP_NO		");
    	query.append("		,a.wocraft_id																				AS WOCRAFT_ID	");
    	query.append("		,a.wkor_id																					AS WKOR_ID		");
    	query.append("		,a.emp_id																					AS EMP_ID		");
        query.append(" 		,(SELECT x.emp_no FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMP_NO		");
        query.append(" 		,(SELECT x.emp_name FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS EMP_DESC		");
        query.append(" 		,(SELECT x.dept_id FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 		AS DEPT_ID		");
        query.append(" 		,(SELECT (SELECT y.description FROM TADEPT y WHERE y.comp_no = x.comp_no AND y.dept_id = x.dept_id)			");
        query.append(" 			FROM TAEMP x WHERE x.comp_no = a.comp_no AND x.emp_id = a.emp_id) 						AS DEPT_DESC	");
    	query.append("		,a.start_date 																				AS START_DATE	");
        query.append("		,a.start_time 																				AS START_TIME	");
        query.append("		,a.end_date 																				AS END_DATE		");
        query.append(" 		,a.end_time 																				AS END_TIME		");
        query.append(" 		,a.work_time 																				AS WORK_TIME	");
        query.append(" 		,a.remark 																					AS REMARK		");
    	query.append("FROM TAWOCRAFT a																									");
    	query.append("WHERE 1=1																											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.getAndQuery("a.wocraft_id", woCraftId);
    	query.getAndQuery("a.emp_id", empId);
    	query.append("ORDER BY a.wocraft_id																								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	
	public int insertWoCraft(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOCRAFT (                                               ");
        query.append("       COMP_NO, WOCRAFT_ID, WKOR_ID, EMP_ID                           ");
        query.append("       ,START_DATE, START_TIME, END_DATE, END_TIME                    ");
        query.append("       ,WORK_TIME, REMARK                                             ");
        query.append("       )                                                              ");
        query.append("VALUES(                                                               ");
        query.append("      ?,?,?,?,   														");
        query.append("      ?,?,?,?,   														");
        query.append("      ?,?        														");
        query.append("      )                                                               ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woCraftId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("empId"))
        		,convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("workTime"))
        		,convertString(map.get("remark"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updateWoCraft(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOCRAFT SET                                     ");
        query.append("       START_DATE         = ?                            ");
        query.append("       ,START_TIME        = ?                            ");
        query.append("       ,END_DATE          = ?                            ");
        query.append("       ,END_TIME          = ?                            ");
        query.append("       ,WORK_TIME         = ?                            ");
        query.append("       ,REMARK            = ?                            ");
        query.append("WHERE COMP_NO    = ?                                     ");
        query.append("  AND WKOR_ID    = ?                                     ");
        query.append("  AND WOCRAFT_ID          = ?                            ");
        
        objects = new Object[] {
        		convertString(map.get("startDate"))
        		,convertString(map.get("startTime"))
        		,convertString(map.get("endDate"))
        		,convertString(map.get("endTime"))
        		,convertString(map.get("workTime"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woCraftId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	public int deleteWoCraft(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAWOCRAFT			");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wocraft_id", convertString(map.get("woCraftId")));
        
        return getJdbcTemplate().update(query.toString());
    }
	
    public List findWoPartsList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String lang 		= String.valueOf(map.get("lang"));
    	String wkorId 		= String.valueOf(map.get("wkorId"));
    	String woPartId 	= String.valueOf(map.get("woPartId"));
    	String partId 		= String.valueOf(map.get("partId"));
    	String partGrade 	= String.valueOf(map.get("partGrade"));
    	String wcodeId 		= String.valueOf(map.get("wcodeId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.wopart_id																				AS ID			");
    	query.append("		,a.comp_no																					AS COMP_NO		");
    	query.append("		,a.wopart_id																				AS WOPART_ID	");
    	query.append("		,a.wkor_id																					AS WKOR_ID		");
    	query.append("		,a.wcode_id																					AS WCODE_ID		");
        query.append(" 		,(SELECT x.wname FROM TAWAREHOUSE x WHERE x.comp_no = a.comp_no AND x.wcode_id = a.wcode_id) AS WCODE_DESC	");
    	query.append("		,a.part_id																					AS PART_ID		");
    	query.append("		,b.part_no																					AS PART_NO		");
    	query.append("		,b.description																				AS PART_DESC	");
    	query.append("		,b.full_desc																			AS PART_FULL_DESC 	");
    	query.append("		,b.uom																						AS PART_UOM		");
    	query.append("		,a.part_grade																				AS PART_GRADE	");
        query.append("		,dbo.SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+lang+"')							AS PART_GRADE_DESC	");
    	query.append("		,a.use_qty																					AS USE_QTY		");
        query.append(" 		,(SELECT x.stock_qty FROM TAPTSTOCK x WHERE x.comp_no = a.comp_no AND x.part_id = a.part_id					");
        query.append(" 								AND x.part_grade = a.part_grade 													");
        query.append(" 								AND x.wcode_id = a.wcode_id )										AS STOCK_QTY	");
    	query.append("		,a.ptrepairlist_id																		AS PTREPAIRLIST_ID	");
    	query.append("		,a.remark																					AS REMARK		");
    	query.append("		,a.unit_price																				AS UNIT_PRICE	");
    	query.append("		,a.tot_price																				AS TOT_PRICE	");
    	query.append("		,a.ptisslist_id																				AS PTISSLIST_ID	");
    	query.append("FROM TAWOPARTS a INNER JOIN TAPARTS b																				");
    	query.append("ON a.comp_no = b.comp_no																							");
    	query.append("AND a.part_id = b.part_id																							");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("b.is_deleted", "N");
    	query.getAndQuery("a.wkor_id", wkorId);
    	query.getAndQuery("a.wopart_id", woPartId);
    	query.getAndQuery("a.part_id", partId);
    	query.getAndQuery("a.part_grade", partGrade);
    	query.append("ORDER BY a.wopart_id																								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	public int insertWoParts(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWOPARTS (                                     ");
        query.append("       COMP_NO, WOPART_ID, WKOR_ID, WCODE_ID                ");
        query.append("       ,PART_ID, PART_GRADE, USE_QTY, REMARK                ");
        query.append("       ,UNIT_PRICE, TOT_PRICE                               ");
        query.append("       )                                                    ");
        query.append("VALUES(                                                     ");
        query.append("      ?,?,?,?,											  ");
        query.append("      ?,?,?,? 											  ");
        query.append("      ,(SELECT last_price FROM TAPARTS WHERE comp_no = ?    ");
        query.append("             AND part_id = ? )                              ");
        query.append("      ,(SELECT last_price*? FROM TAPARTS WHERE comp_no = ?  ");
        query.append("             AND part_id = ? )                              ");
        query.append("      )                                                     ");
        
        objects = new Object[] {
        		convertString(map.get("compNo"))
        		,convertString(map.get("woPartId"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("wcodeId"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("useQty"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("useQty"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int updateWoParts(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWOPARTS SET                                     ");
        query.append("       PART_ID            = ?                            ");
        query.append("       ,PART_GRADE        = ?                            ");
        query.append("       ,USE_QTY           = ?                            ");
        query.append("       ,REMARK            = ?                            ");
        query.append("       ,UNIT_PRICE        =                              ");
        query.append("     (SELECT last_price FROM TAPARTS WHERE comp_no = ?   ");
        query.append("             AND part_id = ? )                           ");
        query.append("       ,TOT_PRICE         =                              ");
        query.append("    (SELECT last_price* ? FROM TAPARTS WHERE comp_no = ? ");
        query.append("             AND part_id = ? )                           ");
        query.append("WHERE COMP_NO    = ?                                     ");
        query.append("  AND WKOR_ID    = ?                                     ");
        query.append("  AND WOPART_ID  = ?                                     ");
        
        objects = new Object[] {
        		convertString(map.get("partId"))
        		,convertString(map.get("partGrade"))
        		,convertString(map.get("useQty"))
        		,convertString(map.get("remark"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("useQty"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("partId"))
        		,convertString(map.get("compNo"))
        		,convertString(map.get("wkorId"))
        		,convertString(map.get("woPartId"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	public int deleteWoParts(Map map) throws Exception
    {
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAWOPARTS			");
        query.append("WHERE 1=1						");
        query.getAndQuery("comp_no", convertString(map.get("compNo")));
        query.getAndQuery("wkor_id", convertString(map.get("wkorId")));
        query.getAndQuery("wopart_id", convertString(map.get("woPartId")));
        
        return getJdbcTemplate().update(query.toString());
    }

    public List findStockQty(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String partId 		= String.valueOf(map.get("partId"));
    	String partGrade 	= String.valueOf(map.get("partGrade"));
    	String wcodeId 		= String.valueOf(map.get("wcodeId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.stock_qty stock_qty                       ");
        query.append("         ,x.bin_no bin_no                            ");
        query.append("FROM TAPTSTOCK x                                     ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.part_grade",partGrade);
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.part_id",partId);
        query.getAndQuery("x.wcode_id",wcodeId);
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    

    public List findPhotoList(Map map) throws Exception
    {
		String compNo 		= String.valueOf(map.get("compNo"));
    	String lang 		= String.valueOf(map.get("lang"));
    	String objectId 	= String.valueOf(map.get("objectId"));
    	String objectType 	= String.valueOf(map.get("objectType"));
    	String regId 		= String.valueOf(map.get("regId"));
    	String docDataId 	= String.valueOf(map.get("docDataId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.docdata_id							AS ID			");
    	query.append("		,a.comp_no								AS COMP_NO		");
    	query.append("		,a.doc_id								AS file_id		");
    	query.append("		,b.object_type							AS OBJECT_TYPE	");
    	query.append("		,'"+objectId+"'							AS OBJECT_ID	");
    	query.append("		,b.dept_id								AS DEPT_ID		");
    	query.append("		,b.reg_id								AS REG_ID		");
    	query.append("		,b.reg_date								AS REG_DATE		");
    	query.append("		,a.nf_file_path							AS FILE_PATH	");
    	query.append("		,a.file_name							AS FILE_NAME	");
    	query.append("		,a.file_ext								AS EXTENSION	");
    	query.append("		,a.file_name							AS REMARK		");
    	query.append("		,'dream/android/'+CAST(a.docdata_id AS VARCHAR(18)) AS URL	");
    	query.append("FROM TADOCDATA a INNER JOIN TADOCUMENT b						");
    	query.append("ON a.comp_no = b.comp_no										");
    	query.append("AND a.doc_id = b.doc_id										");
    	query.append("WHERE 1=1														");
    	query.getStringEqualQuery("object_type", objectType);
    	query.append("AND (a.file_ext IN (SELECT value											");
    	query.append("						FROM dbo.SPLIT_STR_TO_TABLE((select value$ from taconfig where comp_no = '"+compNo+"'		");
    	query.append("														AND name='IMG_FILE_TYPE'),',') )										");
    	query.append("		OR a.file_ext IN (SELECT value											");
    	query.append("						FROM dbo.SPLIT_STR_TO_TABLE((select value$ from taconfig where comp_no = '"+compNo+"'		");
    	query.append("														AND name='VIDEO_FILE_TYPE'),',') ) )										");
    	query.getStringEqualQuery("a.docdata_id", docDataId);
    	query.append("AND a.doc_id IN (												");
    	query.append("			SELECT doc_id										");
    	query.append("			FROM TAOBJDOC										");
    	query.append("			WHERE 1=1											");
    	query.getStringEqualQuery("comp_no", compNo);
    	query.getStringEqualQuery("object_type", objectType);
    	query.getAndQuery("object_id", objectId);
    	query.append("			)													");
    	query.append("ORDER BY file_name											");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	public String findDoc(Map map ) throws Exception
    {
		String compNo = convertString(map.get("compNo"));
		String docId = convertString(map.get("docId"));
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)				");
        query.append("FROM TADOCUMENT x				");
        query.append("WHERE 1=1						");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.doc_id",docId);
        
        return (int)getJdbcTemplate().queryForObject(query.toString(), Integer.class)+""; 
    }
}