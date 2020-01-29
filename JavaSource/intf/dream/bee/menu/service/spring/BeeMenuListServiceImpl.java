package intf.dream.bee.menu.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.menu.dao.BeeMenuListDAO;
import intf.dream.bee.menu.service.BeeMenuListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeMenuListServiceTarget"
 * @spring.txbn id="beeMenuListService"
 * @spring.property name="beeMenuListDAO" ref="beeMenuListDAO"
 */
public class BeeMenuListServiceImpl implements BeeMenuListService
{
    private BeeMenuListDAO beeMenuListDAO = null;
    
	public BeeMenuListDAO getBeeMenuListDAO() {
		return beeMenuListDAO;
	}
	public void setBeeMenuListDAO(BeeMenuListDAO beeMenuListDAO) {
		this.beeMenuListDAO = beeMenuListDAO;
	}
	
	public List findMenuList(Map map) throws Exception
	{      
		return beeMenuListDAO.findMenuList(map);
	}
}

