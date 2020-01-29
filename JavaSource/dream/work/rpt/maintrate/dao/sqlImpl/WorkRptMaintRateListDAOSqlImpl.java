package dream.work.rpt.maintrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.maintrate.dao.WorkRptMaintRateListDAO;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;

/**
 * WorkRptMaintRate Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptMaintRateListDAOTarget"
 * @spring.txbn id="workRptMaintRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptMaintRateListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements WorkRptMaintRateListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptMaintRateCommonDTO
     * @return List
     */
    public List findList(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                                                                                                           ");
        query.append("    ''                                                                                                           AS SEQNO        ");
        query.append("  , (SELECT a.plant FROM TADEPT a WHERE a.comp_no = i.comp_no AND a.dept_id = i.dept_id)                         AS PLANTID      ");
        query.append("  , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = i.comp_no                                                             ");
        query.append("                                         AND a.plant = (SELECT a.plant FROM TADEPT a WHERE a.comp_no = i.comp_no                 ");
        query.append("                                                                                     AND a.dept_id = i.dept_id)) AS PLANTDESC    ");
        query.append("  , i.dept_id                                                                                                    AS DEPTID       ");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = i.comp_no AND a.dept_id = i.dept_id)                   AS TEAMDESC     ");
        query.append("  , ISNULL(SUM(i.tot_amt),0) + ISNULL(SUM(i.woparts_tot_price),0)                                                AS USEAMT       ");
        query.append("  , COUNT(1)                                                                                                     AS ALLMAINTCNT  ");
        query.append("  , SUM(i.complete_cnt)                                                                                          AS COMPLETECNT  ");
        query.append("  , ROUND(SUM(i.complete_cnt) / CONVERT(FLOAT, COUNT(1))*100,2)                                                  AS COMPLETERATE ");
        query.append("  FROM(                                                                                                                          ");
        query.append("    SELECT                                                                     ");
        query.append("        x.comp_no                                         AS comp_no           ");
        query.append("      , x.dept_id                                         AS dept_id           ");
        query.append("      , x.wkor_id                                         AS wkor_id           ");
        query.append("      , x.tot_amt                                         AS tot_amt           ");
        query.append("      , (SELECT SUM(tot_price) FROM TAWOPARTS                                  ");
        query.append("                               WHERE comp_no = x.comp_no                       ");
        query.append("                               AND wkor_id = x.wkor_id)   AS woparts_tot_price ");
        query.append("      , COUNT(CASE WHEN x.wo_status = 'C' THEN 1 END)     AS complete_cnt      ");
        query.append("    FROM TAWORKORDER x                                                         ");
        query.append("    WHERE 1=1                                                                  ");
        query.append(this.getWhere(workRptMaintRateCommonDTO, user));
        query.append("    GROUP BY x.comp_no, x.dept_id, x.wkor_id, x.tot_amt                        ");
        query.append("  ) i                                                                                                                             ");
        query.append("GROUP BY i.comp_no, i.dept_id                                                                                                     ");
        
        query.getOrderByQuery("i.comp_no", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = i.comp_no "
                + "AND a.plant = (SELECT a.plant FROM TADEPT a WHERE a.comp_no = i.comp_no AND a.dept_id = i.dept_id))", workRptMaintRateCommonDTO.getOrderBy(), workRptMaintRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptMaintRateCommonDTO.getIsLoadMaxCount(), workRptMaintRateCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workRptMaintRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("AND x.wo_type NOT IN ('PMI','PMC')");
        
        // 공장
        query.getCodeLikeQuery("(SELECT y.plant FROM TADEPT y WHERE y.comp_no = x.comp_no AND y.dept_id = x.dept_id)"
                , "(SELECT z.description FROM TAPLANT z WHERE z.comp_no = x.comp_no AND z.plant = (SELECT y.plant FROM TADEPT y WHERE y.comp_no = x.comp_no AND y.dept_id = x.dept_id))"
                , workRptMaintRateCommonDTO.getFilterPlantId(), workRptMaintRateCommonDTO.getFilterPlantDesc());
       
        // 일자
        String fromDate = workRptMaintRateCommonDTO.getFilterStartDate();
        String toDate   = workRptMaintRateCommonDTO.getFilterEndDate();
        
        query.getAndDateQuery("x.wkor_date", fromDate, toDate);
        
        return query.toString();
    }
    
    public String findTotalCount(
            WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM (                                            ");
        query.append("SELECT                                            ");
        query.append("    ''                              AS SEQNO      ");
        query.append("FROM TAWORKORDER x                                ");
        query.append("WHERE 1=1                                         ");
        query.append(this.getWhere(workRptMaintRateCommonDTO, user));
        query.append("GROUP BY x.comp_no, x.dept_id                     ");
        query.append(" ) a ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
