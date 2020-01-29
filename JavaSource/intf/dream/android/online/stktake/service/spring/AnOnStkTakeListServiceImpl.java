package intf.dream.android.online.stktake.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.stktake.dao.AnOnStkTakeListDAO;
import intf.dream.android.online.stktake.service.AnOnStkTakeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnStkTakeListServiceTarget"
 * @spring.txbn id="anOnStkTakeListService"
 * @spring.property name="anOnStkTakeListDAO" ref="anOnStkTakeListDAO"
 */
public class AnOnStkTakeListServiceImpl implements AnOnStkTakeListService
{
    private AnOnStkTakeListDAO anOnStkTakeListDAO = null;
    
	public AnOnStkTakeListDAO getAnOnStkTakeListDAO() {
		return anOnStkTakeListDAO;
	}
	public void setAnOnStkTakeListDAO(AnOnStkTakeListDAO anOnStkTakeListDAO) {
		this.anOnStkTakeListDAO = anOnStkTakeListDAO;
	}
	
	public List findStkTakeList(Map map) throws Exception
	{      
		return anOnStkTakeListDAO.findStkTakeList(map);
	}
	public int deleteStkTake(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnStkTakeListDAO.deleteStkTakeItem(map);
			anOnStkTakeListDAO.deleteStkTake(map);
		}
		return resultQty;
	}
	
	public List findStkTakeItemList(Map map) throws Exception
	{      
		return anOnStkTakeListDAO.findStkTakeItemList(map);
	}
	public int deleteStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnStkTakeListDAO.deleteStkTakeItem(map);
		}
		return resultQty;
	}
	public int insertStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnStkTakeListDAO.insertStkTakeItem(map);
		}
		return resultQty;
	}
	public int updateStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnStkTakeListDAO.updateStkTakeItem(map);
		}
		return resultQty;
	}
}

