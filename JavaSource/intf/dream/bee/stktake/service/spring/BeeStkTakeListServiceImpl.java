package intf.dream.bee.stktake.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.stktake.dao.BeeStkTakeListDAO;
import intf.dream.bee.stktake.dto.BeeStkTakeCommonDTO;
import intf.dream.bee.stktake.service.BeeStkTakeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeStkTakeListServiceTarget"
 * @spring.txbn id="beeStkTakeListService"
 * @spring.property name="beeStkTakeListDAO" ref="beeStkTakeListDAO"
 */
public class BeeStkTakeListServiceImpl implements BeeStkTakeListService
{
    private BeeStkTakeListDAO beeStkTakeListDAO = null;
    
	public BeeStkTakeListDAO getBeeStkTakeListDAO() {
		return beeStkTakeListDAO;
	}
	public void setBeeStkTakeListDAO(BeeStkTakeListDAO beeStkTakeListDAO) {
		this.beeStkTakeListDAO = beeStkTakeListDAO;
	}
	
	public List findStkTakeList(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception
	{      
		return beeStkTakeListDAO.findStkTakeList(beeStkTakeCommonDTO, map);
	}
	
	public List findStkTakeCount(BeeStkTakeCommonDTO beeStkTakeCommonDTO, Map map) throws Exception
	{      
		return beeStkTakeListDAO.findStkTakeCount(beeStkTakeCommonDTO, map);
	}
	public int deleteStkTake(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeStkTakeListDAO.deleteStkTakeItem(map);
			beeStkTakeListDAO.deleteStkTake(map);
		}
		return resultQty;
	}
	
	public List findStkTakeItemList(Map map) throws Exception
	{      
		return beeStkTakeListDAO.findStkTakeItemList(map);
	}
	public int deleteStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeStkTakeListDAO.deleteStkTakeItem(map);
		}
		return resultQty;
	}
	public int insertStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeStkTakeListDAO.insertStkTakeItem(map);
		}
		return resultQty;
	}
	public int updateStkTakeItem(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeStkTakeListDAO.updateStkTakeItem(map);
		}
		return resultQty;
	}
}

