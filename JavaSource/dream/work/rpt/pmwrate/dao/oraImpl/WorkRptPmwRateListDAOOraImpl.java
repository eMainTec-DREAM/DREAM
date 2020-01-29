package dream.work.rpt.pmwrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.pmwrate.dao.WorkRptPmwRateListDAO;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;

/**
 * 주기정비 실행 비율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmwRateListDAOTarget"
 * @spring.txbn id="workRptPmwRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmwRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptPmwRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwRateCommonDTO
     * @return List
     */
    public List findList(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("   '' SEQNO      ");
        query.append("  ,c.plant      ");
        query.append("  ,(SELECT description FROM TAPLANT WHERE comp_no = c.comp_no AND plant = c.plant) plant_desc     ");
        query.append("  ,SUBSTR(b.wkor_date,1,6) AS yyyymm     ");
        query.append("  ,sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1 ELSE 0 END) AS c_cnt       ");
        query.append("  ,count(*)  AS t_cnt     ");
        query.append("  ,round(((sum(CASE WHEN a.PMSCHED_STATUS = 'C' THEN 1.0 ELSE 0 END)) / count(*)) *100,2) AS c_rate       ");
        query.append(" FROM TAPMSCHED a INNER JOIN TAWORKORDER b        ");
        query.append("  ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append(" INNER JOIN TAPMLST c ON a.comp_no = c.comp_no AND a.pm_id = c.pm_id ");
        query.append(" INNER JOIN TAPMEQUIP d ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id AND a.pmequip_id = d.pmequip_id ");
        query.append(" WHERE 1=1        ");
        query.append(this.getWhere(workRptPmwRateCommonDTO, user));
        query.append(" GROUP BY c.comp_no,c.plant,SUBSTR(b.wkor_date,1,6)      ");
        query.getOrderByQuery("c.comp_no,c.plant,SUBSTR(b.wkor_date,1,6)", workRptPmwRateCommonDTO.getOrderBy(), workRptPmwRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmwRateCommonDTO.getIsLoadMaxCount(), workRptPmwRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptPmwRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.append("  AND a.is_deleted = 'N'      ");
        query.append("  AND c.is_deleted = 'N'      ");
        query.append("  AND d.is_deleted = 'N'      ");
        query.append("  AND b.wo_type = 'PMW'       ");
        query.append("  AND c.plant IS NOT NULL     ");
        
        // 월
        String fromMonth = workRptPmwRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptPmwRateCommonDTO.getFilterEndDate();
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
            // toMonth가 오늘이후면 오늘까지만 조회
            if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(toMonth)))
            {
                query.getAndDateQuery("b.wkor_date", fromMonth +"01", DateUtil.getDate());
            }
            else{
                query.getAndDateQuery("b.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
            }
        }
        
        query.getPlantCdQuery("c.plant", workRptPmwRateCommonDTO.getFilterPlantId(), workRptPmwRateCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        
        return query.toString();
    }

    public String findTotalCount(
            WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            	");
        query.append("    		count(1)									");
        query.append("from  (																");
        query.append("SELECT c.comp_no,c.plant,SUBSTR(b.wkor_date,1,6) yyyymm       ");
        query.append(" FROM TAPMSCHED a INNER JOIN TAWORKORDER b        ");
        query.append("  ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append(" INNER JOIN TAPMLST c ON a.comp_no = c.comp_no AND a.pm_id = c.pm_id ");
        query.append(" INNER JOIN TAPMEQUIP d ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id AND a.pmequip_id = d.pmequip_id ");
        query.append(" WHERE 1=1        ");
        query.append(this.getWhere(workRptPmwRateCommonDTO, user));
        query.append(" GROUP BY c.comp_no,c.plant,SUBSTR(b.wkor_date,1,6)      ");
        query.append(")	x											");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
