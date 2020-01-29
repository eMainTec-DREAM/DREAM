package dream.work.rpt.pmwcmptrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmwcmptrate.dao.WorkRptPmwCmptRateListDAO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;

/**
 * 주기정비 계획대비 실행 비율 목록 - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPmwCmptRateListDAOTarget"
 * @spring.txbn id="workRptPmwCmptRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmwCmptRateListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements WorkRptPmwCmptRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwCmptRateCommonDTO
     * @return List
     */
    public List findList(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     															");
        query.append("SELECT        ");
        query.append("   '' SEQNO     ");
        query.append("  ,c.plant      ");
        query.append("  ,(SELECT description FROM TAPLANT WHERE comp_no = c.comp_no AND plant = c.plant) PLANTDESC      ");
        query.append("  ,LEFT(a.sched_date,6) AS yyyymm     ");
        query.append("  ,isnull(sum(CASE WHEN b.start_date <= a.sched_date  THEN 1 ELSE 0 END),0) AS c_cnt      ");
        query.append("  ,count(*) AS t_cnt      ");
        query.append("  ,convert(numeric(5,2)       ");
        query.append("      ,(  sum(CASE WHEN b.start_date <= a.sched_date  THEN 1.0 ELSE 0 END)        ");
        query.append("       / count(*)     ");
        query.append("     ) *100) AS c_rate        ");
        query.append(" FROM TAPMSCHED a INNER JOIN TAWORKORDER b        ");
        query.append("  ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append(" INNER JOIN TAPMLST c ON a.comp_no = c.comp_no AND a.pm_id = c.pm_id ");
        query.append(" INNER JOIN TAPMEQUIP d ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id AND a.pmequip_id = d.pmequip_id ");
        query.append(" WHERE 1=1        ");
        query.append(this.getWhere(workRptPmwCmptRateCommonDTO, user));
        query.append(" GROUP BY c.comp_no,c.plant,LEFT(a.sched_date,6)      ");
        query.getOrderByQuery("c.comp_no", "c.comp_no,c.plant,LEFT(a.sched_date,6)", workRptPmwCmptRateCommonDTO.getOrderBy(), workRptPmwCmptRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmwCmptRateCommonDTO.getIsLoadMaxCount(), workRptPmwCmptRateCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param workRptPmwCmptRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("  AND a.is_deleted = 'N'      ");
        query.append("  AND c.is_deleted = 'N'      ");
        query.append("  AND d.is_deleted = 'N'      ");
        query.append("  AND a.pmsched_status = 'C'      ");
        query.append(" AND b.wo_status IN('PRC', 'C')       ");
        query.append(" AND b.wo_type = 'PMW'        ");
        query.append("  AND c.plant IS NOT NULL     ");
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        // 월
        String fromMonth = workRptPmwCmptRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptPmwCmptRateCommonDTO.getFilterEndDate();
        
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
        
        query.getPlantCdQuery("c.plant", workRptPmwCmptRateCommonDTO.getFilterPlantId(), workRptPmwCmptRateCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        return query.toString();
    }
    
    public String findTotalCount(
            WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     		");
        query.append("SELECT                                                ");
        query.append("          count(1)                                    ");
        query.append("FROM (                                                ");
        query.append("SELECT c.comp_no,c.plant,LEFT(a.sched_date,6) yyyymm  ");
        query.append(" FROM TAPMSCHED a INNER JOIN TAWORKORDER b        ");
        query.append("  ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append(" INNER JOIN TAPMLST c ON a.comp_no = c.comp_no AND a.pm_id = c.pm_id ");
        query.append(" INNER JOIN TAPMEQUIP d ON c.comp_no = d.comp_no AND c.pm_id = d.pm_id AND a.pmequip_id = d.pmequip_id ");
        query.append(" WHERE 1=1        ");
        query.append(this.getWhere(workRptPmwCmptRateCommonDTO, user));
        query.append(" GROUP BY c.comp_no,c.plant,LEFT(a.sched_date,6)      ");
        query.append(") x                                                   ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
