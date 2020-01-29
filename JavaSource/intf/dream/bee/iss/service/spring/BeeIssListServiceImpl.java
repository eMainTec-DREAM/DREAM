package intf.dream.bee.iss.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.iss.dao.BeeIssListDAO;
import intf.dream.bee.iss.dto.BeeIssCommonDTO;
import intf.dream.bee.iss.service.BeeIssListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeIssListServiceTarget"
 * @spring.txbn id="beeIssListService"
 * @spring.property name="beeIssListDAO" ref="beeIssListDAO"
 */
public class BeeIssListServiceImpl implements BeeIssListService
{
    private BeeIssListDAO beeIssListDAO = null;
    
	public BeeIssListDAO getBeeIssListDAO() {
		return beeIssListDAO;
	}
	public void setBeeIssListDAO(BeeIssListDAO beeIssListDAO) {
		this.beeIssListDAO = beeIssListDAO;
	}
	
	public List findIssList(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception
	{      
		return beeIssListDAO.findIssList(beeIssCommonDTO, map);
	}
	
	public List findIssCount(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception
	{      
		return beeIssListDAO.findIssCount(beeIssCommonDTO, map);
	}
	
	public int deleteIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeIssListDAO.deleteIss(map);
		}
		return resultQty;
	}
	public int insertIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeIssListDAO.insertIss(map);
			beeIssListDAO.insertIssHdr(map);
		}
		return resultQty;
	}
	public int updateIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeIssListDAO.updateIss(map);
		}
		return resultQty;
	}
}

