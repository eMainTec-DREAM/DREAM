package dream.part.rpt.stockkpi.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.part.rpt.stockkpi.dao.StockKpiListDAO;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;

/**
 * 재고지표 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="stockKpiListDAOTarget"
 * @spring.txbn id="stockKpiListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class StockKpiListDAOOraImpl extends BaseJdbcDaoSupportOra implements StockKpiListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param stockKpiCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(StockKpiCommonDTO stockKpiCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("      x.wcode_id                                                          wcodeId     ");
        query.append("    , (SELECT a.wname FROM TAWAREHOUSE a WHERE a.wcode_id = x.wcode_id)   wname       ");
        query.append("    , x.yyyymm                                                            yyyymm      ");
        query.append("    , NVL((SELECT a.stock_plan_amt FROM TAPTMSTOCKPLAN a WHERE a.wcode_id = x.wcode_id AND a.yyyymm = x.yyyymm),0) goalAmt        ");
        query.append("    , SUM(NVL(x.result_tot_price,0))                                      stockAmt    ");
        query.append("    , SUM(NVL(x.issue_tot_price,0))                                       useAmt      ");
        query.append("FROM TAPTMONTHLYSTOCK x                                                               ");
        query.append("WHERE 1=1                                                                             ");
        query.append(this.getWhere(stockKpiCommonDTO,loginUser));
        query.append("GROUP BY x.wcode_id, x.yyyymm     ");
        query.getOrderByQuery("x.yyyymm", stockKpiCommonDTO.getOrderBy(), stockKpiCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(stockKpiCommonDTO.getIsLoadMaxCount(), stockKpiCommonDTO.getFirstRow()));
    }
    
    public String getWhere(StockKpiCommonDTO stockKpiCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        // 회사번호
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        
        String fromYyyymm = CommonUtil.dateToData(stockKpiCommonDTO.getFilterStartYyyymm());
        String toYyyymm   = CommonUtil.dateToData(stockKpiCommonDTO.getFilterEndYyyymm());
        
        // 년월
        query.getAndDateQuery("x.yyyymm", fromYyyymm, toYyyymm);
        
        // 창고
        if(!"".equals(stockKpiCommonDTO.getFilterWCodeId()))
        {
            query.getAndQuery("x.wcode_id", stockKpiCommonDTO.getFilterWCodeId());
        }
        else if(!"".equals(stockKpiCommonDTO.getFilterWName()))
        {
            query.append("AND x.wcode_id IN (SELECT a.wcode_id      ");
            query.append("                   FROM TAWAREHOUSE a     ");
            query.append("                   WHERE 1=1              ");
            query.getLikeQuery("a.wname", stockKpiCommonDTO.getFilterWName());
            query.append("                   )                      ");
        }
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(StockKpiCommonDTO stockKpiCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("       COUNT(1)                   ");
        query.append("FROM                              ");
        query.append("  ( SELECT wcode_id               ");
        query.append("    FROM TAPTMONTHLYSTOCK x       ");
        query.append("    WHERE 1=1                     ");
        query.append(this.getWhere(stockKpiCommonDTO,loginUser));
        query.append("    GROUP BY x.wcode_id, x.yyyymm ");
        query.append("  )                               ");

        
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}