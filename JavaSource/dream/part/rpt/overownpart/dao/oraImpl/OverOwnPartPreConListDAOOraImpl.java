package dream.part.rpt.overownpart.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.overownpart.dao.OverOwnPartPreConListDAO;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;

/**
 * OverOwnPartPreCon Page - List DAO implements
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="overOwnPartPreConListDAOTarget"
 * @spring.txbn id="overOwnPartPreConListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OverOwnPartPreConListDAOOraImpl  extends BaseJdbcDaoSupportOra implements OverOwnPartPreConListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: overOwnPartPreConListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param overOwnPartPreConCommonDTO
     * @return List
     */
    public List findList(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

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
        query.append("    , NVL((SELECT a.stock_qty                                 ");
        query.append("           FROM TAPTSTOCK a                                   ");
        query.append("           WHERE a.part_id = y.part_id                        ");
        query.append("               AND a.wcode_id = y.wcode_id                    ");
        query.append("               AND a.part_grade =  'A' ),0)   aStockQty       ");
        query.append("    , NVL((SELECT a.stock_qty                                 ");
        query.append("       FROM TAPTSTOCK a                                       ");
        query.append("       WHERE a.part_id = y.part_id                            ");
        query.append("           AND a.wcode_id = y.wcode_id                        ");
        query.append("           AND a.part_grade =  'B' ),0)       bStockQty       ");
        query.append("    , NVL(Y.safty_qty,0)                      saftyQty        ");
        query.append("    , ( NVL((SELECT SUM(a.stock_qty)                          ");
        query.append("             FROM TAPTSTOCK a                                 ");
        query.append("              WHERE a.part_id = y.part_id                     ");
        query.append("                  AND a.wcode_id = y.wcode_id),0)             ");
        query.append("              -  NVL(Y.safty_qty,0) )         overOwnQty      ");
        query.append("    , x.last_iss_date                              lastUseDate     ");
        query.append("    , x.last_gr_date                          lastPurchaseDate");
        query.append("FROM TAPARTS x JOIN TAPTSAFTYSTOCK y                          ");
        query.append("ON x.part_id = y.part_id                                      ");
        query.append("   AND x.comp_no = y.comp_no                                  ");

        query.append("WHERE 1=1                                                     ");

        query.append(this.getWhere(overOwnPartPreConCommonDTO, user));
        query.getOrderByQuery("x.part_no", overOwnPartPreConCommonDTO.getOrderBy(), overOwnPartPreConCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(overOwnPartPreConCommonDTO.getIsLoadMaxCount(), overOwnPartPreConCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("AND (NVL((SELECT sum(a.stock_qty)      ");
        query.append("             FROM TAPTSTOCK a     ");
        query.append("             WHERE a.part_id = y.part_id      ");
        query.append("                 AND a.wcode_id = y.wcode_id      ");
        query.append("                  ),0) ) - y.safty_qty  > 0        ");
        
        // 회사번호
        query.getAndQuery("x.comp_no", user.getCompNo());

        // 일자
        if(!"".equals(overOwnPartPreConCommonDTO.getFilterStandardDate()))
        {
            query.append("AND x.last_iss_date < TO_CHAR(ADD_MONTHS("
                    + "TO_DATE("
                    + overOwnPartPreConCommonDTO.getFilterStandardDate()
                    + ",'yyyymmdd')"
                    + ",-24),'yyyymmdd')        ");
        }
        else
        {
            query.append("AND x.last_iss_date < TO_CHAR(ADD_MONTHS(SYSDATE,-24),'yyyymmdd')        ");

        }
        
        // 창고명
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM TAPARTS x JOIN TAPTSAFTYSTOCK y      ");
        query.append("ON x.part_id = y.part_id      ");
        query.append("   AND x.comp_no = y.comp_no  ");

        query.append("WHERE 1=1                     ");
        query.append(this.getWhere(overOwnPartPreConCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
