package dream.req.rpt.woplancmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.rpt.woplancmptrate.dao.ReqRptWoPlanCmptRateListDAO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;

/**
 * �۾��Ƿ� ��ȹ��� ���� ���� ���  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptWoPlanCmptRateListDAOTarget"
 * @spring.txbn id="reqRptWoPlanCmptRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptWoPlanCmptRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ReqRptWoPlanCmptRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoPlanCmptRateCommonDTO
     * @return List
     */
    public List findList(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                    ");
        query.append("       ''                                                                                 AS seqNo        ");
        query.append("     , b.plant                                                                            AS plantId      ");
        query.append("     , ( SELECT description                                                                               ");
        query.append("           FROM TAPLANT                                                                                   ");
        query.append("          WHERE comp_no = b.comp_no                                                                       ");
        query.append("            AND plant   = b.plant )                                                       AS plantDesc    ");
        query.append("     , (SUBSTR(b.wkor_date, 0, 4)||'-'||SUBSTR(b.wkor_date, 5, 2))                        AS month        ");
        query.append("     , COUNT(*)                                                                           AS totCnt       ");
        query.append("     , SUM(CASE WHEN NVL(c.end_date, b.end_date) >= b.end_date                                            ");
        query.append("                THEN 1                                                                                    ");
        query.append("                ELSE 0                                                                                    ");
        query.append("                 END)                                                                     AS mitCnt       ");
        query.append("     , ROUND((( SUM(CASE WHEN NVL(c.end_date,b.end_date) >= b.end_date                                    ");
        query.append("                         THEN 1.0                                                                         ");
        query.append("                         ELSE 0                                                                           ");
        query.append("                          END) / COUNT(*)) * 100 ),2)                                     AS mitRate      ");
        query.append("  FROM TAWOREQRES a INNER JOIN TAWORKORDER b                                                              ");
        query.append("                      ON a.comp_no = b.comp_no                                                            ");
        query.append("                     AND a.wkor_id = b.wkor_id                                                            ");
        query.append("                    LEFT OUTER JOIN TAWOPLAN c                                                            ");
        query.append("                      ON a.comp_no = c.comp_no                                                            ");
        query.append("                     AND a.wkor_id = c.wkor_id                                                            ");
        query.append(" WHERE 1 = 1                                                                                              ");
        query.append(this.getWhere(reqRptWoPlanCmptRateCommonDTO, user));
        query.append(" GROUP BY b.plant, (SUBSTR(b.wkor_date, 0, 4)||'-'||SUBSTR(b.wkor_date, 5, 2)), b.comp_no                 ");
        query.getOrderByQuery("b.comp_no", "b.plant, (SUBSTR(b.wkor_date, 0, 4)||'-'||SUBSTR(b.wkor_date, 5, 2))", reqRptWoPlanCmptRateCommonDTO.getOrderBy(), reqRptWoPlanCmptRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptWoPlanCmptRateCommonDTO.getIsLoadMaxCount(), reqRptWoPlanCmptRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter ����
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param reqRptWoPlanCmptRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
query.getAndQuery("a.comp_no", user.getCompNo());
        
        // ����
        query.getCodeLikeQuery("b.plant", "(SELECT xx.description FROM  TAPLANT xx WHERE xx.comp_no = '"+ user.getCompNo() +"' AND b.plant = xx.plant)", 
                reqRptWoPlanCmptRateCommonDTO.getFilterPlantId(), reqRptWoPlanCmptRateCommonDTO.getFilterPlantDesc());
        // ��
        query.getAndDateQuery("b.wkor_date", reqRptWoPlanCmptRateCommonDTO.getFilterStartDate()+"01", DateUtil.plusLastDayOfMonth(reqRptWoPlanCmptRateCommonDTO.getFilterEndDate()));
        
        query.getAndQuery("b.is_deleted", "N");
        
        query.append(" AND b.wo_status IN('PRC', 'C')   ");
        
        return query.toString();
    }

    public String findTotalCount(
            ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*) FROM (                                                                                    ");
        query.append("SELECT                                                                                                    ");
        query.append("       ''                                                                                 AS seqNo        ");
        query.append("     , b.plant                                                                            AS plantId      ");
        query.append("     , ( SELECT description                                                                               ");
        query.append("           FROM TAPLANT                                                                                   ");
        query.append("          WHERE comp_no = b.comp_no                                                                       ");
        query.append("            AND plant   = b.plant )                                                       AS plantDesc    ");
        query.append("     , (SUBSTR(b.wkor_date, 0, 4)||'-'||SUBSTR(b.wkor_date, 5, 2))                          AS month        ");
        query.append("     , COUNT(*)                                                                           AS totCnt       ");
        query.append("     , SUM(CASE WHEN NVL(c.end_date, b.end_date) >= b.end_date                                            ");
        query.append("                THEN 1                                                                                    ");
        query.append("                ELSE 0                                                                                    ");
        query.append("                 END)                                                                     AS mitCnt       ");
        query.append("     , ROUND((( SUM(CASE WHEN NVL(c.end_date,b.end_date) >= b.end_date                                    ");
        query.append("                         THEN 1.0                                                                         ");
        query.append("                         ELSE 0                                                                           ");
        query.append("                          END) / COUNT(*)) * 100 ), 2)                                    AS mitRate      ");
        query.append("  FROM TAWOREQRES a INNER JOIN TAWORKORDER b                                                              ");
        query.append("                      ON a.comp_no = b.comp_no                                                            ");
        query.append("                     AND a.wkor_id = b.wkor_id                                                            ");
        query.append("                    LEFT OUTER JOIN TAWOPLAN c                                                            ");
        query.append("                      ON a.comp_no = c.comp_no                                                            ");
        query.append("                     AND a.wkor_id = c.wkor_id                                                            ");
        query.append(" WHERE 1 = 1                                                                                              ");
        query.append(this.getWhere(reqRptWoPlanCmptRateCommonDTO, user));
        query.append(" GROUP BY b.plant, (SUBSTR(b.wkor_date, 0, 4)||'-'||SUBSTR(b.wkor_date, 5, 2)), b.comp_no                 ");
        query.append(") a                                                                                                       ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
