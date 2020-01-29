package dream.work.rpt.pmiequipcmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pmiequipcmptrate.dao.WorkRptPmiEquipCmptRateListDAO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;

/**
 * 예방점검설비 실행 비율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiEquipCmptRateListDAOTarget"
 * @spring.txbn id="workRptPmiEquipCmptRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiEquipCmptRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptPmiEquipCmptRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiEquipCmptRateCommonDTO
     * @return List
     */
    public List findList(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																		");
        query.append("    '' 														AS seqNo		");
        query.append("    ,b.plant    												AS plantId		");
        query.append("	  ,(SELECT aa.description FROM TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = b.plant)	AS plantDesc	");
        query.append("	  ,substr(a.sched_date,1,6) 								AS yyyymm		");
        query.append("	  ,sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1 ELSE 0 END) 	AS completeCnt	");
        query.append("	  ,count(*)  												AS totCnt		");
        query.append("	  ,round((((sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1.0 ELSE 0 END))/count(*))*100), 2) 	AS completeRate	");
        query.append("FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b 									");
        query.append(" ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id 				");
        query.append("WHERE 1=1																		");
        query.append(this.getWhere(workRptPmiEquipCmptRateCommonDTO, user));
        query.append("GROUP BY b.plant, substr(a.sched_date,1,6), b.comp_no 									");
        query.getOrderByQuery("b.comp_no", "b.plant, substr(a.sched_date,1,6)", workRptPmiEquipCmptRateCommonDTO.getOrderBy(), workRptPmiEquipCmptRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiEquipCmptRateCommonDTO.getIsLoadMaxCount(), workRptPmiEquipCmptRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptPmiEquipCmptRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());

        query.getAndQuery("a.is_deleted", "N");
        
        // 공장
        query.getCodeLikeQuery("b.plant", "(SELECT aa.description FROM TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = b.plant )", 
        		workRptPmiEquipCmptRateCommonDTO.getFilterPlantId(), workRptPmiEquipCmptRateCommonDTO.getFilterPlantDesc());
        // 월
        String fromMonth = workRptPmiEquipCmptRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptPmiEquipCmptRateCommonDTO.getFilterEndDate();
        
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

    public String findTotalCount(
            WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            	");
        query.append("    	count(1)										");
        query.append("FROM(													");
        query.append("		SELECT 											");
        query.append("	  		'' 							AS seqNo		");
        query.append("		FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b	");
        query.append("		ON a.comp_no = b.comp_no 						");
        query.append("		  AND a.pminssched_id = b.pminssched_id 		");
        query.append("		WHERE 1=1										");
        query.append(this.getWhere(workRptPmiEquipCmptRateCommonDTO, user));
        query.append("		GROUP BY b.plant,substr(a.sched_date,1,6)		");
        query.append(" ) aa													");
        query.append("WHERE 1=1												");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
