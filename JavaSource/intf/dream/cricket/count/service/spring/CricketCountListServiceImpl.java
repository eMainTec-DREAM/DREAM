package intf.dream.cricket.count.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.count.dao.CricketCountListDAO;
import intf.dream.cricket.count.service.CricketCountListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketCountListServiceTarget"
 * @spring.txbn id="cricketCountListService"
 * @spring.property name="cricketCountListDAO" ref="cricketCountListDAO"
 */
public class CricketCountListServiceImpl implements CricketCountListService
{
    private CricketCountListDAO cricketCountListDAO = null;

	public CricketCountListDAO getCricketCountListDAO() {
		return cricketCountListDAO;
	}
	public void setCricketCountListDAO(CricketCountListDAO cricketCountListDAO) {
		this.cricketCountListDAO = cricketCountListDAO;
	}
	
	public List findMainList(Map map) throws Exception
	{      
		return cricketCountListDAO.findMainList(map);
	}
}

