package intf.dream.ant.pmwork.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.pmwork.dao.AntPmworkListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antPmworkListDAOTarget"
 * @spring.txbn id="antPmworkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntPmworkListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AntPmworkListDAO
{
	public List findHdrList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 
    	
    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
    	query.append("WITH eqInfo AS (				");
        query.append("    SELECT a.equip_id,			");
        query.append("           a.wkor_id,				");
        query.append("           b.item_no,				");
        query.append("           a.comp_no,				");
        query.append("           b.sub_mng_id,			");
        query.append("           b.eqctg_id,			");
        query.append("           b.eqloc_id,			");
        query.append("           b.old_eq_no,			");
        query.append("           b.description ,		");
        query.append("           b.eqctg_type			");
        query.append("FROM  TAWOEQUIP a, TAEQUIPMENT b	");
        query.append("     WHERE a.comp_no = b.comp_no	");
        query.append("     AND a.equip_id = b.equip_id	");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("     AND b.is_deleted='N'			");
        query.append("     AND b.is_last_version='Y')	");
    	query.append("SELECT a.COMP_NO					");
    	query.append("		,a.WKOR_ID					");
    	query.append("		,a.WO_NO					");
    	query.append("		,a.DESCRIPTION				");
    	query.append("		,a.WO_STATUS				");
    	query.append("		,a.WO_TYPE					");
    	query.append("		,a.PM_TYPE					");
    	query.append("		,a.START_DATE				");
    	query.append("		,a.START_TIME				");
    	query.append("		,a.END_DATE					");
    	query.append("		,a.END_TIME					");
    	query.append("		,a.WORK_TIME				");
    	query.append("		,a.DEPT_ID					");
    	query.append("		,a.EMP_ID					");
    	query.append("		,a.PERFORM					");
    	query.append("		,a.PM_ID					");
    	query.append("		,a.PMSCHED_ID				");
    	query.append("		,a.WO_GEN_TYPE				");
    	query.append("		,a.WOPOINT_ID				");
    	query.append("		,a.UPD_DATE					");
    	query.append("		,a.UPD_BY					");
    	query.append("		,a.SELF_VENDOR_TYPE			");
    	query.append("		,a.VENDOR_ID				");
    	query.append("		,a.P_WKOR_ID				");
    	query.append("		,a.WKOR_DATE				");
    	query.append("		,a.SHIFT_TYPE				");
    	query.append("		,a.TOT_AMT					");
    	query.append("		,a.PMACTION					");
    	query.append("		,a.EQLOC_ID					");
    	query.append("		,a.WKCTR_ID					");
    	query.append("		,a.CLOSE_ID					");
    	query.append("		,a.CLOSE_DATE				");
    	query.append("		,'' SUPUSER_ID				");
    	query.append("FROM TAWORKORDER a				");
    	query.append("WHERE 1=1							");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.append("AND a.plant = '"+plant+"'			");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(AntCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	query.append("  and a.wkor_date >= '"+startDate+"'							");
    	query.append("  and a.wkor_date <=  '"+endDate+"'							");
    	query.getAndQuery("a.emp_id", empId);
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
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findWocraftList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
    	query.append("WITH eqInfo AS (									");
        query.append("    SELECT a.equip_id,							");
        query.append("           a.wkor_id,								");
        query.append("           b.item_no,								");
        query.append("           a.comp_no,								");
        query.append("           b.sub_mng_id,							");
        query.append("           b.eqctg_id,							");
        query.append("           b.eqloc_id,							");
        query.append("           b.old_eq_no,							");
        query.append("           b.description ,						");
        query.append("           b.eqctg_type							");
        query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b				");
        query.append("     WHERE a.comp_no = b.comp_no					");
        query.append("       AND a.equip_id = b.equip_id				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y')					");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WOCRAFT_ID								");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.EMP_ID									");
        query.append("      ,x.START_DATE								");
        query.append("      ,x.START_TIME								");
        query.append("      ,x.END_DATE									");
        query.append("      ,x.END_TIME									");
        query.append("      ,x.WORK_TIME								");
        query.append("      ,x.REMARK									");
    	query.append("FROM TAWOCRAFT x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.append("AND a.plant = '"+plant+"'							");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(AntCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

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
        query.append("			)										");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findWopartsList(Map map) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
    	query.append("WITH eqInfo AS (								");
        query.append("    SELECT a.equip_id,							");
        query.append("           a.wkor_id,								");
        query.append("           b.item_no,								");
        query.append("           a.comp_no,								");
        query.append("           b.sub_mng_id,							");
        query.append("           b.eqctg_id,							");
        query.append("           b.eqloc_id,							");
        query.append("           b.old_eq_no,							");
        query.append("           b.description ,						");
        query.append("           b.eqctg_type							");
        query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b				");
        query.append("     WHERE a.comp_no = b.comp_no					");
        query.append("       AND a.equip_id = b.equip_id				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y')					");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WOPART_ID								");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.WCODE_ID									");
        query.append("      ,x.PART_ID									");
        query.append("      ,x.PART_GRADE								");
        query.append("      ,x.USE_QTY									");
        query.append("      ,x.PTREPAIRLIST_ID							");
        query.append("      ,x.REMARK									");
        query.append("      ,x.UNIT_PRICE								");
        query.append("      ,x.TOT_PRICE								");
        query.append("      ,x.PTISSLIST_ID								");
    	query.append("FROM TAWOPARTS x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.append("AND a.plant = '"+plant+"'			");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(AntCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

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
        query.append("			)										");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findWoequipList(Map map) throws Exception
    { 

    	QuerySqlBuffer query = new QuerySqlBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
    	
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WOEQUIP_ID								");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.EQUIP_ID									");
        query.append("      ,x.EQCTG_ID									");
        query.append("      ,x.DESCRIPTION								");
        query.append("      ,x.WORK_TIME								");
        query.append("      ,x.REMARK									");
        query.append("     FROM  TAWOEQUIP x, TAEQUIPMENT y				");
        query.append("     WHERE x.comp_no  = y.comp_no					");
        query.append("       AND x.equip_id = y.equip_id				");
        query.append("       AND y.is_last_version='Y'					");
        query.append("       AND y.is_deleted='N'						");
    	query.getStringEqualQuery("x.comp_no", compNo);
		query.getStringEqualQuery("y.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("y.eqloc_id", eqlocId, "", compNo);
    	query.getDeptLevelQuery("y.usage_dept", usageDeptId, "", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.plant = '"+plant+"'							");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(AntCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
        query.append("			)										");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    

    public String findNextWkorId(){
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT next value for SQAWKOR_ID       ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
    public int updateWorkOrder(Map map)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TAWORKORDER SET                    ");
        query.append("  pm_type          = ?,                   ");
        query.append("  wkor_date        = ?,                   ");
        query.append("  start_date       = ?,                   ");
        query.append("  start_time       = ?,                   ");
        query.append("  end_date         = ?,                   ");
        query.append("  end_time         = ?,                   ");
        query.append("  shift_type       = ?,                   ");
        query.append("  emp_id           = ?,                   ");
        query.append("  perform          = ?,                   ");
        query.append("  upd_date         = CONVERT(VARCHAR, GETDATE(), 112),");
        query.append("  upd_by           = ?,                   ");
        query.append("  wkctr_id         = ?,                   ");
        query.append("  work_time        = ?,                   ");
        query.append("  wo_status        = ?                    ");
        query.append("WHERE wkor_id      = ?                    ");
        query.append("    and comp_no    = ?                    ");

        
        objects = new Object[] {
        		convertString(map.get("pmType")),
        		convertString(map.get("wkorDate")),
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
        		convertString(map.get("endDate")),
        		convertString(map.get("endTime")),
        		convertString(map.get("shiftType")),
        		convertString(map.get("empId")),
        		convertString(map.get("perform")),
                convertString(map.get("userId")),
                convertString(map.get("wkctrId")),
                convertString(map.get("workTime")),
                "PRW",
                convertString(map.get("wkorId")),
                convertString(map.get("compNo"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertWorkOrder(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("INSERT INTO TAWORKORDER(                                      ");
        query.append("     COMP_NO, WKOR_ID, WO_NO,                                 ");
        query.append("     DESCRIPTION, WO_STATUS, WO_TYPE, PM_TYPE,                ");
        query.append("     START_DATE, START_TIME, END_DATE, END_TIME,              ");
        query.append("     WORK_TIME, DEPT_ID, EMP_ID, PERFORM,                     ");
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
        query.append("         )                                                    ");
        
        objects = new Object[] {
        		convertString(map.get("compNo")),
        		wkorId,
        		wkorId,
        		convertString(map.get("woDesc")),
        		"PRW",
        		convertString(map.get("woType")),
        		convertString(map.get("pmType")),
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
                convertString(map.get("endDate")),
                convertString(map.get("endTime")),
                convertString(map.get("workTime")),
                convertString(map.get("deptId")),
                convertString(map.get("empId")),
                convertString(map.get("perform")),
                "",
                "",
                convertString(map.get("woGenType")),
                "",
                convertString(map.get("updDate")),
                convertString(map.get("updBy")),
                "",
                "",
                "",
        		convertString(map.get("wkorDate")),
        		convertString(map.get("shiftType")),
                "",
                "",
                "",
        		convertString(map.get("wkctrId")),
                "",
                "",
                "N",
                convertString(map.get("plant"))
        };

        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int insertWorkOrderLog(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects = null;
 
        query.append("INSERT INTO TXPDAWORKORDER                                    ");
        query.append("  (comp_no,       txpdaworkorder_id,                          ");
        query.append("   cre_date,      cre_time,                                   ");
        query.append("   mobins_yn,     mobins_wkor_id,                             ");
        query.append("   wkor_id,       wo_no,                                      ");
        query.append("   wo_title,      dept_no,                                    ");
        query.append("   dept_name,     emp_no,                                     ");
        query.append("   emp_name,      item_no,                                    ");
        query.append("   item_name,     start_date,                                 ");
        query.append("   start_time,    end_date,                                   ");
        query.append("   end_time,      ca_code,                                    ");
        query.append("   ca_name,       mo_code,                                    ");
        query.append("   mo_name,       re_code,                                    ");
        query.append("   re_name,       perform, user_no ,wo_type, pm_type          ");
        query.append("  )   VALUES                                                  ");
        query.append("  (?,             next value for SQATXPDAWORKORDER_ID,        ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?,                                          ");
        query.append("   ?,             ?, ? ,?,?                                   ");
        query.append("  )                                                           ");
        
        objects = new Object[] {
                convertString(map.get("compNo")),
                DateUtil.getDate(),
                DateUtil.getDateTime("HH")+""+DateUtil.getDateTime("mm"),
                "Y",
                convertString(map.get("wkorId")),
                wkorId,
                wkorId,
                convertString(map.get("woDesc")),
                convertString(map.get("deptId")),
                "",
                convertString(map.get("empId")),
                "",
                "",
                "",
        		convertString(map.get("startDate")),
        		convertString(map.get("startTime")),
                convertString(map.get("endDate")),
                convertString(map.get("endTime")),
                "",
                "",
                "",
                "",
                "",
                "",
                convertString(map.get("perform")),
                convertString(map.get("userNo")),
                convertString(map.get("woType")),
                convertString(map.get("pmType"))
        };
            
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int changeFileSeq(Map map, String wkorId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        Object[] objects;

        query.append("UPDATE TADOCUMENT SET                  ");
        query.append("  doc_no            = ?,               ");
        query.append("  remark            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		wkorId,
        		convertString(map.get("remark")),
                convertString(map.get("wkorId")),
                "WORESULT",
                convertString(map.get("compNo"))
        };
        getJdbcTemplate().update(query.toString(), getObject(objects));
        
        query = new QuerySqlBuffer();
        query.append("UPDATE TAOBJDOC SET                   ");
        query.append("  object_id           = ?             ");
        query.append("WHERE object_id       = ?             ");
        query.append("    and object_type   = ?             ");
        query.append("    and comp_no       = ?             ");

        
        objects = new Object[] {
        		wkorId,
                convertString(map.get("wkorId")),
                "WORESULT",
                convertString(map.get("compNo"))
        };
        
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int saveWoequip(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,equipId NVARCHAR(1000)											");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?)										");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOEQUIP a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.equip_id = b.equipId								");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOEQUIP SET										");
		query.append("				comp_no = b.compNo									");
		query.append("		FROM TAWOEQUIP a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.equip_id = b.equipId								");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOEQUIP										");
		query.append("			(comp_no,	woequip_id,	wkor_id,	equip_id		)	");
		query.append("		SELECT b.compNo, next value for SQAWOEQUIP_ID,	b.wkorId,	b.equipId	");
		query.append("		FROM @t1 b													");
		query.append("END																");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EQUIPID"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWocraft(Map map, String wkorId, String woCraftId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,empId NVARCHAR(1000)											");
		query.append("	,startDate NVARCHAR(1000)										");
		query.append("	,startTime NVARCHAR(1000)										");
		query.append("	,endDate NVARCHAR(1000)											");
		query.append("	,endTime NVARCHAR(1000)											");
		query.append("	,workTime NVARCHAR(1000)										");
		query.append("	,remark NVARCHAR(1000)											");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?)								");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOCRAFT a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.emp_id  = b.empId									");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOCRAFT SET										");
		query.append("			start_date = b.startDate,								");
    	query.append("			start_time = b.startTime,								");
    	query.append("			end_date   = b.endDate,									");
    	query.append("			end_time   = b.endTime,									");
    	query.append("			work_time  = b.workTime,								");
    	query.append("			remark     = b.remark									");
		query.append("		FROM TAWOCRAFT a, @t1 b										");
		query.append("		WHERE 	a.comp_no = b.compNo								");
    	query.append("		AND 	a.wkor_id = b.wkorId								");
    	query.append("		AND 	a.emp_id  = b.empId									");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOCRAFT										");
		query.append("			(comp_no,		wocraft_id,			wkor_id,	emp_id, 		start_date,		");
    	query.append("			start_time,	end_date,				end_time,	work_time,	remark	)			");
		query.append("		SELECT b.compNo,	?,					b.wkorId,	b.empId,		b.startDate,	");
    	query.append("			b.startTime,	b.endDate,			b.endTime,	b.workTime,		b.remark		");
		query.append("		FROM @t1 b													");
		query.append("END																");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EMPID")),
    			convertString(map.get("STARTDATE")),
    			convertString(map.get("STARTTIME")),
    			convertString(map.get("ENDDATE")),
    			convertString(map.get("ENDTIME")),
    			convertString(map.get("WORKTIME")),
    			convertString(map.get("REMARK")),
    			woCraftId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWoparts(Map map, String wkorId, String woPartId) {
		
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	

		query.append("DECLARE @t1 TABLE(												");
		query.append("	compNo NVARCHAR(1000)											");
		query.append("	,wopartId NVARCHAR(1000)										");
		query.append("	,wkorId NVARCHAR(1000)											");
		query.append("	,wcodeId NVARCHAR(1000)											");
		query.append("	,partId NVARCHAR(1000)											");
		query.append("	,partGrade NVARCHAR(1000)										");
		query.append("	,useQty NVARCHAR(1000)											");
		query.append("	,remark NVARCHAR(1000)											");
		query.append("	,unitPrice NVARCHAR(1000)										");
		query.append("	,totPrice NVARCHAR(1000)										");
		query.append("	)																");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?)						");
		query.append("IF EXISTS(														");
		query.append("		SELECT 1													");
		query.append("		FROM TAWOPARTS a, @t1 b										");
		query.append("		WHERE 	a.comp_no   = b.compNo								");
    	query.append("		AND 	a.wopart_id = b.wopartId							");
    	query.append("		AND 	a.wkor_id  = b.wkorId								");
		query.append("	)																");
		query.append("	BEGIN															");
		query.append("		UPDATE TAWOPARTS SET										");
		query.append("			wcode_id    = b.wcodeId,								");
    	query.append("			part_id     = b.partId,									");
    	query.append("			part_grade  = b.partGrade,								");
    	query.append("			use_qty     = b.useQty,									");
    	query.append("			remark      = b.remark,									");
    	query.append("			unit_price  = CAST(b.unitPrice AS numeric),				");
    	query.append("			tot_price   = CAST(b.totPrice AS numeric)				");
		query.append("		FROM TAWOPARTS a, @t1 b										");
		query.append("		WHERE 	a.comp_no   = b.compNo								");
    	query.append("		AND 	a.wopart_id = b.wopartId							");
    	query.append("		AND 	a.wkor_id  = b.wkorId								");
		query.append("	END																");
		query.append("ELSE																");
		query.append("	BEGIN															");
		query.append("		INSERT INTO TAWOPARTS										");
		query.append("			(comp_no,		wopart_id,			wkor_id,	wcode_id,		part_id,	");
    	query.append("			part_grade,	use_qty,				remark,	unit_price,	tot_price	)		");
		query.append("		SELECT b.compNo,	?,					b.wkorId,	b.wcodeId,		b.partId,	");
    	query.append("			b.partGrade,	b.useQty,			b.remark,	b.unitPrice,	b.totPrice	");
		query.append("		FROM @t1 b													");
		query.append("END																");

		Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOPARTID")),
    			wkorId,
    			convertString(map.get("WCODEID")),
    			convertString(map.get("PARTID")),
    			convertString(map.get("PARTGRADE")),
    			convertString(map.get("USEQTY")),
    			convertString(map.get("REMARK")),
    			convertString(map.get("UNITPRICE")),
    			convertString(map.get("TOTPRICE")),
    			woPartId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

	@Override
	public String getWoStatusOfWorkOrder(Map map) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT wo_status		");
		query.append("FROM TAWORKORDER		");
		query.append("WHERE comp_no = ?		");
		query.append("AND wkor_id = ?		");
		
		Object[] objects = new Object[] {
				convertString(map.get("compNo")),
				convertString(map.get("wkorId"))
		};
		
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
}
    
    
}