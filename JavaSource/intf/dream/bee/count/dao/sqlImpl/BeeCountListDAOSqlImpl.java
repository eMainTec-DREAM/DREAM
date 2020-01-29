package intf.dream.bee.count.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import intf.dream.bee.common_value.BeeCommonValues;
import intf.dream.bee.count.dao.BeeCountListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeCountListDAOTarget"
 * @spring.txbn id="beeCountListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeCountListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeCountListDAO
{
	public List findMainList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
    	String startDate = String.valueOf(map.get("startDate"));
    	String endDate = String.valueOf(map.get("endDate"));
    	String deptId = String.valueOf(map.get("deptId"));
    	String wkctrId = String.valueOf(map.get("wkctrId"));
    	String eqctgType = String.valueOf(map.get("eqctgType"));
    	String eqlocId = String.valueOf(map.get("eqlocId"));
    	String wcodeId = String.valueOf(map.get("wcodeId"));
    	String empId = String.valueOf(map.get("empId"));
    	String plant = String.valueOf(map.get("plant"));
     	String usageDeptId = String.valueOf(map.get("usageDeptId"));
     	String loginEmpId = String.valueOf(map.get("loginEmpId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("WITH eqInfo AS (					");
        query.append("    SELECT a.equip_id,			");
        query.append("           a.pm_id,				");
        query.append("           0 wkor_id,				");
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
        query.append("     AND b.is_last_version='Y'	");
        query.append("UNION ALL							");
        query.append("    SELECT a.equip_id,			");
        query.append("           0 pm_id,				");
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
        query.append("		AND b.is_deleted='N'		");
        query.append("     AND b.is_last_version='Y')	");

        //작업요청 건수
    	query.append("SELECT 'WOREQ' title,count(*) count								");
    	query.append("FROM TAWOREQ a									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.woreq_status", "WRT");
    	query.append("AND a.plant='"+plant+"'			");
    	query.append("  and a.req_date >= '"+startDate+"'				");
    	query.append("  and a.req_date <=  '"+endDate+"'				");
//    	query.getDeptLevelQuery("a.req_dept_id", deptId, "", compNo);
        query.getAndQuery("a.req_emp_id", empId);
        query.getDeptLevelQuery("a.req_dept_id", deptId, "", compNo);
        
        if (!"null".equals(eqctgType) && !"".equals(eqctgType))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
        if (!"null".equals(eqlocId) && !"".equals(eqlocId))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
        }
    	
    	query.append("UNION ALL											");
    	//요청접수 건수
    	query.append("SELECT 'WORES' title,count(*) count								");
    	query.append("FROM TAWOREQ a									");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.plant='"+plant+"'			");
    	query.append("  and a.req_date >= '"+startDate+"'				");
    	query.append("  and a.req_date <=  '"+endDate+"'				");
//    	query.getDeptLevelQuery("a.rec_dept_id", deptId, "", compNo);
    	
    	query.getAndQuery("a.woreq_status", "REQ+REC+WRK");
        query.getAndQuery("a.rec_emp_id", empId);
        query.getWkCtrLevelQuery("a.rec_wkctr_id", wkctrId, "", compNo);
        query.getDeptLevelQuery("a.rec_dept_id", deptId, "", compNo);
        
        if (!"null".equals(eqctgType) && !"".equals(eqctgType))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
        if (!"null".equals(eqlocId) && !"".equals(eqlocId))
        {
    		query.append("AND a.equip_id IN (						");
    		query.append("					SELECT x.equip_id		");
    		query.append("					FROM TAEQUIPMENT x		");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
        }
//        query.append("AND a.woreq_status IN (SELECT cdsysd_no FROM TACDSYSD WHERE list_type='WOREQ_STATUS' AND param1= 'C')                           ");
    	query.append("UNION ALL											");
    	//분해점검 건수
    	query.append("SELECT 'INSPECTION' title,count(*) count				");
    	query.append("FROM TAWORKORDER a, TAPMLST b		");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.append("AND b.plant='"+plant+"'			");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.IS_DELETED", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "INS");
    	
    	query.append("  and a.WKOR_DATE >= '"+startDate+"'																												");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
        }
    	
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	query.append("UNION ALL	");
    	//일상점검 건수
    	query.append("SELECT 'PMIDAY' title,count(*) count				");
    	query.append("FROM TAPMINSDLIST a, TAPMLST b	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.append("AND b.plant='"+plant+"'			");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.IS_DELETED", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "DINS");
    	
    	query.append("  and a.WKOR_DATE >=  '"+startDate+"'																										");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
    		
        }
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	query.append("UNION ALL	");
    	
    	//Part Change 건수
    	query.append("SELECT 'PMIPART' title,count(*) count				");
    	query.append("FROM TAPMINSDLIST a, TAPMLST b	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND b.plant='"+plant+"'			");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.IS_DELETED", "N");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "CINS");
    	query.append("  and a.WKOR_DATE >=  '"+startDate+"'																									");
    	query.append("  and a.WKOR_DATE <=  '"+endDate+"'																										");
    	if (!empId.equals("null") && !"".equals(empId))
        {
    		query.getAndQuery("a.emp_id", empId);
        }
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	query.append("UNION ALL	");
    	
    	//정기점검건수
    	query.append("SELECT 'PMIROUTINE' title,count(*) count				");
    	query.append("FROM TAPMINSLIST a, TAPMLST b	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.append("AND b.plant='"+plant+"'			");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.IS_DELETED", "N");
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
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.pm_id IN (							");
    		query.append("					SELECT x.pm_id			");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
        
    	query.append("UNION ALL	");
    	
    	//순회점검검수
    	query.append("SELECT 'PMIPATROL' title,count(*) count				");
    	query.append("FROM TAPMPTRLSCHED a, TAPMLST b	");
    	query.append("WHERE a.comp_no = b.comp_no		");
    	query.append("AND a.pm_id = b.pm_id				");
    	query.append("AND b.plant='"+plant+"'			");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.getStringEqualQuery("b.IS_DELETED", "N");
		query.append("AND a.pm_id IN ( SELECT pm_id FROM eqInfo )	");
    	query.append("AND a.pmsched_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_INSPECTION_STATES, "''")+")		");
    	query.getStringEqualQuery("b.wo_type", "PMI");
    	query.getStringEqualQuery("b.pm_type", "PINS");
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
    	query.append("UNION ALL	");
    	
    	//계획작업건수
    	query.append("SELECT 'PMWORK' title,count(*) count				");
    	query.append("FROM TAWORKORDER a				");
    	query.append("WHERE 1=1							");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant='"+plant+"'			");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMW");
    	query.append("  and a.wkor_date >= '"+startDate+"'							");
    	query.append("  and a.wkor_date <=  '"+endDate+"'							");
    	query.getAndQuery("a.emp_id", empId);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
        
    	query.append("UNION ALL	");
    	
    	//교정작업건수
    	query.append("SELECT 'CALIBRATION' title,count(*) count									");
    	query.append("FROM TAWORKORDER a INNER JOIN TAWOCALIB b				");
    	query.append("ON a.comp_no = b.comp_no								");
    	query.append("AND a.wkor_id = b.wkor_id								");
    	query.append("WHERE 1=1												");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant='"+plant+"'			");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
    	query.getStringEqualQuery("a.wo_type",   "PMC");
    	query.append("  and a.wkor_date >= '"+startDate+"'							");
    	query.append("  and a.wkor_date <=  '"+endDate+"'							");
    	query.getAndQuery("a.emp_id", empId);

    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	query.append("UNION ALL	");
    	
    	//돌발작업건수
    	query.append("SELECT 'UNPLAN' title,count(*) count				");
    	query.append("FROM TAWORKORDER a				");
    	query.append("WHERE 1=1							");
    	query.getStringEqualQuery("a.comp_no", compNo);
		query.append("AND a.wkor_id IN ( SELECT wkor_id FROM eqInfo )	");
    	query.getStringEqualQuery("a.IS_DELETED", "N");
    	query.append("AND a.plant='"+plant+"'			");
		query.append("AND a.wo_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WO_STATES, "''")+")	");
    	query.append("AND  a.wo_type NOT IN ('PMI','PMW','PMC','PM')				");
    	query.append("  and a.wkor_date >= '"+startDate+"'							");
    	query.append("  and a.wkor_date <=  '"+endDate+"'							");
    	query.getAndQuery("a.emp_id", empId);

    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getWkCtrLevelQuery("a.wkctr_id", wkctrId, "", compNo);
    	if (!"".equals(eqctgType) && !"null".equals(eqctgType))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
    		query.getStringEqualQuery("x.eqctg_type", eqctgType);
    		query.append("			)								");
        }
    	if (!"".equals(eqlocId) && !"null".equals(eqlocId))
        {
    		query.append("AND a.wkor_id IN (						");
    		query.append("					SELECT x.wkor_id		");
    		query.append("					FROM eqInfo x			");
    		query.append("					WHERE 1=1				");
    		query.getStringEqualQuery("x.comp_no", compNo);
        	query.getEqLocLevelQuery("x.eqloc_id", eqlocId, "", compNo);
    		query.append("			)								");
    	}
    	
    	query.append("UNION ALL							");
    	
    	//부품출고건수
    	query.append("SELECT 'ISS' title,count(*) count										");
        query.append("FROM TAPTISSLIST a INNER JOIN TAPTISSUE b					");
        query.append("ON a.comp_no = b.comp_no									");
        query.append("AND a.ptissue_id = b.ptissue_id							");
        query.append("INNER JOIN TAPARTS c										");
        query.append("ON a.comp_no = c.comp_no									");
        query.append("AND a.part_id = c.part_id									");
        query.append("WHERE 1=1													");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("c.is_deleted", "N");
    	query.append("AND a.ptiss_status IN ('W','X')							");
    	query.append("AND a.plant = '"+plant+"'									");
    	query.getAndQuery("a.wcode_id", wcodeId);
    	query.append("  and a.issue_date >= '"+startDate+"'						");
    	query.append("  and a.issue_date <=  '"+endDate+"'						");
    	query.getAndQuery("a.issue_by", empId);
    	query.getDeptLevelQuery("a.issue_dept", deptId, "", compNo);
    	
    	query.append("UNION ALL											");
    	
    	//현재고 건수
    	query.append("SELECT 'STOCK' title,count(*) count								");
    	query.append("FROM TAPTSTOCK x INNER JOIN TAPARTS y				");
        query.append("ON x.comp_no = y.comp_no							");
        query.append("AND x.part_id = y.part_id							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("x.comp_no", compNo);
        query.getStringEqualQuery("y.is_deleted", "N");
    	query.append("AND x.wcode_id='"+wcodeId+"'			");
        query.append("AND y.is_stock_control='Y'		");
        query.append("AND y.part_categ='SPPT'			");
        query.append("AND x.stock_qty>=0.001									");
        query.append("AND x.wcode_id IN ( SELECT wcode_id						");
        query.append("  					FROM TAWAREHOUSE					");
        query.append("  					WHERE 1=1		");
        query.getStringEqualQuery("comp_no", compNo);
        query.append("  					AND plant = '"+plant+"'				");
        query.append("  					AND wh_categ='PART'					");
        query.append("  			)											");
    	
    	query.append("UNION ALL											");
    	
    	//부품실사 건수
    	query.append("SELECT 'STKTAKE' title,count(*) count								");
    	query.append("FROM TAPTSTKTAKELIST a							");
        query.append("WHERE 1=1											");
    	query.getStringEqualQuery("a.comp_no", compNo);
    	query.getStringEqualQuery("a.ptstktake_status", "W");
    	query.getAndQuery("a.wcode_id", wcodeId);
    	query.append("AND a.plant='"+plant+"'			");
    	query.append("  and a.plan_date >= '"+startDate+"'				");
    	query.append("  and a.plan_date <=  '"+endDate+"'				");
    	
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	
    	query.append("UNION ALL											");
    	
    	//설비자산 건수
    	query.append("SELECT 'EQUIPMENT' title,count(*) count		");
    	query.append("FROM TAEQUIPMENT a		");
        query.append("WHERE 1=1					");
        query.append("AND a.is_last_version='Y'	");
        query.append("AND a.is_deleted='N'		");
    	query.append("AND a.plant='"+plant+"'			");
        query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("a.eqctg_type", eqctgType);
        
        query.getEqLocLevelQuery("a.eqloc_id", eqlocId, "", compNo);
        query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	query.getDeptLevelQuery("a.usage_dept", usageDeptId, "", compNo);

    	query.append("UNION ALL										");
    	
    	//작업요청 결재대기 건수
    	query.append("SELECT 'APPROVAL' title,count(*) count							");
    	query.append("FROM TAAPPRLIST x INNER JOIN TAAPPRUSR y		");
        query.append("ON x.comp_no = y.comp_no                		");
        query.append("AND x.apprlist_id = y.apprlist_id            	");
        query.append("WHERE 1=1										");
        query.append("AND x.comp_no = '" + compNo + "'      		");
        query.append("AND x.appr_status IN ('R','P')          		");
        query.append("AND y.apprusr_action = 'P'              		");
        query.getStringEqualQuery("x.appr_type", "REQWORK");
        query.getAndNumKeyQuery("y.appr_by", loginEmpId);

    	query.append("UNION ALL										");
    	
    	//문서목록 건수
    	query.append("SELECT 'DOCUMENT' title,count(*) count						");
    	query.append("FROM TADOCDATA x INNER JOIN TADOCUMENT y	");
        query.append("ON x.comp_no = y.comp_no					");
        query.append("AND x.doc_id = y.doc_id					");
        query.append("WHERE 1=1									");
        query.append("AND x.comp_no = '"+compNo+"'				");
        query.getDeptLevelQuery("y.dept_id", deptId, "", compNo);
    	
        query.append("UNION ALL											");
        
    	//현장구매신청건수
    	query.append("SELECT 'PTPURREQ' title,count(*) count					");
        query.append("            FROM taptpnlist a        						");
        query.append("			  LEFT OUTER JOIN TAPARTS c						");
    	query.append("			  ON a.comp_no = c.comp_no						");
    	query.append("		      AND a.part_id = c.part_id						");
        query.append("            WHERE 1 =1         							");
    	query.getStringEqualQuery("a.comp_no", compNo);
        query.getStringEqualQuery("c.is_deleted", "N");
    	query.append("AND a.plant = '"+plant+"'									");
    	query.append("  and a.req_date >= '"+startDate+"'						");
    	query.append("  and a.req_date <=  '"+endDate+"'						");
    	query.append("AND a.ptpnlist_status IN ("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_PTPN_STATUS, "''")+")					");
    	query.getAndQuery("a.user_id", empId);
    	query.getDeptLevelQuery("a.dept_id", deptId, "", compNo);
    	
    	
        query.append("UNION ALL											");
        
    	//일일작업승인
        query.append("SELECT 'WODAILY' title,count(*) count		");
        query.append("FROM   TAWODAYLIST a                          ");
        query.append("WHERE 1=1                  					");
        query.getStringEqualQuery("a.comp_no", compNo);
    	query.append("AND a.plant = '"+plant+"'									");
    	query.append("AND a.wo_date >= '"+startDate+"'						");
    	query.append("AND a.wo_date <=  '"+endDate+"'						");
    	query.append("AND a.wodaylist_status IN("+CommonUtil.getCommaStringFromArray(BeeCommonValues.USED_WODAILY_STATUS, "''")+")				");
    	
    	
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
}