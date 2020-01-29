package intf.dream.android.online.stock.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.stock.dao.AnOnStockListDAO;
import intf.dream.android.online.stock.service.AnOnStockListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnStockListServiceTarget"
 * @spring.txbn id="anOnStockListService"
 * @spring.property name="anOnStockListDAO" ref="anOnStockListDAO"
 */
public class AnOnStockListServiceImpl implements AnOnStockListService
{
    private AnOnStockListDAO anOnStockListDAO = null;
    
	public AnOnStockListDAO getAnOnStockListDAO() {
		return anOnStockListDAO;
	}
	public void setAnOnStockListDAO(AnOnStockListDAO anOnStockListDAO) {
		this.anOnStockListDAO = anOnStockListDAO;
	}
	
	public List findStockList(Map map) throws Exception
	{      
		return anOnStockListDAO.findStockList(map);
	}
	public int updateStock(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnStockListDAO.insertStockLog(map);
			anOnStockListDAO.updateStock(map);
		}
		return resultQty;
	}
}

