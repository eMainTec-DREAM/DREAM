package intf.dream.cricket.menu.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.menu.dao.CricketMenuListDAO;
import intf.dream.cricket.menu.service.CricketMenuListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketMenuListServiceTarget"
 * @spring.txbn id="cricketMenuListService"
 * @spring.property name="cricketMenuListDAO" ref="cricketMenuListDAO"
 */
public class CricketMenuListServiceImpl implements CricketMenuListService
{
    private CricketMenuListDAO cricketMenuListDAO = null;
    
	public CricketMenuListDAO getCricketMenuListDAO() {
		return cricketMenuListDAO;
	}
	public void setCricketMenuListDAO(CricketMenuListDAO cricketMenuListDAO) {
		this.cricketMenuListDAO = cricketMenuListDAO;
	}
	
	public List findMenuList(Map map) throws Exception
	{      
		return cricketMenuListDAO.findMenuList(map);
	}
}

