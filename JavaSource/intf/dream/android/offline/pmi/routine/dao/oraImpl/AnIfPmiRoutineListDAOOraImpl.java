package intf.dream.android.offline.pmi.routine.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.pmi.routine.dao.AnIfPmiRoutineListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anIfPmiRoutineListDAOTarget"
 * @spring.txbn id="anIfPmiRoutineListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnIfPmiRoutineListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnIfPmiRoutineListDAO
{
	public List findSchedList(Map map)
    {
    	QueryBuffer query = new QueryBuffer(); 
    	
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
        query.append("           a.pm_id,				");
        query.append("           b.item_no,				");
        query.append("           a.comp_no,				");
        query.append("           b.sub_mng_id,			");
        query.append("           b.eqctg_id,			");
        query.append("           b.eqloc_id,			");
        query.append("           b.old_eq_no,			");
        query.append("           b.description ,		");
        query.append("           b.eqctg_type			");
        query.append("FROM  TAPMEQUIP a, TAEQUIPMENT b	");
        query.append("     WHERE a.comp_no = b.comp_no	");
        query.append("     AND a.equip_id = b.equip_id	");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
        query.append("     AND b.is_last_version='Y')	");
    	query.append("SELECT a.COMP_NO					");
    	query.append("		,a.PMINSSCHED_ID			");
    	query.append("		,a.PM_ID					");
    	query.append("		,c.PMEQUIP_ID				");
    	query.append("		,a.EQUIP_ID					");
    	query.append("		,c.PLAN_INIT_DATE			");
    	query.append("		,c.PLAN_DATE				");
    	query.append("		,a.WKOR_DATE AS SCHED_DATE	");
    	query.append("		,a.PMINSLIST_ID				");
    	query.append("		,a.PMSCHED_STATUS			");
    	query.append("		,c.ACTUAL_DATE				");
    	query.append("		,c.ACTUAL_TIME				");
    	query.append("		,c.CHECK_BY					");
    	query.append("		,c.MEASURE_TIME				");
    	query.append("FROM TAPMINSLIST a, TAPMLST b, TAPMINSSCHED c	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.append("and a.comp_no = c.comp_no ");
    	query.append("and a.pminssched_id = c.pminssched_id ");
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
    	query.getStringEqualQuery("c.is_deleted", "N");
    	query.append("AND b.plant='"+plant+"'			");
		query.append("AND c.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "RINS");
    	query.append("  and a.WKOR_DATE >= '"+startDate+"'																										");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
        }
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!eqctgType.equals("null") && !"".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!eqlocId.equals("null") && !"".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findPmlstList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 

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
        query.append("           a.pm_id,								");
        query.append("           b.item_no,								");
        query.append("           a.comp_no,								");
        query.append("           b.sub_mng_id,							");
        query.append("           b.eqctg_id,							");
        query.append("           b.eqloc_id,							");
        query.append("           b.old_eq_no,							");
        query.append("           b.description ,						");
        query.append("           b.eqctg_type							");
        query.append("FROM  TAPMEQUIP a, TAEQUIPMENT b					");
        query.append("     WHERE a.comp_no = b.comp_no					");
        query.append("     AND a.equip_id = b.equip_id					");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
        query.append("     AND b.is_last_version='Y')					");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.PM_ID									");
        query.append("      ,x.PM_NO									");
        query.append("      ,x.DESCRIPTION								");
        query.append("      ,1 EQUIP_ID									");
        query.append("      ,x.DEPT_ID									");
        query.append("      ,x.PM_TYPE									");
        query.append("      ,x.SCHEDULE_TYPE							");
        query.append("      ,x.CYCLE									");
        query.append("      ,x.PERIOD_TYPE								");
        query.append("      ,x.USAGE									");
        query.append("      ,x.WO_RES_BEF								");
        query.append("      ,'' INIT_WRK_DATE							");
        query.append("      ,'' LAST_SCH_DATE							");
        query.append("      ,x.IS_ACTIVE								");
        query.append("      ,x.REMARK									");
        query.append("      ,'' NEXT_SCH_DATE							");
        query.append("      ,x.PM_CATEG									");
        query.append("      ,x.EMP_ID									");
        query.append("      ,x.WO_TYPE									");
        query.append("      ,x.EQLOC_ID									");
        query.append("      ,x.WKCTR_ID									");
    	query.append("FROM TAPMLST x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.getStringEqualQuery("x.is_deleted", "N");
        query.append("AND x.pm_id IN ( SELECT a.pm_id					");
        query.append("FROM TAPMINSLIST a, TAPMLST b						");
    	query.append("WHERE a.comp_no = b.comp_no						");
    	query.append("AND a.pm_id = b.pm_id								");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND b.plant='"+plant+"'			");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "RINS");
    	query.append("  and a.WKOR_DATE >= '"+startDate+"'																												");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
        }
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	
    	if (!eqctgType.equals("null") && !"".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!eqlocId.equals("null") && !"".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
        query.append("			)								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findPmequipList(Map map)
    { 

    	QueryBuffer query = new QueryBuffer(); 

    	String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
    	
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.PMEQUIP_ID								");
        query.append("      ,x.PM_ID									");
        query.append("      ,x.EQUIP_ID									");
        query.append("      ,x.EQCTG_ID									");
        query.append("      ,x.DESCRIPTION								");
        query.append("      ,(SELECT a.wo_type							");
        query.append("		  FROM TAPMLST a							");
        query.append("		  WHERE 1=1									");
        query.append("		  AND a.comp_no = x.comp_no					");
        query.append("		  AND a.pm_id = x.pm_id	) AS WO_TYPE		");
        query.append("      ,x.LAST_CPLT_DATE							");
        query.append("      ,x.LAST_CPLT_BY								");
        query.append("      ,x.PM_RESULT_STATUS							");
        query.append("      ,x.NEXT_PLAN_DATE							");
        query.append("     FROM  TAPMEQUIP x, TAEQUIPMENT y				");
        query.append("     WHERE x.comp_no  = y.comp_no					");
        query.append("       AND x.equip_id = y.equip_id				");
        query.append("       AND y.is_last_version='Y'					");
        query.append("       AND y.is_deleted='N'						");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.append("		 AND y.plant = '"+plant+"'					");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findPmpointList(Map map)
    {
    	QueryBuffer query = new QueryBuffer(); 

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
        query.append("           a.pm_id,								");
        query.append("           b.item_no,								");
        query.append("           a.comp_no,								");
        query.append("           b.sub_mng_id,							");
        query.append("           b.eqctg_id,							");
        query.append("           b.eqloc_id,							");
        query.append("           b.old_eq_no,							");
        query.append("           b.description ,						");
        query.append("           b.eqctg_type							");
        query.append("FROM  TAPMEQUIP a, TAEQUIPMENT b					");
        query.append("     WHERE a.comp_no = b.comp_no					");
        query.append("     AND a.equip_id = b.equip_id					");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getDeptLevelQuery("b.usage_dept", usageDeptId, "", compNo);
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
        query.append("     AND b.is_last_version='Y')					");
    	query.append("SELECT											");
        query.append("      x.COMP_NO									");
        query.append("      ,x.PM_POINT_ID								");
        query.append("      ,x.PM_ID									");
        query.append("      ,x.STEP_NUM									");
        query.append("      ,x.EQASMB_ID								");
        query.append("      ,x.CHECK_POINT								");
        query.append("      ,x.CHECK_METHOD								");
        query.append("      ,x.FIT_BASIS								");
        query.append("      ,x.CHECK_TYPE								");
        query.append("      ,x.CHECK_MIN								");
        query.append("      ,x.CHECK_BASIS_VAL							");
        query.append("      ,x.CHECK_MAX								");
        query.append("      ,x.UOM										");
        query.append("      ,x.IS_ACTIVE								");
        query.append("      ,x.REMARK									");
        query.append("      ,x.STWRK_POINT_ID							");
    	query.append("FROM TAPMPOINT x									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
    	query.getStringEqualQuery("x.is_deleted", "N");
        query.append("AND x.pm_id IN ( SELECT a.pm_id					");
        query.append("FROM TAPMINSLIST a, TAPMLST b	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND b.plant='"+plant+"'			");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(CommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.is_deleted", "N");
    	query.getStringEqualQuery("b.is_deleted", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "RINS");
    	query.append("  and a.WKOR_DATE >= '"+startDate+"'																										");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
        	query.getAndQuery("a.emp_id", empId);
        }
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!eqctgType.equals("null") && !"".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!eqlocId.equals("null") && !"".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
        query.append("			)										");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public int saveInspoint(Map<String,String> map, String pminspointId)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAPMINSPOINT a																						");
    	query.append("USING(	SELECT 	? COMP_NO,																					");
    	query.append("					? PMINSLIST_ID,																				");
    	query.append("					? PM_POINT_ID,																				");
    	query.append("					? PM_POINT_RSLT_STATUS,																		");
    	query.append("					? RESULT_VALUE,																				");
    	query.append("					? pm_id,																					");
    	query.append("					? pmequip_id,																				");
    	query.append("					? actual_date,																				");
    	query.append("					? REMARK																					");
    	query.append("			FROM DUAL) b																						");
    	query.append("ON(	a.COMP_NO = b.COMP_NO																					");
    	query.append("	AND a.PMINSLIST_ID = b.PMINSLIST_ID																			");
    	query.append("	AND a.PM_POINT_ID = b.PM_POINT_ID	)																		");
    	query.append("WHEN MATCHED THEN																								");
    	query.append("	UPDATE SET 	a.pm_point_rslt_status = b.PM_POINT_RSLT_STATUS,												");
    	query.append("				a.result_value = b.RESULT_VALUE,																");
    	query.append("				a.remark = b.REMARK	,																			");
    	query.append("				a.is_saved = 'Y',																				");
    	query.append("				a.actual_date = b.actual_date																	");
    	query.append("WHEN NOT MATCHED THEN																							");
    	query.append("	INSERT (a.comp_no,			a.pminspoint_id,		a.pminslist_id,	a.pm_id,	a.pmequip_id,				");
    	query.append("			a.actual_date,		a.pm_point_id,			a.pm_point_rslt_status,	a.result_value,	a.remark		");
    	query.append("			,a.is_saved																							");
    	query.append("			)																									");
    	query.append("	VALUES (b.comp_no,			?,						b.PMINSLIST_ID,	b.pm_id,	b.pmequip_id,				");
    	query.append("			b.actual_date,		b.pm_point_id,			b.pm_point_rslt_status,	b.result_value,	b.remark		");
    	query.append("			,?																									");
    	query.append("			)																									");
    	
        Object[] objects = new Object[] {
                convertString(map.get("COMP_NO"))
                ,convertString(map.get("PMINSLIST_ID"))
                ,convertString(map.get("PM_POINT_ID"))
                ,convertString(map.get("PM_POINT_RSLT_STATUS"))
                ,convertString(map.get("RESULT_VALUE"))
                ,convertString(map.get("PM_ID"))
                ,convertString(map.get("PMEQUIP_ID"))
                ,convertString(map.get("ACTUAL_DATE"))
                ,convertString(map.get("REMARK"))
                ,pminspointId
                ,"Y"
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public String getInspoint(Map<String,String> map)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT pminspoint_id		");
    	query.append("FROM TAPMINSPOINT 		");
    	query.append("WHERE 1=1					");
    	query.append("AND comp_no = ? 			");
    	query.append("AND pminslist_id = ? 		");
    	query.append("AND pm_point_id = ? 		");
    	query.append("AND rownum=1		 		");

    	
    	Object[] objects = new Object[] {
                convertString(map.get("COMP_NO"))
                ,convertString(map.get("PMINSLIST_ID"))
                ,convertString(map.get("PM_POINT_ID"))
        };
    	
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    }
    public int mergeAbnormalRslt(Map map, String pminspointId) {
		
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
                convertString(map.get("COMP_NO"))
                ,convertString(map.get("PMINSLIST_ID"))
                ,convertString(map.get("PM_POINT_ID"))
                ,convertString(map.get("PM_POINT_RSLT_STATUS"))
                ,convertString(map.get("RESULT_VALUE"))
                ,convertString(map.get("REMARK"))
                ,"RINS"
                ,pminspointId
        };
        
    	
    	return getJdbcTemplate().update(query.toString(), objects);

	}

	public void deleteAbnormalRslt(Map map) {
		// TODO Auto-generated method stub
		
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
    			convertString(map.get("actualDate")),
                convertString(map.get("actualTime")),
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
        query.append("  emp_id            = ?,                    ");
        query.append("  close_id          = ?,                    ");
        query.append("  close_date        = ?                     ");
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
                convertString(map.get("empId")),
                convertString(map.get("empId")),
                convertString(map.get("closeDate")),
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

	@Override
	public String getWoStatusOfInspection(Map map) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT pmsched_status		");
		query.append("FROM TAPMINSLIST			");
		query.append("WHERE comp_no = ?			");
		query.append("AND pminslist_id = ?		");
		
		Object[] objects = new Object[] {
				convertString(map.get("compNo")),
				convertString(map.get("pminslistId"))
		};
		
		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}

}