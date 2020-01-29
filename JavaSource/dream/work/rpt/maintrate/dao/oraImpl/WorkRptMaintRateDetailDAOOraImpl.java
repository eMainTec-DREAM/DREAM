package dream.work.rpt.maintrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.maintrate.dao.WorkRptMaintRateDetailDAO;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateDetailDTO;

/**
 * WorkRptMaintRate Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="workRptMaintRateDetailDAOTarget"
 * @spring.txbn id="workRptMaintRateDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptMaintRateDetailDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptMaintRateDetailDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptMaintRateDetailDTO
     * @return List
     */
    public List findPartDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                    ");
        query.append("    ''                              AS SEQNO              ");
        query.append("  , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id) PARTDESC          ");
        query.append("  , COUNT(1)                 AS ALLMAINTCNT       ");
        query.append("  , NVL(SUM(x.tot_amt),0)           AS USEAMT                 ");
        query.append("  , COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) AS COMPLETECNT      ");
        query.append("  , ROUND(COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) /COUNT(1)*100,2)  AS COMPLETERATE         ");
        query.append("FROM TAWORKORDER x                                        ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(workRptMaintRateDetailDTO, user));
        query.append("GROUP BY x.comp_no, x.eqloc_id         ");
        query.getOrderByQuery("(SELECT a.full_desc FROM TAEQLOC a "
                + "WHERE a.comp_no = x.comp_no AND a.eqloc_id = x.eqloc_id)"
        , workRptMaintRateDetailDTO.getOrderBy(), workRptMaintRateDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptMaintRateDetailDTO
     * @return List
     */
    public List findTypeDetail(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    ''                              AS SEQNO              ");
        query.append("  , SFACODETODESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no) AS WOTYPEDESC     ");
        query.append("  , COUNT(1)                 AS ALLMAINTCNT       ");
        query.append("  , NVL(SUM(x.tot_amt),0)           AS USEAMT                 ");
        query.append("  , COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) AS COMPLETECNT      ");
        query.append("  , ROUND(COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) /COUNT(1)*100,2)  AS COMPLETERATE         ");
        query.append("FROM TAWORKORDER x                                        ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptMaintRateDetailDTO, user));
        query.append("GROUP BY x.comp_no, x.wo_type         ");
        query.getOrderByQuery("SFACODETODESC(x.wo_type, 'WO_TYPE', 'SYS', x.comp_no)"
                , workRptMaintRateDetailDTO.getOrderBy(), workRptMaintRateDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * find chart
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptMaintRateDetailDTO
     * @return List
     */
    public List findDayChart(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                       ");
        query.append("    NVL(SUM(x.tot_amt),0)   AS USEAMT        ");
        query.append("  , COUNT(1)                AS ALLMAINTCNT   ");
        query.append("  , COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) AS COMPLETECNT              ");
        query.append("  , ROUND(COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END) /COUNT(1)*100,2)  AS COMPLETERATE                 ");
        query.append("  , x.wkor_date             AS WKORDATE      ");
        query.append("FROM TAWORKORDER x                           ");
        query.append("WHERE 1=1                                    ");
        query.append(this.getWhere(workRptMaintRateDetailDTO, user));
        query.append("GROUP BY x.comp_no, x.wkor_date              ");

        query.getOrderByQuery("x.wkor_date", workRptMaintRateDetailDTO.getOrderBy(), workRptMaintRateDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptMaintRateDetailDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.dept_id", workRptMaintRateDetailDTO.getDeptId());
        query.append("AND x.wo_type NOT IN ('PMI','PMC')");
        query.append("AND x.dept_id IS NOT NULL               ");
        query.append("AND x.eqloc_id IS NOT NULL               ");
        
        // 공장
        query.getCodeLikeQuery("(SELECT y.plant FROM TADEPT y WHERE y.comp_no = x.comp_no AND y.dept_id = x.dept_id)"
                , "(SELECT z.description FROM TAPLANT z WHERE z.comp_no = x.comp_no AND z.plant = (SELECT y.plant FROM TADEPT y WHERE y.comp_no = x.comp_no AND y.dept_id = x.dept_id))"
                , workRptMaintRateDetailDTO.getPlantId(), workRptMaintRateDetailDTO.getPlantDesc());
        
        // 일자
        String fromDate = workRptMaintRateDetailDTO.getStartDate();
        String toDate   = workRptMaintRateDetailDTO.getEndDate();
        
        query.getAndDateQuery("x.wkor_date", fromDate, toDate);

        return query.toString();
    }
}
