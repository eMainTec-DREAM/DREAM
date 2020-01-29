package dream.part.rpt.stockkpi.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.stockkpi.dao.StockKpiListDAO;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;
import dream.part.rpt.stockkpi.service.StockKpiListService;

/**
 * 재고지표 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="stockKpiListServiceTarget"
 * @spring.txbn id="stockKpiListService"
 * @spring.property name="stockKpiListDAO" ref="stockKpiListDAO"
 */
public class StockKpiListServiceImpl implements StockKpiListService
{
    private StockKpiListDAO stockKpiListDAO = null;
    
	public StockKpiListDAO getStockKpiListDAO()
    {
        return stockKpiListDAO;
    }
	
    public void setStockKpiListDAO(
            StockKpiListDAO stockKpiListDAO)
    {
        this.stockKpiListDAO = stockKpiListDAO;
    }
    
    public List findList(StockKpiCommonDTO stockKpiCommonDTO, User loginUser)
    {
        return stockKpiListDAO.findList(stockKpiCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(StockKpiCommonDTO stockKpiCommonDTO, User user)
    {
        return stockKpiListDAO.findTotalCount(stockKpiCommonDTO, user);
    }
	
}

