package intf.dream.bee.stock.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.stock.dao.BeeStockListDAO;
import intf.dream.bee.stock.dto.BeeStockCommonDTO;
import intf.dream.bee.stock.service.BeeStockListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeStockListServiceTarget"
 * @spring.txbn id="beeStockListService"
 * @spring.property name="beeStockListDAO" ref="beeStockListDAO"
 */
public class BeeStockListServiceImpl implements BeeStockListService
{
    private BeeStockListDAO beeStockListDAO = null;
    
	public BeeStockListDAO getBeeStockListDAO() {
		return beeStockListDAO;
	}
	public void setBeeStockListDAO(BeeStockListDAO beeStockListDAO) {
		this.beeStockListDAO = beeStockListDAO;
	}
	
	public List findStockList(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception
	{      
		return beeStockListDAO.findStockList(beeStockCommonDTO, map);
	}
	
	public List findStockCount(BeeStockCommonDTO beeStockCommonDTO, Map map) throws Exception
	{      
		return beeStockListDAO.findStockCount(beeStockCommonDTO, map);
	}
	public int updateStock(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeStockListDAO.insertStockLog(map);
			beeStockListDAO.updateStock(map);
		}
		return resultQty;
	}
}

