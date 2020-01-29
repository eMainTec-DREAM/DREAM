package dream.req.rpt.preworeqrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.rpt.preworeqrate.dao.ReqRptPreWoreqRateListDAO;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;

/**
 * 작업의뢰 사전 시스템 요청율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptPreWoreqRateListDAOTarget"
 * @spring.txbn id="reqRptPreWoreqRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptPreWoreqRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ReqRptPreWoreqRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoreqRateCommonDTO
     * @return List
     */
    public List findList(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                    ");
        query.append("       ''                                                                 AS SEQNO        ");
        query.append("     , a.plant                                                            AS plantId      ");
        query.append("     , ( SELECT description                                                               ");
        query.append("           FROM taplant x                                                                 ");
        query.append("          WHERE x.comp_no = a.comp_no                                                     ");
        query.append("            AND x.plant   = a.plant )                                     AS plantDesc    ");
        query.append("     , a.yyyymm                                                           AS month        ");
        query.append("     , SUM(tot_cnt)                                                       AS totCnt       ");
        query.append("     , SUM(c_cnt)                                                         AS mitCnt       ");
        query.append("     , ROUND((SUM(c_cnt) * 1.0 / SUM(tot_cnt) *100), 2)                   AS mitRate      ");
        query.append(" FROM  ( SELECT                                                                           ");
        query.append("                a.comp_no                                                                 ");
        query.append("              , a.plant                                                                   ");
        query.append("              , (SUBSTR(a.wkor_date, 0, 4)||'-'||SUBSTR(a.wkor_date, 5, 2)) AS yyyymm ");
        query.append("              , ( CASE WHEN (SELECT max(aa.wkor_id)                                       ");
        query.append("                               FROM TAWOREQRES aa                                         ");
        query.append("                              WHERE a.comp_no  = aa.comp_no                               ");
        query.append("                                AND  a.wkor_id = aa.wkor_id) IS NULL                      ");
        query.append("                       THEN 0                                                             ");
        query.append("                       ELSE 1                                                             ");
        query.append("                        END )                                             AS c_cnt        ");
        query.append("              , 1                                                         AS tot_cnt      ");
        query.append("           FROM TAWORKORDER a INNER JOIN TAWOEQUIP b                                      ");
        query.append("                                 ON a.comp_no = b.comp_no                                 ");
        query.append("                                AND a.wkor_id = b.wkor_id                                 ");
        query.append("                               LEFT OUTER JOIN TAWOREQRES c                               ");
        query.append("                                 ON a.comp_no = c.comp_no                                 ");
        query.append("                                AND a.wkor_id = c.wkor_id                                 ");
        query.append("                               LEFT OUTER JOIN TAWOREQ d                                  ");
        query.append("                                 ON c.comp_no = d.comp_no                                 ");
        query.append("                                AND c.woreq_id = d.woreq_id                               ");
        query.append("          WHERE 1 = 1                                                                     ");
        query.append(this.getWhere(reqRptPreWoreqRateCommonDTO, user));
        query.append("       ) a                                                                                ");
        query.append("WHERE 1 = 1                                                                               ");
        query.append("GROUP BY a.plant, a.yyyymm, a.comp_no                                                     ");
        query.getOrderByQuery("a.comp_no", "a.plant", reqRptPreWoreqRateCommonDTO.getOrderBy(), reqRptPreWoreqRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptPreWoreqRateCommonDTO.getIsLoadMaxCount(), reqRptPreWoreqRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param reqRptPreWoreqRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("a.plant", "(SELECT x.description FROM TAPLANT x WHERE x.comp_no = '"+user.getCompNo()+"' AND x.plant = a.plant )", 
                reqRptPreWoreqRateCommonDTO.getFilterPlantId(), reqRptPreWoreqRateCommonDTO.getFilterPlantDesc());
        
        // 삭제여부
        query.append(" AND a.is_deleted = 'N' ");
        
        // 작업서 발생 구분
        query.append(" AND a.wo_gen_type != 'PMPLAN' ");
        
        //월
        query.getAndDateQuery("a.wkor_date", reqRptPreWoreqRateCommonDTO.getFilterStartDate()+"01", DateUtil.plusLastDayOfMonth(reqRptPreWoreqRateCommonDTO.getFilterEndDate()));
        // 작업상태
        query.append(" AND a.wo_status IN ('PRC', 'C') ");
        
        return query.toString();
    }

    public String findTotalCount(
            ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*) FROM (                                                                    ");
        query.append("SELECT                                                                                    ");
        query.append("       ''                                                                 AS SEQNO        ");
        query.append("     , a.plant                                                            AS plantId      ");
        query.append("     , ( SELECT description                                                               ");
        query.append("           FROM taplant x                                                                 ");
        query.append("          WHERE x.comp_no = a.comp_no                                                     ");
        query.append("            AND x.plant   = a.plant )                                     AS plantDesc    ");
        query.append("     , a.yyyymm                                                           AS month        ");
        query.append("     , SUM(tot_cnt)                                                       AS totCnt       ");
        query.append("     , SUM(c_cnt)                                                         AS mitCnt       ");
        query.append("     , ROUND((SUM(c_cnt) * 1.0 / SUM(tot_cnt) *100), 2)                   AS mitRate      ");
        query.append(" FROM  ( SELECT                                                                           ");
        query.append("                a.comp_no                                                                 ");
        query.append("              , a.plant                                                                   ");
        query.append("              , (SUBSTR(a.wkor_date, 0, 4)||'-'||SUBSTR(a.wkor_date, 5, 2)) AS yyyymm     ");
        query.append("              , ( CASE WHEN (SELECT max(aa.wkor_id)                                       ");
        query.append("                               FROM TAWOREQRES aa                                         ");
        query.append("                              WHERE a.comp_no  = aa.comp_no                               ");
        query.append("                                AND  a.wkor_id = aa.wkor_id) IS NULL                      ");
        query.append("                       THEN 0                                                             ");
        query.append("                       ELSE 1                                                             ");
        query.append("                        END )                                             AS c_cnt        ");
        query.append("              , 1                                                         AS tot_cnt      ");
        query.append("           FROM TAWORKORDER a INNER JOIN TAWOEQUIP b                                      ");
        query.append("                                 ON a.comp_no = b.comp_no                                 ");
        query.append("                                AND a.wkor_id = b.wkor_id                                 ");
        query.append("                               LEFT OUTER JOIN TAWOREQRES c                               ");
        query.append("                                 ON a.comp_no = c.comp_no                                 ");
        query.append("                                AND a.wkor_id = c.wkor_id                                 ");
        query.append("                               LEFT OUTER JOIN TAWOREQ d                                  ");
        query.append("                                 ON c.comp_no = d.comp_no                                 ");
        query.append("                                AND c.woreq_id = d.woreq_id                               ");
        query.append("          WHERE 1 = 1                                                                     ");
        query.append(this.getWhere(reqRptPreWoreqRateCommonDTO, user));
        query.append("       ) a                                                                                ");
        query.append("WHERE 1 = 1                                                                               ");
        query.append("GROUP BY a.plant, a.yyyymm, a.comp_no                                                     ");
        query.append(") b                                                                                       ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
