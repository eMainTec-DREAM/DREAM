package dream.work.rpt.pmiequipplanrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pmiequipplanrate.dao.WorkRptPmiEquipPlanDetailDAO;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPmiEquipPlanDetailDAOTarget"
 * @spring.txbn id="workRptPmiEquipPlanDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiEquipPlanDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptPmiEquipPlanDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser)  throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        													");
        query.append("    '' 										AS SEQNO       		");
        query.append("    ,a.pminslist_id							AS pminslistId		");
        query.append("    ,a.description							AS description		");
        query.append("    ,b.sched_date								AS schedDate   		");
        query.append("    ,a.start_date								AS startDate 		");
        query.append("    ,a.end_date								AS endDate 			");
        query.append("    ,(SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 		AS itemNo 	");
        query.append("    ,(SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id)	AS itemDesc	");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) 			AS deptDesc	");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id) 				AS empName 	");
        query.append("    ,a.equip_id								AS equipId 			");
        query.append("    ,(SELECT aa.eqctg_type FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 	AS eqCtgType	");
        query.append("    ,a.pm_id									AS pmId				");
        query.append("    ,(SELECT aa.param2 FROM TACDSYSD aa WHERE aa. cdsysd_no=a.pm_type AND aa.list_type= a.wo_type||'_TYPE')	AS param    ");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b   					");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id   	");
        query.append("WHERE 1=1     													");
        query.append(this.getWhere(workRptPmiEquipPlanDetailDTO,loginUser));
        query.getOrderByQuery("b.sched_date ASC", workRptPmiEquipPlanDetailDTO.getOrderBy(), workRptPmiEquipPlanDetailDTO.getDirection());
      
      return getJdbcTemplate().queryForList(query.toString(workRptPmiEquipPlanDetailDTO.getIsLoadMaxCount(), workRptPmiEquipPlanDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO,User loginUser)  throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", loginUser.getCompNo());
        
        query.getStringEqualQuery("a.is_deleted", "N");
        query.append(" AND a.pmsched_status IN('PRC', 'C')      ");
        
        // 월
        String yyyymm = workRptPmiEquipPlanDetailDTO.getYyyymm();
        
        if (yyyymm != null && !"".equals(yyyymm))
        {
        	// toMonth가 오늘이후면 오늘까지만 조회
        	if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(yyyymm)))
        	{
        		query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.getDate());
        	}
        	else{
        		query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.plusLastDayOfMonth(yyyymm));
        	}
        }
        query.getAndQuery("a.plant", workRptPmiEquipPlanDetailDTO.getPlantId());
        
        // 예방작업설비 삭제여부
        query.append("AND EXISTS (SELECT aa.equip_id                    ");
        query.append("            FROM TAPMEQUIP aa                    	");
        query.append("            WHERE 1=1                           	");
        query.append("			   AND aa.comp_no = b.comp_no			");
    	query.append("			    AND aa.pmequip_id = b.pmequip_id	");
        query.getAndQuery("aa.is_deleted", "N");
        query.append("           )                   					");
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO, User loginUser)  throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                          	");
        query.append("    COUNT(1)                                    	");
        query.append("FROM TAPMINSLIST a INNER JOIN TAPMINSSCHED b 		");
        query.append("ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id	");
        query.append("WHERE 1=1     									");
        query.append(this.getWhere(workRptPmiEquipPlanDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}