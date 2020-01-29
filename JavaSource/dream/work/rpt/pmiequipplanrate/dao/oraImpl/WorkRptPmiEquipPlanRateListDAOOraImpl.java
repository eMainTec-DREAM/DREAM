package dream.work.rpt.pmiequipplanrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pmiequipplanrate.dao.WorkRptPmiEquipPlanRateListDAO;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;

/**
 * 예방점검 설비 계획대비 실행 비율 목록  - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiEquipPlanRateListDAOTarget"
 * @spring.txbn id="workRptPmiEquipPlanRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiEquipPlanRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptPmiEquipPlanRateListDAO
{

    /**
     * grid find
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipPlanRateCommonDTO
     * @return List
     */
    public List findList(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        														");
        query.append("   ''        										AS SEQNO			");
        query.append("  ,b.plant        								AS plant			");
        query.append("  ,(SELECT description FROM TAPLANT WHERE comp_no = b.comp_no AND plant = b.plant)	AS plantDesc	");
        query.append("  ,SUBSTR(a.sched_date,1,6)     					AS yyyymm 			");
        query.append("  ,NVL(sum(CASE WHEN b.start_date <= a.sched_date  THEN 1 ELSE 0 END),0)				AS completeCnt	");
        query.append("  ,count(*)       								AS totCnt			");
        query.append("  ,round(     														");
        query.append("      (  sum(CASE WHEN b.start_date <= a.sched_date  THEN 1.0 ELSE 0 END)	");
        query.append("       / count(*)     												");
        query.append("     ) *100,2)         							AS completeRate		");
        query.append("FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b         					");
        query.append(" ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id      	");
        query.append("WHERE 1=1                 				         					");
        query.append(this.getWhere(workRptPmiEquipPlanRateCommonDTO, user));
        query.append("GROUP BY b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)					");
        query.getOrderByQuery("b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)", workRptPmiEquipPlanRateCommonDTO.getOrderBy(), workRptPmiEquipPlanRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiEquipPlanRateCommonDTO.getIsLoadMaxCount(), workRptPmiEquipPlanRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptPmiEquipPlanRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        query.getAndQuery("a.is_deleted", "N");
        query.append(" AND b.pmsched_status IN('PRC', 'C')      ");
        
        query.getPlantCdQuery("b.plant", workRptPmiEquipPlanRateCommonDTO.getFilterPlantId(), workRptPmiEquipPlanRateCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        // 월
        String fromMonth = workRptPmiEquipPlanRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptPmiEquipPlanRateCommonDTO.getFilterEndDate();
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
        	// toMonth가 오늘이후면 오늘까지만 조회
        	if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(toMonth)))
        	{
        		query.getAndDateQuery("a.sched_date", fromMonth +"01", DateUtil.getDate());
        	}
        	else{
        		query.getAndDateQuery("a.sched_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
        	}
        }
        
        // 예방작업설비 삭제여부
        query.append("AND EXISTS (SELECT aa.equip_id                    ");
        query.append("            FROM TAPMEQUIP aa                    	");
        query.append("            WHERE 1=1                           	");
        query.append("			   AND aa.comp_no = a.comp_no			");
    	query.append("			    AND aa.pmequip_id = a.pmequip_id	");
        query.getAndQuery("aa.is_deleted", "N");
        query.append("           )                   					");
        
        return query.toString();
    }

    public String findTotalCount(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            			");
        query.append("    	count(1)												");
        query.append("FROM(															");
        query.append("		SELECT 													");
        query.append("	  		b.comp_no,b.plant,SUBSTR(a.sched_date,1,6) yyyymm	");
        query.append(" 		FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b         	");
        query.append(" 		 ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id	");
        query.append(" 		WHERE 1=1        										");
        query.append(this.getWhere(workRptPmiEquipPlanRateCommonDTO, user));
        query.append(" 		GROUP BY b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)		");
        query.append(" ) aa															");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
