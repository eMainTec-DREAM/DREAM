package intf.dream.bee.finder.wh.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.wh.dao.BeeFinderWhListDAO;
import intf.dream.bee.finder.wh.service.BeeFinderWhListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderWhListServiceTarget"
 * @spring.txbn id="beeFinderWhListService"
 * @spring.property name="beeFinderWhListDAO" ref="beeFinderWhListDAO"
 */
public class BeeFinderWhListServiceImpl implements BeeFinderWhListService
{
    private BeeFinderWhListDAO beeFinderWhListDAO = null;

	public BeeFinderWhListDAO getBeeFinderWhListDAO() {
		return beeFinderWhListDAO;
	}
	public void setBeeFinderWhListDAO(BeeFinderWhListDAO beeFinderWhListDAO) {
		this.beeFinderWhListDAO = beeFinderWhListDAO;
	}
	
	public List findWhList(Map map) throws Exception
	{      
		return beeFinderWhListDAO.findWhList(map);
	}
}

