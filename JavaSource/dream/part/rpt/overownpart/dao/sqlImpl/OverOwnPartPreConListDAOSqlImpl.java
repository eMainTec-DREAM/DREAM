package dream.part.rpt.overownpart.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.rpt.overownpart.dao.OverOwnPartPreConListDAO;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;

/**
 * OverOwnPartPreCon Page - List DAO implements
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="overOwnPartPreConListDAOTarget"
 * @spring.txbn id="overOwnPartPreConListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OverOwnPartPreConListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements OverOwnPartPreConListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: overOwnPartPreConListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param overOwnPartPreConCommonDTO
     * @return List
     */
    public List findList(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                    ");
        query.append("      '' seqNo                                                ");
        query.append("    , '' isDelCheck                                           ");
        query.append("    , x.part_id                               partId          ");
        query.append("    , y.wcode_id                              wId             ");
        query.append("    , (SELECT a.wname                                         ");
        query.append("       FROM TAWAREHOUSE a                                     ");
        query.append("       WHERE a.wcode_id = y.wcode_id)         wDesc           ");
        query.append("    , x.part_no                               partCode        ");
        query.append("    , x.full_desc                             partDesc        ");
        query.append("    , ISNULL((SELECT a.stock_qty                                 ");
        query.append("           FROM TAPTSTOCK a                                   ");
        query.append("           WHERE a.part_id = y.part_id                        ");
        query.append("               AND a.wcode_id = y.wcode_id                    ");
        query.append("               AND a.part_grade =  'A' ),0)   aStockQty       ");
        query.append("    , ISNULL((SELECT a.stock_qty                                 ");
        query.append("       FROM TAPTSTOCK a                                       ");
        query.append("       WHERE a.part_id = y.part_id                            ");
        query.append("           AND a.wcode_id = y.wcode_id                        ");
        query.append("           AND a.part_grade =  'B' ),0)       bStockQty       ");
        query.append("    , ISNULL(Y.safty_qty,0)                      saftyQty        ");
        query.append("    , ( ISNULL((SELECT SUM(a.stock_qty)                          ");
        query.append("             FROM TAPTSTOCK a                                 ");
        query.append("              WHERE a.part_id = y.part_id                     ");
        query.append("                  AND a.wcode_id = y.wcode_id),0)             ");
        query.append("              -  ISNULL(Y.safty_qty,0) )         overOwnQty      ");
        query.append("    , x.last_iss_date                              lastUseDate     ");
        query.append("    , x.last_gr_date                          lastPurchaseDate");
        query.append("FROM TAPARTS x JOIN TAPTSAFTYSTOCK y                          ");
        query.append("ON x.part_id = y.part_id                                      ");
        query.append("   AND x.comp_no = y.comp_no                                  ");

        query.append("WHERE 1=1                                                     ");

        query.append(this.getWhere(overOwnPartPreConCommonDTO, user));
        query.getOrderByQuery("x.part_id","x.part_no", overOwnPartPreConCommonDTO.getOrderBy(), overOwnPartPreConCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(overOwnPartPreConCommonDTO.getIsLoadMaxCount(), overOwnPartPreConCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter ����
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param overOwnPartPreConCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("AND (ISNULL((SELECT sum(a.stock_qty)      ");
        query.append("             FROM TAPTSTOCK a     ");
        query.append("             WHERE a.part_id = y.part_id      ");
        query.append("                 AND a.wcode_id = y.wcode_id      ");
        query.append("                  ),0) ) - y.safty_qty  > 0        ");
        
        // ȸ���ȣ
        query.getAndQuery("x.comp_no", user.getCompNo());

        // ����
        if(!"".equals(overOwnPartPreConCommonDTO.getFilterStandardDate()))
        {
            query.append("AND x.last_iss_date < DATEADD(MONTH,-24,CONVERT(DATE, '"
                    + overOwnPartPreConCommonDTO.getFilterStandardDate()
                    + " ')) ");
        }
        else
        {
            query.append("AND x.last_iss_date < CONVERT(VARCHAR, DATEADD(MONTH, -24, getdate()), 112)        ");

        }
        
        // â���
        if (!"".equals(overOwnPartPreConCommonDTO.getFilterWCode()))
        {
            query.getAndQuery("y.wcode_id", overOwnPartPreConCommonDTO.getFilterWCode());
            return query.toString();
        }
        else if(!"".equals(overOwnPartPreConCommonDTO.getFilterWDesc()))
        {
            query.append("AND y.wcode_id IN (SELECT a.wcode_id      ");
            query.append("                    FROM   TAWAREHOUSE a  ");
            query.append("                    WHERE  1=1            ");
            query.getLikeQuery("a.wname", overOwnPartPreConCommonDTO.getFilterWDesc());
            query.append("                    )                     ");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(
            OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM TAPARTS x JOIN TAPTSAFTYSTOCK y      ");
        query.append("ON x.part_id = y.part_id      ");
        query.append("   AND x.comp_no = y.comp_no  ");

        query.append("WHERE 1=1                     ");
        query.append(this.getWhere(overOwnPartPreConCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
