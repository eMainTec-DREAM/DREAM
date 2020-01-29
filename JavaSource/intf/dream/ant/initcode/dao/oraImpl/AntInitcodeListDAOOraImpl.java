package intf.dream.ant.initcode.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import intf.dream.ant.initcode.dao.AntInitcodeListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="antInitcodeListDAOTarget"
 * @spring.txbn id="antInitcodeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AntInitcodeListDAOOraImpl extends BaseJdbcDaoSupportOra implements AntInitcodeListDAO
{
	public List findLangList(Map map)
    {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            		");
        query.append("      lang_id     		");
        query.append("      ,lang       		");
        query.append("      ,key_type   		");
        query.append("      ,key_no     		");
        query.append("      ,key_name   		");
        query.append("      ,upd_date   		");
        query.append("      ,is_comm_js_use   	");
        query.append("      ,remark   			");
        query.append("FROM   TALANG a   		");
        query.append("WHERE  1 = 1      		");
        query.append("  AND  use_android='Y'	");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    } 
    
    public List findSyscodeList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            	");
        query.append("      cdsysd_id		");
        query.append("      ,cdsysm_id		");
        query.append("      ,cdsysd_no cdsysdno		");
        query.append("      ,description	");
        query.append("      ,ord_no			");
        query.append("      ,is_use			");
        query.append("      ,list_type		");
        query.append("      ,remark			");
        query.append("      ,param1			");
        query.append("      ,param2			");
        query.append("      ,key_type		");
        query.append("      ,key_no			");
        query.append("FROM   TACDSYSD a		");
        query.append("WHERE  1 = 1			");
        query.append("  AND  is_use='Y'		");
        query.getStringEqualQuery("list_type", String.valueOf(map.get("listType")));
        query.append("ORDER BY a.ord_no		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findUsrcodeList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            								");
        query.append("      comp_no										");
        query.append("      ,cdusrd_id									");
        query.append("      ,cdusrm_id									");
        query.append("      ,cdusrd_no									");
        query.append("      ,p_cdusrd_id								");
        query.append("      ,description								");
        query.append("      ,remark										");
        query.append("      ,ord_no										");
        query.append("      ,is_use										");
        query.append("      ,(SELECT b.dir_type							");
        query.append("        FROM TACDUSRM b							");
        query.append("        WHERE b.comp_no = a.comp_no				");
        query.append("        AND b.cdusrm_id = a.cdusrm_id) dir_type	");
        query.append("FROM   TACDUSRD a									");
        query.append("WHERE  1 = 1										");
        query.append("  AND  is_use='Y'									");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findFailureList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT				");
        query.append("      COMP_NO			");
        query.append("      ,FAILURE_ID		");
        query.append("      ,FAILURE_NO		");
        query.append("      ,DESCRIPTION	");
        query.append("      ,FAIL_TYPE		");
        query.append("      ,IS_USE			");
        query.append("      ,ORD_NO			");
        query.append("      ,REMARK			");
        query.append("      ,KEY_TYPE		");
        query.append("      ,KEY_NO			");
        query.append("FROM   TAFAILURE		");
        query.append("WHERE  1 = 1			");
        query.append("  AND  is_use='Y'		");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findUserList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       x.comp_no			");
    	query.append("      ,x.user_id			");
        query.append("      ,x.user_no			");
        query.append("      ,x.password			");
        query.append("      ,x.user_name		");
        query.append("      ,x.usrgrp_id		");
        query.append("      ,x.init_menu_id		");
        query.append("      ,x.emp_id			");
        query.append("      ,x.m_phone			");
        query.append("      ,x.e_mail			");
        query.append("      ,x.is_use			");
        query.append("      ,x.reg_date			");
        query.append("      ,x.login_date		");
        query.append("      ,x.secur_grade		");
        query.append("      ,x.scrn_font_size	");
        query.append("      ,x.is_monitor		");
        query.append("      ,x.shift_type		");
        query.append("      ,x.eqloc_id			");
        query.append("      ,x.alarm_view_yn	");
        query.append("      ,x.eqctg_type		");
        query.append("      ,x.filter_dept_id	");
        query.append("      ,x.menu_display		");
        query.append("      ,y.wkctr_id filter_wkctr_id			");
        query.append("      ,x.filter_emp_id filter_emp_id		");
        query.append("      ,x.filter_wcode_id filter_wcode_id	");
        query.append("      ,x.filter_day_interval filter_day_interval                             ");
        query.append("      ,CASE WHEN x.filter_plant is null THEN y.plant ELSE x.filter_plant END as filter_plant	");
        query.append("FROM TAUSER x, TAEMP y	");
        query.append("WHERE x.comp_no = y.comp_no");
        query.append("  AND x.emp_id = y.emp_id	");
        query.append("  AND x.is_use='Y'		");
        query.append("  AND y.is_join ='Y'		");
        query.getStringEqualQuery("x.comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND y.plant = '"+String.valueOf(map.get("plant"))+"'		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findEmpList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,emp_id				");
        query.append("      ,emp_no				");
        query.append("      ,emp_name			");
        query.append("      ,dept_id			");
        query.append("      ,grade				");
        query.append("      ,is_direct			");
        query.append("      ,m_phone			");
        query.append("      ,e_mail				");
        query.append("      ,eq_org_id			");
        query.append("      ,join_date			");
        query.append("      ,retire_date		");
        query.append("      ,is_join			");
        query.append("      ,remark				");
        query.append("      ,plant				");
        query.append("      ,wkctr_id			");
        query.append("FROM TAEMP				");
        query.append("WHERE 1=1					");
        query.append("  AND is_join ='Y'		");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findDeptList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,dept_id			");
        query.append("      ,dept_no			");
        query.append("      ,description		");
        query.append("      ,p_dept_id			");
        query.append("      ,dept_categ			");
        query.append("      ,is_use				");
        query.append("      ,ord_no				");
        query.append("      ,wcode_id			");
        query.append("      ,is_lowest_lvl		");
        query.append("      ,plant				");
        query.append("      ,twcode_id			");
      query.append("       ,LEVEL AS LVL        ");
        query.append("FROM TADEPT				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.append("START WITH p_dept_id = '0'						");
        query.append("CONNECT BY PRIOR dept_id = p_dept_id				");
        query.append("ORDER SIBLINGS BY ord_no							");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findWkctrList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,wkctr_id			");
        query.append("      ,wkctr_no			");
        query.append("      ,description		");
        query.append("      ,full_desc			");
        query.append("      ,p_wkctr_id			");
        query.append("      ,is_use				");
        query.append("      ,ord_no				");
        query.append("      ,remark				");
        query.append("      ,LEVEL AS LVL       ");
        query.append("FROM TAWKCTR				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("START WITH p_wkctr_id = '0'						");
        query.append("CONNECT BY PRIOR wkctr_id = p_wkctr_id				");
        query.append("ORDER SIBLINGS BY ord_no							");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findPlantList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,plant				");
        query.append("      ,description		");
        query.append("FROM TAPLANT				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findEquipmentList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,equip_id			");
        query.append("      ,item_no			");
        query.append("      ,description		");
        query.append("      ,eqloc_id			");
        query.append("      ,eqctg_id			");
        query.append("      ,eq_status			");
        query.append("      ,dept_id			");
        query.append("      ,main_mng_id		");
        query.append("      ,sub_mng_id			");
        query.append("      ,setup_date			");
        query.append("      ,buy_amt			");
        query.append("      ,plf_type			");
        query.append("      ,maker				");
        query.append("      ,model_no			");
        query.append("      ,capacity			");
        query.append("      ,util_capa			");
        query.append("      ,is_law_eq			");
        query.append("      ,guaranty_date		");
        query.append("      ,ord_no				");
        query.append("      ,remark				");
        query.append("      ,excel_no			");
        query.append("      ,as_vendor			");
        query.append("      ,as_name			");
        query.append("      ,as_phone			");
        query.append("      ,old_eq_no			");
        query.append("      ,serial_no			");
        query.append("      ,eq_grade			");
        query.append("      ,plant				");
        query.append("      ,eqctg_type			");
        query.append("      ,'' eqstrloc_no		");
        query.append("      ,p_equip_id			");
        query.append("FROM TAEQUIPMENT			");
        query.append("WHERE 1=1					");
        query.append("AND is_last_version='Y'	");
    	query.getStringEqualQuery("is_deleted", "N");
    	query.getDeptLevelQuery("usage_dept", usageDeptId, "", String.valueOf(map.get("compNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.getStringEqualQuery("eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("eqloc_id", eqLocId, "", String.valueOf(map.get("compNo")));
        query.getDeptLevelQuery("dept_id", deptId, "", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findEqtoolList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,equip_id			");
        query.append("      ,measure_unit		");
        query.append("      ,guage_type			");
        query.append("      ,is_standard_eq		");
        query.append("      ,base_equip_id		");
        query.append("      ,min_unit_value		");
        query.append("      ,min_value			");
        query.append("      ,max_value			");
        query.append("      ,measure_tool		");
        query.append("      ,measure_categ		");
        query.append("      ,all_range			");
        query.append("      ,use_range			");
        query.append("      ,accuracy			");
        query.append("      ,tolerance			");
        query.append("FROM TAEQTOOL				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("AND equip_id IN ( SELECT equip_id 	");
        query.append("					FROM TAEQUIPMENT 	");
        query.append("					WHERE 1=1		 	");
        query.append("					AND is_last_version='Y'	");
    	query.getStringEqualQuery("is_deleted", "N");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.getDeptLevelQuery("usage_dept", usageDeptId, "", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.getStringEqualQuery("eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("eqloc_id", eqLocId, "", String.valueOf(map.get("compNo")));
        query.getDeptLevelQuery("dept_id", deptId, "", String.valueOf(map.get("compNo")));
        query.append("					)				 	");
        
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findEqspecList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       COMP_NO			");
    	query.append("      ,EQSPEC_ID			");
        query.append("      ,EQUIP_ID			");
        query.append("      ,CATEG				");
        query.append("      ,PROMPT				");
        query.append("      ,RESPONSE			");
        query.append("      ,UOM				");
        query.append("      ,ORD_NO				");
        query.append("FROM TAEQSPEC				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("AND equip_id IN ( SELECT equip_id 	");
        query.append("					FROM TAEQUIPMENT 	");
        query.append("					WHERE 1=1		 	");
        query.append("					AND is_last_version='Y'	");
    	query.getStringEqualQuery("is_deleted", "N");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.getDeptLevelQuery("usage_dept", usageDeptId, "", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.getStringEqualQuery("eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("eqloc_id", eqLocId, "", String.valueOf(map.get("compNo")));
        query.getDeptLevelQuery("dept_id", deptId, "", String.valueOf(map.get("compNo")));
        query.append("					)				 	");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findEqpartList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       COMP_NO			");
    	query.append("      ,EQPART_ID			");
        query.append("      ,EQASMB_ID			");
        query.append("      ,EQUIP_ID			");
        query.append("      ,PART_ID			");
        query.append("      ,CONSIST_QTY		");
        query.append("      ,USE_QTY			");
        query.append("      ,ISSUE_TIME			");
        query.append("      ,ISSUE_FIRST_DATE	");
        query.append("      ,ISSUE_LAST_DATE	");
        query.append("FROM TAEQPART				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("AND equip_id IN ( SELECT equip_id 	");
        query.append("					FROM TAEQUIPMENT 	");
        query.append("					WHERE 1=1		 	");
        query.append("					AND is_last_version='Y'	");
    	query.getStringEqualQuery("is_deleted", "N");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.getDeptLevelQuery("usage_dept", usageDeptId, "", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.getStringEqualQuery("eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("eqloc_id", eqLocId, "", String.valueOf(map.get("compNo")));
        query.getDeptLevelQuery("dept_id", deptId, "", String.valueOf(map.get("compNo")));
        query.append("					)				 	");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findEqasmbList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       COMP_NO			");
    	query.append("      ,EQASMB_ID			");
    	query.append("      ,EQUIP_ID			");
    	query.append("      ,EQ_CTG_ASMB_ID		");
    	query.append("      ,EQCTG_ID			");
    	query.append("      ,DESCRIPTION		");
    	query.append("      ,IS_EQTYPE_ASMB		");
    	query.append("      ,ORD_NO				");
    	query.append("      ,IS_USE				");
    	query.append("      ,EQ_CTG_ASMB_NO		");
    	query.append("      ,P_EQ_CTG_ASMB_ID	");
    	query.append("      ,EQASMB_NO			");
    	query.append("      ,P_EQASMB_ID		");
    	query.append("      ,REMARK				");
    	query.append("      ,FULL_DESC			");
        query.append("      ,LEVEL AS LVL       ");
    	query.append("FROM TAEQASMB				");
    	query.append("WHERE 1=1					");
    	query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.getStringEqualQuery("is_use", "Y");
    	query.append("AND equip_id IN ( SELECT equip_id 	");
    	query.append("					FROM TAEQUIPMENT 	");
    	query.append("					WHERE 1=1		 	");
    	query.append("					AND is_last_version='Y'	");
    	query.getStringEqualQuery("is_deleted", "N");
    	query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.getDeptLevelQuery("usage_dept", usageDeptId, "", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.getStringEqualQuery("eqctg_type", eqCtgType);
        query.getEqLocLevelQuery("eqloc_id", eqLocId, "", String.valueOf(map.get("compNo")));
        query.getDeptLevelQuery("dept_id", deptId, "", String.valueOf(map.get("compNo")));
    	query.append("					)				 	");

        query.append("START WITH p_eqasmb_id = '0'						");
        query.append("CONNECT BY PRIOR eqasmb_id = p_eqasmb_id			");
        query.append("ORDER SIBLINGS BY ord_no							");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findPmequipList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
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
        query.append("		,CASE WHEN x.LAST_CPLT_DATE IS NULL THEN x.INIT_WRK_DATE	");
        query.append("				ELSE x.LAST_CPLT_DATE END AS LAST_CPLT_DATE			");
        query.append("      ,x.LAST_CPLT_BY								");
        query.append("      ,x.PM_RESULT_STATUS							");
        query.append("		,CASE WHEN x.NEXT_PLAN_DATE IS NULL THEN 	");
        query.append("			(										");
        query.append("			to_char(case (select a.period_type from tapmlst a Where a.comp_no = x.comp_no and a.pm_id = x.pm_id) 	");
        query.append("				WHEN 'D' THEN						");
        query.append("					(select to_date(x.init_wrk_date)+a.cycle + (CASE WHEN a.wo_type ='PMC' THEN -1 ELSE 0 END)  from tapmlst a Where a.comp_no = x.comp_no and a.pm_id = x.pm_id)		");
        query.append("				WHEN 'W' THEN						");
        query.append("					(select to_date(x.init_wrk_date)+(a.cycle*7) + (CASE WHEN a.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst a Where a.comp_no = x.comp_no and a.pm_id = x.pm_id)	");
        query.append("				WHEN 'M' THEN						");
        query.append("					(select add_months(to_date(x.init_wrk_date),a.cycle) + (CASE WHEN a.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst a Where a.comp_no = x.comp_no and a.pm_id = x.pm_id)	");
        query.append("				WHEN 'Y' THEN						");
        query.append("					(select add_months(to_date(x.init_wrk_date),(a.cycle*12)) + (CASE WHEN a.wo_type ='PMC' THEN -1 ELSE 0 END) from tapmlst a Where a.comp_no = x.comp_no and a.pm_id = x.pm_id)	");
        query.append("				END, 'YYYYMMDD')					");
        query.append("			)										");
        query.append("		ELSE x.NEXT_PLAN_DATE END AS NEXT_PLAN_DATE	");
        query.append("     FROM  TAPMEQUIP x INNER JOIN TAEQUIPMENT y	");
        query.append("     ON x.comp_no  = y.comp_no					");
        query.append("       AND x.equip_id = y.equip_id				");
        query.append("       WHERE y.is_last_version='Y'				");
    	query.getStringEqualQuery("y.is_deleted", "N");
    	query.getStringEqualQuery("x.is_deleted", "N");
    	query.getStringEqualQuery("x.comp_no", String.valueOf(map.get("compNo")));
        query.append("		 AND y.plant = '"+String.valueOf(map.get("plant"))+"'					");
        
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findEqlocList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,eqloc_id			");
        query.append("      ,eqloc_no			");
        query.append("      ,description		");
        query.append("      ,full_desc			");
        query.append("      ,p_eqloc_id			");
        query.append("      ,eqloc_lvl_type		");
        query.append("      ,lvl				");
        query.append("      ,remark				");
        query.append("      ,ord_no				");
        query.append("      ,is_use				");
        query.append("      ,ctctr_id			");
        query.append("      ,plant				");
        query.append("      ,mes_line_id		");
        query.append("      ,is_operation		");
        query.append("      ,is_kpi				");
        query.append("      ,dtime				");
        query.append("      ,ntime				");
        query.append("      ,etime				");
        query.append("      ,is_lowest_lvl		");
        query.append("FROM TAEQLOC				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findEqctgList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,eqctg_id			");
        query.append("      ,eqctg_no			");
        query.append("      ,description		");
        query.append("      ,full_desc			");
        query.append("      ,p_eqctg_id			");
        query.append("      ,ord_no				");
        query.append("      ,lvl				");
        query.append("      ,is_use				");
        query.append("      ,remark				");
        query.append("      ,is_lowest_lvl		");
        query.append("      ,eqctg_type			");
        query.append("FROM TAEQCTG				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findPartsList(Map map, String wcodeId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,part_id			");
        query.append("      ,part_no			");
        query.append("      ,description		");
        query.append("      ,pt_size			");
        query.append("      ,uom				");
        query.append("      ,full_desc			");
        query.append("      ,model				");
        query.append("      ,maker				");
        query.append("      ,usage				");
        query.append("      ,last_price			");
        query.append("      ,plf_type			");
        query.append("      ,pco_type			");
        query.append("      ,seller				");
        query.append("      ,lead_time			");
        query.append("      ,is_use				");
        query.append("      ,remark				");
        query.append("      ,upd_date			");
        query.append("      ,upd_by				");
        query.append("      ,mro_type			");
        query.append("      ,kind				");
        query.append("      ,var_class			");
        query.append("      ,part_group			");
        query.append("      ,is_inpart			");
        query.append("      ,vendor_desc		");
        query.append("      ,vendor_code		");
        query.append("      ,part_categ			");
        query.append("      ,is_serial_part		");
        query.append("      ,last_gr_date		");
        query.append("      ,last_iss_date		");
        query.append("      ,out_upd_date		");
        query.append("      ,pt_abc_class		");
        query.append("FROM TAPARTS				");
        query.append("WHERE 1=1					");
        query.append("  AND is_use='Y'			");
        query.append("  AND is_stock_control='Y'		");
        query.getStringEqualQuery("is_deleted", "N");
        query.append("  AND part_categ='SPPT'			");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND part_id IN ( SELECT part_id						");
        query.append("  					FROM TAPTSTOCK a INNER JOIN TAWAREHOUSE b		");
        query.append("  					ON a.comp_no = b.comp_no		");
        query.append("  					AND a.wcode_id = b.wcode_id		");
        query.append("  					WHERE 1=1		");
        query.getStringEqualQuery("a.comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND b.plant = '"+String.valueOf(map.get("plant"))+"'		");
        query.append("  AND b.wh_categ='PART'			");
        query.getAndQuery("a.wcode_id", wcodeId);
        query.append("  			)			");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findWarehouseList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,wcode_id			");
        query.append("      ,wcode				");
        query.append("      ,wname				");
        query.append("      ,wh_type			");
        query.append("      ,is_use				");
        query.append("      ,remark				");
        query.append("      ,plant				");
        query.append("      ,out_wcode			");
        query.append("      ,out_plant			");
        query.append("      ,gsber				");
        query.append("      ,wh_categ			");
        query.append("      ,bwat				");
        query.append("FROM TAWAREHOUSE			");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("  AND plant = '"+String.valueOf(map.get("plant"))+"'		");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findMenuList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 

    	query.append("SELECT  menu_id, description, p_menu_id, page_id, ord_no				");
    	query.append("		, param, is_system, key_no, key_type, is_use					");
    	query.append("		, menu_no, p_menu_no, service_type, remark, LEVEL menu_lvl		");
    	query.append("FROM TAMENU															");
    	query.append("WHERE is_use='Y'														");
    	query.append("AND is_system='N'														");
    	query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
    	query.append("START WITH p_menu_id =0												");
    	query.append("CONNECT BY PRIOR menu_id = p_menu_id									");
    	query.append("ORDER SIBLINGS BY ord_no												");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findProductList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 

    	query.append("SELECT  comp_no, product_id, product_no, description, is_use,remark	");
    	query.append("FROM TAPRODUCT														");
    	query.append("WHERE is_use='Y'														");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public List findUsrGrpMenuList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,usrgrpmenu_id		");
        query.append("      ,usrgrp_id			");
        query.append("      ,menu_id			");
        query.append("FROM TAUSRGRPMENU			");
        query.append("WHERE 1=1					");
        query.append("AND menu_id IN (SELECT menu_id FROM TAMENU 	");
        query.append("					WHERE 1=1					");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
        query.append(")							");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findConfigList(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,config_id			");
    	query.append("      ,name				");
    	query.append("      ,value$				");
    	query.append("      ,description		");
    	query.append("      ,is_system			");
    	query.append("FROM TACONFIG				");
    	query.append("WHERE 1=1					");
    	query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findStockList(Map map, String wcodeId)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       comp_no			");
    	query.append("      ,wcode_id			");
        query.append("      ,part_id			");
        query.append("      ,part_grade			");
        query.append("      ,stock_qty			");
        query.append("      ,bin_no				");
        query.append("      ,unit_price			");
        query.append("FROM TAPTSTOCK			");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
        query.append("AND wcode_id = '"+wcodeId+"' ");
        query.append("AND stock_qty>=0.001			");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
    public List findDownUserCheck(Map map)
    { 
    	String id =  "";
    	String pw =  "";
    	
    	id = String.valueOf(map.get("ID"));
		pw = String.valueOf(map.get("PW"));
    	try {
    		pw = CommonUtil.aesEncodeString(pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            		");
        query.append("      x.user_no userNo	");
        query.append("      ,y.plant as plant	");
        query.append("      ,CASE WHEN x.filter_plant is null THEN y.plant ELSE x.filter_plant END as filterplant	");
        query.append("FROM   TAUSER x INNER JOIN TAEMP y	");
        query.append("ON x.comp_no = y.comp_no				");
        query.append("AND x.emp_id = y.emp_id				");
        query.append("WHERE  UPPER(x.user_no) = UPPER('"+id+"')	");
        query.append("AND x.password = '"+pw+"' 	");
        query.append("AND x.comp_no = '"+String.valueOf(map.get("compNo"))+"' ");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

    public String findUserEqCtgType(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       eqctg_type			");
        query.append("FROM TAUSER				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String findUserEqLoc(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       eqloc_id			");
        query.append("FROM TAUSER				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String findUserDept(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       filter_dept_id		");
        query.append("FROM TAUSER				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String findUserUsageDept(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       filter_usage_dept_id		");
        query.append("FROM TAUSER				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    public String findUserWcode(Map map)
    { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT 					");
    	query.append("       filter_wcode_id	");
        query.append("FROM TAUSER				");
        query.append("WHERE 1=1					");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

	@Override
	public List findPageList(Map map) {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            			");
        query.append("      page_id     			");
        query.append("      ,file_name       		");
        query.append("      ,description   			");
        query.append("      ,remark     			");
        query.append("      ,is_use   				");
        query.append("      ,is_chkauth   			");
        query.append("      ,page_type   			");
        query.append("      ,page_categ   			");
        query.append("      ,service_type   		");
        query.append("      ,key_type   			");
        query.append("      ,key_no   				");
        query.append("FROM   TAPAGE a   			");
        query.append("WHERE  1 = 1      			");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
        
    	return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findPgBtnList(Map map) {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            					");
        query.append("      pgbtn_id     					");
        query.append("      ,page_id       					");
        query.append("      ,button_id   					");
        query.append("      ,button_loc     				");
        query.append("      ,remark   						");
        query.append("      ,ord_no   						");
        query.append("      ,is_use   						");
        query.append("      ,is_chkauth   					");
        query.append("      ,is_basic   					");
        query.append("      ,is_set_group   				");
        query.append("      ,key_type   					");
        query.append("      ,key_no   						");
        query.append("      ,file_name   					");
        query.append("      ,button_no   					");
        query.append("FROM   TAPGBTN a   					");
        query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findPgFieldList(Map map) {
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT            					");
        query.append("      pgfield_id     					");
        query.append("      ,page_id       					");
        query.append("      ,field_id   					");
        query.append("      ,description     				");
        query.append("      ,hidden_yn   					");
        query.append("      ,ord_no   						");
        query.append("      ,display_yn   					");
        query.append("      ,key_type   					");
        query.append("      ,key_no   						");
        query.append("      ,check_yn   					");
        query.append("      ,readonly_yn   					");
        query.append("      ,file_name   					");
        query.append("FROM   TAPGFIELD a   					");
        query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
	@Override
	public List findPgGridList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            					");
		query.append("      pggrid_id     					");
		query.append("      ,page_id       					");
		query.append("      ,grid_obj_id   					");
		query.append("      ,description     				");
		query.append("      ,height   						");
		query.append("      ,file_name   					");
		query.append("FROM   TAPGGRID a   					");
		query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findPgGridColList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            										");
		query.append("      pggridcol_id     									");
		query.append("      ,pggrid_id       									");
		query.append("      ,column_id   										");
		query.append("      ,ord_no     										");
		query.append("      ,key_no   											");
		query.append("      ,width   											");
		query.append("      ,type   											");
		query.append("      ,align   											");
		query.append("      ,sort   											");
		query.append("      ,hidden   											");
		query.append("      ,key_type   										");
		query.append("      ,system_col   										");
		query.append("      ,description   										");
		query.append("      ,file_name   										");
		query.append("      ,grid_obj_id   										");
		query.append("FROM   TAPGGRIDCOL a   									");
		query.append("WHERE  1 = 1      										");
		query.append("AND  pggrid_id IN ( SELECT pggrid_id     					");
		query.append("						FROM TAPGGRID     					");
		query.append("						WHERE 1=1     						");
		query.append("						AND page_id IN (					");
		query.append("							SELECT page_id					");
		query.append("							FROM TAPAGE						");
		query.append("							WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("							)								");
		query.append("					)										");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findPgPageList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            					");
		query.append("      pgpage_id     					");
		query.append("      ,page_id       					");
		query.append("      ,c_page_id   					");
		query.append("      ,ord_no     					");
		query.append("      ,key_type   					");
		query.append("      ,key_no   						");
		query.append("      ,remark   						");
		query.append("      ,file_name   					");
		query.append("      ,c_file_name   					");
		query.append("      ,pgpage_name   					");
		query.append("      ,is_use     					");
		query.append("FROM   TAPGPAGE a   					");
		query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findPgLinkedFuncList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            					");
		query.append("      pglinkedfunc_id     			");
		query.append("      ,page_id       					");
		query.append("      ,linkedfunc_id   				");
		query.append("      ,file_name     					");
		query.append("      ,linkedfunc_method   			");
		query.append("      ,key_type   					");
		query.append("      ,key_no   						");
		query.append("      ,ord_no   						");
		query.append("      ,is_use   						");
		query.append("      ,remark   						");
		query.append("      ,linkedfunc_loc   				");
		query.append("		,(SELECT linkedfunc_no FROM TALINKEDFUNC b WHERE b.linkedfunc_id = a.linkedfunc_id) as linkedfunc_no ");
		query.append("FROM   TAPGLINKEDFUNC a   			");
		query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findUgPgAuList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            					");
		query.append("      ugpgau_id     					");
		query.append("      ,usrgrp_id       				");
		query.append("      ,page_id   						");
		query.append("      ,comp_no     					");
		query.append("FROM   TAUGPGAU a   					");
		query.append("WHERE  1 = 1      					");
		query.append("AND page_id IN (						");
		query.append("		SELECT page_id					");
		query.append("		FROM TAPAGE						");
		query.append("		WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("		)								");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findUgPgBtnAuList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            					");
		query.append("       ugpgbtnau_id     				");
		query.append("      ,usrgrp_id       				");
		query.append("      ,pgbtn_id   					");
		query.append("      ,comp_no     					");
		query.append("FROM   TAUGPGBTNAU a   				");
		query.append("WHERE  1 = 1      					");
		query.append("AND  pgbtn_id IN ( SELECT pgbtn_id     					");
		query.append("						FROM TAPGBTN     					");
		query.append("						WHERE 1=1     						");
		query.append("						AND page_id IN (					");
		query.append("							SELECT page_id					");
		query.append("							FROM TAPAGE						");
		query.append("							WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("							)								");
		query.append("					)										");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public List findUgPgPgAuList(Map map) {
		QueryBuffer query = new QueryBuffer(); 
		
		query.append("SELECT            										");
		query.append("       ugpgpgau_id     									");
		query.append("      ,usrgrp_id       									");
		query.append("      ,pgpage_id   										");
		query.append("      ,comp_no     										");
		query.append("FROM   TAUGPGPGAU a   									");
		query.append("WHERE  1 = 1      										");
		query.append("AND  pgpage_id IN ( SELECT pgpage_id     					");
		query.append("						FROM TAPGPAGE     					");
		query.append("						WHERE 1=1     						");
		query.append("						AND page_id IN (					");
		query.append("							SELECT page_id					");
		query.append("							FROM TAPAGE						");
		query.append("							WHERE 1=1						");
		query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("							)								");
		query.append("					)										");
		
		return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public String findIsMaintDept(Map map) { 
    	QueryBuffer query = new QueryBuffer(); 
    	
    	query.append("SELECT						");
    	query.append("		CASE WHEN is_maint IS NULL THEN 'N' ELSE is_maint END AS isMaint	");
    	query.append("FROM TADEPT					");
    	query.append("WHERE 1=1						");
    	query.append("AND dept_id = (				");
    	query.append("		SELECT dept_id 			");
    	query.append("		FROM TAEMP				");
    	query.append("		WHERE 1=1				");
    	query.append("		AND emp_id = (			");
    	query.append("			SELECT emp_id		");
    	query.append("			FROM TAUSER			");
    	query.append("			WHERE 1=1			");
        query.getStringEqualQuery("user_no", String.valueOf(map.get("userNo")));
        query.getStringEqualQuery("comp_no", String.valueOf(map.get("compNo")));
    	query.append("			)					");
    	query.append("		)						");

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }

}