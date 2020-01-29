package intf.dream.android.offline.macal.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.macal.dao.AnIfCalListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfCalListDAOTarget"
 * @spring.txbn id="anIfCalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfCalListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnIfCalListDAO
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
    	
    	query.append("WITH eqInfo AS (					");
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
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getAndQuery("a.emp_id", empId);
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.append("  and a.wkor_date >= '"+startDate+"'							");
    	query.append("  and a.wkor_date <=  '"+endDate+"'							");
    	
    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	//설비유형
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
    	//위치
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
        query.append("       AND a.equip_id = b.equip_id   				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
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
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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

    public List findWocalibList(Map map) throws Exception
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
        query.append("       AND a.equip_id = b.equip_id   				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.CALIB_ENV								");
        query.append("      ,x.CALIB_CORP								");
        query.append("      ,x.CALIB_TYPE								");
        query.append("      ,x.CALIB_DEVICE								");
        query.append("      ,x.CALIB_RESULT_STATUS						");
    	query.append("FROM TAWOCALIB x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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

    public List findWocalibValueList(Map map) throws Exception
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
        query.append("       AND a.equip_id = b.equip_id   				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.wocalibetcvalue_id						");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.SET_VALUE								");
        query.append("      ,x.BASIS_VALUE								");
        query.append("      ,x.BEFORE_VALUE								");
        query.append("      ,x.BEFORE_DIFF_VALUE						");
        query.append("      ,x.AFTER_VALUE								");
        query.append("      ,x.AFTER_DIFF_VALUE							");
        query.append("      ,x.REMARK									");
    	query.append("FROM TAWOCALIBETCVALUE x							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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
        query.append("       AND y.is_deleted='N'						");
        query.append("       AND y.is_last_version='Y'					");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.getDeptLevelQuery("y.usage_dept", usageDeptId, "", compNo);
		query.getStringEqualQuery("y.eqctg_type", eqctgType);
    	query.getEqLocLevelQuery("y.eqloc_id", eqlocId, "", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
        query.append("			)										");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findWocalibStdEq(Map map) throws Exception
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
        query.append("       AND a.equip_id = b.equip_id    			");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
        query.append("SELECT                                            ");
        query.append("      x.COMP_NO                                   ");
        query.append("      ,x.WOCALIBSTDEQ_ID                          ");
        query.append("      ,x.WKOR_ID                                  ");
        query.append("      ,x.CALIB_WKOR_ID                            ");
        query.append("      ,x.WO_NO                                    ");
        query.append("      ,x.EQUIP_ID                                 ");
        query.append("      ,x.DESCRIPTION                              ");
        query.append("      ,x.SERIAL_NO                                ");
        query.append("      ,x.NEXT_PLAN_DATE                           ");
        query.append("      ,x.SOPDOC_NO                                ");
    	query.append("FROM TAWOCALIBSTDEQ x								");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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

    public List findWocalibGnlValue(Map map) throws Exception
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
        query.append("       AND a.equip_id = b.equip_id   				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WOCALIBGNLVALUE_ID						");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.CALIB_POINT_TYPE							");
        query.append("      ,x.CALIB_POINT								");
        query.append("      ,x.ALLOW_VALUE								");
        query.append("      ,x.ASF_STD_VALUE							");
        query.append("      ,x.ASF_CAL_VALUE							");
        query.append("      ,x.ASF_DIFF_VALUE							");
        query.append("      ,x.ASL_STD_VALUE							");
        query.append("      ,x.ASL_CAL_VALUE							");
        query.append("      ,x.ASL_DIFF_VALUE							");
        query.append("      ,x.ORD_NO									");
        query.append("      ,x.REMARK									");
    	query.append("FROM TAWOCALIBGNLVALUE x							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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

    public List findWocalibSclValue(Map map) throws Exception
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
        query.append("       AND a.equip_id = b.equip_id   				");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
        query.append("       AND b.is_deleted='N'						");
        query.append("       AND b.is_last_version='Y'		)			");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.WOCALIBSCLVALUE_ID						");
        query.append("      ,x.WKOR_ID									");
        query.append("      ,x.LI0_VALUE								");
        query.append("      ,x.LI25_VALUE								");
        query.append("      ,x.LI50_VALUE								");
        query.append("      ,x.LI75_VALUE								");
        query.append("      ,x.LI100_VALUE								");
        query.append("      ,x.LD75_VALUE								");
        query.append("      ,x.LD50_VALUE								");
        query.append("      ,x.LD25_VALUE								");
        query.append("      ,x.LD0_VALUE								");
        query.append("      ,x.ECNTR_VALUE								");
        query.append("      ,x.EBEF_VALUE								");
        query.append("      ,x.EAFT_VALUE								");
        query.append("      ,x.ELFT_VALUE								");
        query.append("      ,x.ERIG_VALUE								");
        query.append("      ,x.DEGREE1									");
        query.append("      ,x.DEGREE2									");
        query.append("      ,x.DEGREE3									");
        query.append("      ,x.REMARK									");
    	query.append("FROM TAWOCALIBSCLVALUE x							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("AND x.wkor_id IN ( SELECT a.wkor_id				");
        query.append("FROM TAWORKORDER a								");
    	query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant = '"+plant+"'");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.getAndQuery("a.emp_id", empId);
    	query.append("  and a.wkor_date >= '"+startDate+"'				");
    	query.append("  and a.wkor_date <=  '"+endDate+"'				");

    	//부서
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	//작업그룹
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
    

    public String findNextWkorId(){
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT NEXT VALUE FOR SQAWKOR_ID		");

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
        query.append("  doc_no            = ?                ");
        query.append("WHERE doc_no        = ?                ");
        query.append("    and object_type = ?                ");
        query.append("    and comp_no     = ?                ");

        
        objects = new Object[] {
        		wkorId,
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
    	
		query.append("DECLARE @t1 TABLE(									");
		query.append("	compNo NVARCHAR(1000)								");
		query.append("	,wkorId NVARCHAR(1000)								");
		query.append("	,equipId NVARCHAR(1000)								");
		query.append("	)													");
		query.append("INSERT INTO @t1 VALUES(?,?,?)							");
		query.append("IF EXISTS(											");
		query.append("		SELECT 1										");
		query.append("		FROM TAWOEQUIP a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
		query.append("		AND 	a.wkor_id = b.wkorId					");
		query.append("		AND 	a.equip_id = b.equipId					");
		query.append("	)													");
		query.append("	BEGIN												");
		query.append("		UPDATE TAWOEQUIP SET							");
		query.append("				comp_no = b.compNo						");
		query.append("		FROM TAWOEQUIP a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
		query.append("		AND 	a.wkor_id = b.wkorId					");
		query.append("		AND 	a.equip_id = b.equipId					");
		query.append("	END													");
		query.append("ELSE													");
		query.append("	BEGIN												");
		query.append("		INSERT INTO TAWOEQUIP							");
		query.append("			(comp_no,	woequip_id						");
		query.append("			,wkor_id,	equip_id	)					");
		query.append("		SELECT b.compNo, next value for SQAWOEQUIP_ID	");
		query.append("				,b.wkorId,	b.equipId					");
		query.append("		FROM @t1 b										");
		query.append("END													");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("EQUIPID"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWocraft(Map map, String wkorId, String woCraftId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DECLARE @t1 TABLE(									");
		query.append("	compNo NVARCHAR(1000)								");
		query.append("	,wkorId NVARCHAR(1000)								");
		query.append("	,empId NVARCHAR(1000)								");
		query.append("	,startDate NVARCHAR(1000)							");
		query.append("	,startTime NVARCHAR(1000)							");
		query.append("	,endTime NVARCHAR(1000)								");
		query.append("	,workTime NVARCHAR(1000)							");
		query.append("	,remark NVARCHAR(1000)								");
		query.append("	)													");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?)				");
		query.append("IF EXISTS(											");
		query.append("		SELECT 1										");
		query.append("		FROM TAWOCRAFT a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.wkor_id = b.wkorId					");
    	query.append("		AND 	a.emp_id  = b.empId						");
		query.append("	)													");
		query.append("	BEGIN												");
		query.append("		UPDATE TAWOCRAFT SET							");
		query.append("			start_date = b.startDate,					");
    	query.append("			start_time = b.startTime,					");
    	query.append("			end_date   = b.endDate,						");
    	query.append("			end_time   = b.endTime,						");
    	query.append("			work_time  = b.workTime,					");
    	query.append("			remark     = b.remark						");
		query.append("		FROM TAWOCRAFT a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.wkor_id = b.wkorId					");
    	query.append("		AND 	a.emp_id  = b.empId						");
		query.append("	END													");
		query.append("ELSE													");
		query.append("	BEGIN												");
		query.append("		INSERT INTO TAWOCRAFT							");
		query.append("			(comp_no,		wocraft_id,			wkor_id,	emp_id, 	start_date,					");
    	query.append("			start_time,		end_date,			end_time,	work_time,	remark		)				");
		query.append("		SELECT 	b.compNo,	?,					b.wkorId,	b.empId,b.startDate,		");
    	query.append("			b.startTime,	b.endDate,			b.endTime,	b.workTime,		b.remark			");
		query.append("		FROM @t1 b										");
		query.append("END													");

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

    public int saveWocalib(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
    	
		query.append("DECLARE @t1 TABLE(									");
		query.append("	compNo NVARCHAR(1000)								");
		query.append("	,wkorId NVARCHAR(1000)								");
		query.append("	,calibEnv NVARCHAR(1000)							");
		query.append("	,calibCorp NVARCHAR(1000)							");
		query.append("	,calibType NVARCHAR(1000)							");
		query.append("	,calibDevice NVARCHAR(1000)							");
		query.append("	,calibResultStatus NVARCHAR(1000)					");
		query.append("	)													");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?)					");
		query.append("IF EXISTS(											");
		query.append("		SELECT 1										");
		query.append("		FROM TAWOCALIB a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.wkor_id = b.wkorId					");
		query.append("	)													");
		query.append("	BEGIN												");
		query.append("		UPDATE TAWOCALIB SET							");
		query.append("			CALIB_ENV           = b.calibEnv,			");
    	query.append("			CALIB_CORP          = b.calibCorp,			");
    	query.append("			CALIB_TYPE          = b.calibType,			");
    	query.append("			CALIB_DEVICE        = b.calibDevice,		");
    	query.append("			CALIB_RESULT_STATUS = b.calibResultStatus	");
		query.append("		FROM TAWOCALIB a, @t1 b							");
		query.append("		WHERE 	a.comp_no = b.compNo					");
    	query.append("		AND 	a.wkor_id = b.wkorId					");
		query.append("	END													");
		query.append("ELSE													");
		query.append("	BEGIN												");
		query.append("		INSERT INTO TAWOCALIB							");
		query.append("			(comp_no,		wkor_id,			calib_env,	calib_corp, 	calib_type,			");
    	query.append("			calib_device,	calib_result_status											)		");
		query.append("		SELECT b.compNo,	b.wkorId,			b.calibEnv,		b.calibCorp,	b.calibType,	");
    	query.append("			b.calibDevice,	b.calibResultStatus													");
		query.append("		FROM @t1 b										");
		query.append("END													");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			wkorId,
    			convertString(map.get("CALIBENV")),
    			convertString(map.get("CALIBCORP")),
    			convertString(map.get("CALIBTYPE")),
    			"",
    			convertString(map.get("CALIBRESULTSTATUS"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}

    public int saveWocalibValue(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DECLARE @t1 TABLE(											");
		query.append("	compNo NVARCHAR(1000)										");
		query.append("	,wocalibEtcValueId NVARCHAR(1000)							");
		query.append("	,wkorId NVARCHAR(1000)										");
		query.append("	,setValue NVARCHAR(1000)									");
		query.append("	,basisValue NVARCHAR(1000)									");
		query.append("	,beforeValue NVARCHAR(1000)									");
		query.append("	,beforeDiffValue NVARCHAR(1000)								");
		query.append("	,afterValue NVARCHAR(1000)									");
		query.append("	,afterDiffValue NVARCHAR(1000)								");
		query.append("	,remark NVARCHAR(1000)										");
		query.append("	)															");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?)					");
		query.append("IF EXISTS(													");
		query.append("		SELECT 1												");
		query.append("		FROM TAWOCALIBETCVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibetcvalue_id = b.wocalibEtcValueId		");
		query.append("	)															");
		query.append("	BEGIN														");
		query.append("		UPDATE TAWOCALIBETCVALUE SET							");
		query.append("				SET_VALUE           = b.setValue,				");
    	query.append("				BASIS_VALUE         = b.basisValue,				");
    	query.append("				BEFORE_VALUE        = b.beforeValue,			");
    	query.append("				BEFORE_DIFF_VALUE   = b.beforeDiffValue,		");
    	query.append("				AFTER_VALUE         = b.afterValue,				");
    	query.append("				AFTER_DIFF_VALUE    = b.afterDiffValue,			");
    	query.append("				REMARK              = b.remark					");
		query.append("		FROM TAWOCALIBETCVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibetcvalue_id = b.wocalibEtcValueId		");
		query.append("	END															");
		query.append("ELSE															");
		query.append("	BEGIN														");
		query.append("		INSERT INTO TAWOCALIBETCVALUE							");
		query.append("			(comp_no,		wocalibetcvalue_id,		wkor_id,		SET_VALUE, 			BASIS_VALUE,		");
    	query.append("			BEFORE_VALUE,	BEFORE_DIFF_VALUE,		AFTER_VALUE,	AFTER_DIFF_VALUE,	REMARK	)			");
		query.append("		SELECT b.compNo,	next value for SQAWOCALIBETCVALUE_ID,	b.wkorId,	b.setValue,	b.basisValue,	");
    	query.append("			b.beforeValue,	b.beforeDiffValue,			b.afterValue,	b.afterDiffValue,	b.remark		");
		query.append("		FROM @t1 b												");
		query.append("END															");
		
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOCALIBVALUEID")),
    			wkorId,
    			convertString(map.get("SETVALUE")),
    			convertString(map.get("BASISVALUE")),
    			convertString(map.get("BEFOREVALUE")),
    			convertString(map.get("BEFOREDIFFVALUE")),
    			convertString(map.get("AFTERVALUE")),
    			convertString(map.get("AFTERDIFFVALUE")),
    			convertString(map.get("REMARK"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));

	}
    
public int saveWocalibStdEq(Map map, String wkorId, String sqawocalibstdeq_id) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("DECLARE @t1 TABLE(											");
		query.append("	compNo NVARCHAR(1000)										");
		query.append("	,woCalibStdEqId NVARCHAR(1000)								");
		query.append("	,wkorId NVARCHAR(1000)										");
		query.append("	,calibWkorId NVARCHAR(1000)									");
		query.append("	,woNo NVARCHAR(1000)										");
		query.append("	,equipId NVARCHAR(1000)										");
		query.append("	,description NVARCHAR(1000)									");
		query.append("	,serialNo NVARCHAR(1000)									");
		query.append("	,nextPlanDate NVARCHAR(1000)								");
		query.append("	,sopDocNo NVARCHAR(1000)									");
		query.append("	)															");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,								");
		query.append("(SELECT																										");
	 	query.append("         ISNULL(first_value(wocal.calib_cert_no) OVER (ORDER BY wo.wkor_date DESC), first_value(wo.wkor_id) OVER (ORDER BY wo.wkor_date DESC))   ");
	 	query.append("         FROM TAWORKORDER wo INNER JOIN TAWOEQUIP woeq    													");
	 	query.append("         	ON wo.comp_no = woeq.comp_no   																		");
	 	query.append("         	AND wo.wkor_id = woeq.wkor_id   																	");
	 	query.append("         INNER JOIN TAWOCALIB wocal    																		");
	 	query.append("         	ON wo.comp_no = wocal.comp_no   																	");
	 	query.append("         	AND wo.wkor_id = wocal.wkor_id   																	");
	 	query.append("         WHERE  woeq.equip_id = ?																				");
	 	query.append("           AND wo.comp_no = ?																					");
	 	query.append("           AND wo.wo_status ='C'																				");
	 	query.append("        ),																									");
		query.append("			?,?,																								");
	 	query.append("        (SELECT x.serial_no FROM TAEQUIPMENT x WHERE x.comp_no = ? AND x.equip_id = ? ),  					");
    	query.append("			(SELECT        																						");
        query.getDate("first_Value(wocal.next_calib_date) OVER (ORDER BY wo.wkor_date DESC)", "next_calib_date");
	 	query.append("         FROM TAWORKORDER wo INNER JOIN TAWOEQUIP woeq    													");
	 	query.append("         	ON wo.comp_no = woeq.comp_no   																		");
	 	query.append("         	AND wo.wkor_id = woeq.wkor_id   																	");
	 	query.append("         INNER JOIN TAWOCALIB wocal    																		");
	 	query.append("         	ON wo.comp_no = wocal.comp_no   																	");
	 	query.append("         	AND wo.wkor_id = wocal.wkor_id   																	");
	 	query.append("         WHERE  woeq.equip_id = ?																				");
	 	query.append("           AND wo.comp_no = ?																					");
	 	query.append("           AND wo.wo_status ='C'																				");
	 	query.append("        ),																									");
		query.append("					?)											");
		query.append("IF EXISTS(													");
		query.append("		SELECT 1												");
		query.append("		FROM TAWOCALIBSTDEQ a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibstdeq_id = b.woCalibStdEqId			");
    	query.append("		AND 	a.sopdoc_no = b.sopDocNo						");
		query.append("	)															");
		query.append("	BEGIN														");
		query.append("		UPDATE TAWOCALIBSTDEQ SET								");
		query.append("				WO_NO               = b.wkorId,					");
    	query.append("				EQUIP_ID            = b.equipId,				");
    	query.append("				DESCRIPTION         = b.description,			");
    	query.append("				SOPDOC_NO           = b.sopDocNo				");
		query.append("		FROM TAWOCALIBSTDEQ a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibstdeq_id = b.woCalibStdEqId			");
		query.append("	END															");
		query.append("ELSE															");
		query.append("	BEGIN														");
		query.append("		INSERT INTO TAWOCALIBSTDEQ								");
		query.append("			(comp_no,	wocalibstdeq_id,	wkor_id,	calib_wkor_id, 		wo_no,					");
    	query.append("			equip_id,	description,		serial_no,	next_plan_date,	sopdoc_no	)				");
		query.append("		SELECT b.compNo, ?,	b.wkorId,	b.calibWkorId,	b.wkorId,									");
    	query.append("			b.equipId,	b.description,		b.serialNo,		b.nextPlanDate, b.sopDocNo				");
		query.append("		FROM @t1 b												");
		query.append("END															");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOCALIBSTDEQID")),
    			wkorId,
    			"",
    			convertString(map.get("EQUIPID")), //성적서
    			convertString(map.get("COMPNO")),  //성적서
    			convertString(map.get("EQUIPID")),
    			convertString(map.get("DESCRIPTION")),
    			convertString(map.get("COMPNO")),		//serial_no
    			convertString(map.get("EQUIPID")),		//serial_no
    			convertString(map.get("EQUIPID")), //next date
    			convertString(map.get("COMPNO")),  //next date
    			convertString(map.get("SOPDOCNO")),
    			sqawocalibstdeq_id
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

public int updateCalStdEqNextDateList(Map map, String wkorId, String sqawocalibstdeq_id)
{
	QuerySqlBuffer query = new QuerySqlBuffer();
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
	query.append("	and x.pmsched_status IN ('IRWDA','S','PRW')				");
	query.append("	)													");
	
	query.append("WHERE comp_no    			= ?                            ");
	query.append("  AND wocalibstdeq_id     = ?                            ");
	
	
	
	objects = new Object[] {
			convertString(map.get("COMPNO"))
			,convertString(map.get("EQUIPID"))
			,DateUtil.getDate()
			,convertString(map.get("COMPNO"))
			,sqawocalibstdeq_id
	};
	
	return getJdbcTemplate().update(query.toString(), getObject(objects));
}
public int saveWocalibGnlValue(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DECLARE @t1 TABLE(											");
		query.append("	compNo NVARCHAR(1000)										");
		query.append("	,woCalibGnlValueId NVARCHAR(1000)							");
		query.append("	,wkorId NVARCHAR(1000)										");
		query.append("	,calibPointType NVARCHAR(1000)								");
		query.append("	,calibPoint NVARCHAR(1000)									");
		query.append("	,allowValue NVARCHAR(1000)									");
		query.append("	,asfStdValue NVARCHAR(1000)									");
		query.append("	,asfCalValue NVARCHAR(1000)									");
		query.append("	,asfDiffValue NVARCHAR(1000)								");
		query.append("	,aslStdValue NVARCHAR(1000)									");
		query.append("	,aslCalValue NVARCHAR(1000)									");
		query.append("	,aslDiffValue NVARCHAR(1000)								");
		query.append("	,ordNo NVARCHAR(1000)										");
		query.append("	,remark NVARCHAR(1000)										");
		query.append("	)															");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)					");
		query.append("IF EXISTS(													");
		query.append("		SELECT 1												");
		query.append("		FROM TAWOCALIBGNLVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibgnlvalue_id = b.woCalibGnlValueId		");
		query.append("	)															");
		query.append("	BEGIN														");
		query.append("		UPDATE TAWOCALIBGNLVALUE SET							");
		query.append("				calib_point_type    = b.calibPointType,			");
    	query.append("				calib_point         = b.calibPoint,				");
    	query.append("				allow_value         = b.allowValue,				");
    	query.append("				asf_std_value       = b.asfStdValue,			");
    	query.append("				asf_cal_value       = b.asfCalValue,			");
    	query.append("				asf_diff_value      = b.asfDiffValue,			");
    	query.append("				asl_std_value       = b.aslStdValue,			");
    	query.append("				asl_cal_value       = b.aslCalValue,			");
    	query.append("				asl_diff_value      = b.aslDiffValue,			");
    	query.append("				ord_no              = b.ordNo,					");
    	query.append("				remark              = b.remark					");
		query.append("		FROM TAWOCALIBGNLVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibgnlvalue_id = b.woCalibGnlValueId		");
		query.append("	END															");
		query.append("ELSE															");
		query.append("	BEGIN														");
		query.append("		INSERT INTO TAWOCALIBGNLVALUE							");
		query.append("			(comp_no,		wocalibgnlvalue_id,	wkor_id,		calib_point_type, 	calib_point,				");
    	query.append("			allow_value,	asf_std_value,		asf_cal_value,	asf_diff_value,		asl_std_value,				");
    	query.append("			asl_cal_value,	asl_diff_value,		ord_no,			remark	)										");
		query.append("		SELECT b.compNo,	next value for sqawocalibgnlvalue_id,	b.wkorId,b.calibPointType,	b.calibPoint,	");
    	query.append("			b.allowValue,	b.asfStdValue,			b.asfCalValue,	b.asfDiffValue, b.aslStdValue,				");
    	query.append("			b.aslCalValue,	b.aslDiffValue,			b.ordNo,	b.remark										");
		query.append("		FROM @t1 b												");
		query.append("END															");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOCALIBGNLVALUEID")),
    			wkorId,
    			convertString(map.get("CALIBPOINTTYPE")),
    			convertString(map.get("CALIBPOINT")),
    			convertString(map.get("ALLOWVALUE")),
    			convertString(map.get("ASFSTDVALUE")),
    			convertString(map.get("ASFCALVALUE")),
    			convertString(map.get("ASFDIFFVALUE")),
    			convertString(map.get("ASLSTDVALUE")),
    			convertString(map.get("ASLCALVALUE")),
    			convertString(map.get("ASLDIFFVALUE")),
    			convertString(map.get("ORDNO")),
    			convertString(map.get("REMARK"))
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

public int saveWocalibSclValue(Map map, String wkorId) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("DECLARE @t1 TABLE(											");
		query.append("	compNo NVARCHAR(1000)										");
    	query.append("	,woCalibSclValueId NVARCHAR(1000)							");
    	query.append("	,wkorId NVARCHAR(1000)										");
    	query.append("	,li0Value NVARCHAR(1000)									");
    	query.append("	,li25Value NVARCHAR(1000)									");
    	query.append("	,li50Value NVARCHAR(1000)									");
    	query.append("	,li75Value NVARCHAR(1000)									");
    	query.append("	,li100Value NVARCHAR(1000)									");
    	query.append("	,ld75Value NVARCHAR(1000)									");
    	query.append("	,ld50Value NVARCHAR(1000)									");
    	query.append("	,ld25Value NVARCHAR(1000)									");
    	query.append("	,ld0Value NVARCHAR(1000)									");
    	query.append("	,ecntrValue NVARCHAR(1000)									");
    	query.append("	,ebefValue NVARCHAR(1000)									");
    	query.append("	,eaftValue NVARCHAR(1000)									");
    	query.append("	,elftValue NVARCHAR(1000)									");
    	query.append("	,erigValue NVARCHAR(1000)									");
    	query.append("	,degree1 NVARCHAR(1000)										");
    	query.append("	,degree2 NVARCHAR(1000)										");
    	query.append("	,degree3 NVARCHAR(1000)										");
    	query.append("	,remark NVARCHAR(1000)										");
		query.append("	)															");
		query.append("INSERT INTO @t1 VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)	");
		query.append("IF EXISTS(													");
		query.append("		SELECT 1												");
		query.append("		FROM TAWOCALIBSCLVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibsclvalue_id = b.woCalibSclValueId		");
		query.append("	)															");
		query.append("	BEGIN														");
		query.append("		UPDATE TAWOCALIBSCLVALUE SET							");
		query.append("				li0_value    = b.li0Value,						");
    	query.append("				li25_value   = b.li25Value,						");
    	query.append("				li50_value   = b.li50Value,						");
    	query.append("				li75_value   = b.li75Value,						");
    	query.append("				li100_value  = b.li100Value,					");
    	query.append("				ld75_value   = b.ld75Value,						");
    	query.append("				ld50_value   = b.ld50Value,						");
    	query.append("				ld25_value   = b.ld25Value,						");
    	query.append("				ld0_value    = b.ld0Value,						");
    	query.append("				ecntr_value  = b.ecntrValue,					");
    	query.append("				ebef_value   = b.ebefValue,						");
    	query.append("				eaft_value   = b.eaftValue,						");
    	query.append("				elft_value   = b.elftValue,						");
    	query.append("				erig_value   = b.erigValue,						");
    	query.append("				degree1      = b.degree1,						");
    	query.append("				degree2      = b.degree2,						");
    	query.append("				degree3      = b.degree3,						");
    	query.append("				remark       = b.remark							");
		query.append("		FROM TAWOCALIBSCLVALUE a, @t1 b							");
		query.append("		WHERE 	a.comp_no         = b.compNo					");
    	query.append("		AND 	a.wkor_id         = b.wkorId					");
    	query.append("		AND 	a.wocalibsclvalue_id = b.woCalibSclValueId		");
		query.append("	END															");
		query.append("ELSE															");
		query.append("	BEGIN														");
		query.append("		INSERT INTO TAWOCALIBSCLVALUE							");
		query.append("			(comp_no,		wocalibsclvalue_id,	wkor_id,		li0_value, 	li25_value,							");
    	query.append("			li50_value,	li75_value,			li100_value,	ld75_value,	ld50_value,								");
    	query.append("			ld25_value,	ld0_value,			ecntr_value,	ebef_value,	eaft_value,								");
    	query.append("			elft_value,	erig_value,			degree1,		degree2,		degree3,							");
    	query.append("			remark	)																							");
		query.append("		SELECT b.compNo,	next value for sqawocalibsclvalue_id,	b.wkorId,b.li0Value,	b.li25Value,		");
    	query.append("			b.li50Value,	b.li75Value,			b.li100Value,	b.ld75Value, 	b.ld50Value,				");
    	query.append("			b.ld25Value,	b.ld0Value,				b.ecntrValue,	b.ebefValue, 	b.eaftValue,				");
    	query.append("			b.elftValue,	b.erigValue,			b.degree1,		b.degree2, 		b.degree3,					");
    	query.append("			b.remark											");
		query.append("		FROM @t1 b												");
		query.append("END															");
    	
    	Object[] objects = new Object[] {
    			convertString(map.get("COMPNO")),
    			convertString(map.get("WOCALIBSCLVALUE_ID")),
    			wkorId,
    			convertString(map.get("LI0_VALUE")),
    			convertString(map.get("LI25_VALUE")),
    			convertString(map.get("LI50_VALUE")),
    			convertString(map.get("LI75_VALUE")),
    			convertString(map.get("LI100_VALUE")),
    			convertString(map.get("LD75_VALUE")),
    			convertString(map.get("LD50_VALUE")),
    			convertString(map.get("LD25_VALUE")),
    			convertString(map.get("LD0_VALUE")),
    			convertString(map.get("ECNTR_VALUE")),
    			convertString(map.get("EBEF_VALUE")),
    			convertString(map.get("EAFT_VALUE")),
    			convertString(map.get("ELFT_VALUE")),
    			convertString(map.get("ERIG_VALUE")),
    			convertString(map.get("DEGREE1")),
    			convertString(map.get("DEGREE2")),
    			convertString(map.get("DEGREE3")),
    			convertString(map.get("REMARK"))
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
	
	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
}
}