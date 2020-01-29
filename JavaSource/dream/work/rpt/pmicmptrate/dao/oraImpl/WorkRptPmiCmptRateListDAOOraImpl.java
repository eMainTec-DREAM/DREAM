package dream.work.rpt.pmicmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmicmptrate.dao.WorkRptPmiCmptRateListDAO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;

/**
 * 예방점검항목 실행 비율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiCmptRateListDAOTarget"
 * @spring.txbn id="workRptPmiCmptRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmiCmptRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptPmiCmptRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptRateCommonDTO
     * @return List
     */
    public List findList(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        													");
        query.append("	   '' 												AS SEQNO	");
        query.append("	  ,b.plant       									AS plant	");
        query.append("	  ,(SELECT description FROM TAPLANT WHERE comp_no=b.comp_no AND plant=b.plant)	AS plantDesc	");
        query.append("	  ,SUBSTR(a.sched_date,1,6) 						AS yyyymm	");
        query.append("	  ,sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1 ELSE 0 END)	AS completeCnt	");
        query.append("	  ,count(*)  										AS totCnt	");
        query.append("	  ,round(((sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1.0 ELSE 0 END)) / count(*)) *100,2)	AS completeRate	");
        query.append("FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b         				");
        query.append(" ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id	");
        query.append(" INNER JOIN TAPMINSPOINT c       									");
        query.append(" ON b.comp_no = c.comp_no AND b.pminslist_id = c.pminslist_id     ");
        query.append("WHERE 1=1        													");
        query.append(this.getWhere(workRptPmiCmptRateCommonDTO, user));
        query.append("GROUP BY b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)      ");
//        query.append("ORDER BY b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)       ");
        query.getOrderByQuery("b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)", workRptPmiCmptRateCommonDTO.getOrderBy(), workRptPmiCmptRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmiCmptRateCommonDTO.getIsLoadMaxCount(), workRptPmiCmptRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptPmiCmptRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());

        query.getAndQuery("a.is_deleted", "N");
        
        // 공장
        query.getPlantCdQuery("b.plant", workRptPmiCmptRateCommonDTO.getFilterPlantId(), workRptPmiCmptRateCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        // 월
        String fromMonth = workRptPmiCmptRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptPmiCmptRateCommonDTO.getFilterEndDate();
        
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
        query.append("            FROM TAPMEQUIP aa INNER JOIN TAEQUIPMENT bb   ");
        query.append("            ON aa.comp_no = bb.comp_no            ");
        query.append("            AND aa.equip_id = bb.equip_id         ");
        query.append("            WHERE 1=1                           	");
        query.append("			   AND aa.comp_no = a.comp_no			");
    	query.append("			    AND aa.pmequip_id = a.pmequip_id	");
        query.getAndQuery("aa.is_deleted", "N");
        query.getAndQuery("bb.is_deleted", "N");
        query.append("           )                   					");
        
        return query.toString();
    }

    public String findTotalCount(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            						");
        query.append("    	count(1)															");
        query.append("FROM(																		");
        query.append("		SELECT 																");
        query.append("	  		b.comp_no,b.plant,SUBSTR(a.sched_date,1,6) yyyymm				");
        query.append("		FROM TAPMINSSCHED a INNER JOIN TAPMINSLIST b         				");
        query.append("		ON a.comp_no = b.comp_no AND a.pminssched_id = b.pminssched_id		");
        query.append("			INNER JOIN TAPMINSPOINT c       								");
        query.append("		 	ON b.comp_no = c.comp_no AND b.pminslist_id = c.pminslist_id	");
        query.append("		WHERE 1=1        													");
        query.append(this.getWhere(workRptPmiCmptRateCommonDTO, user));
        query.append("		GROUP BY b.comp_no,b.plant,SUBSTR(a.sched_date,1,6)      			");
        query.append(" ) aa																		");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
