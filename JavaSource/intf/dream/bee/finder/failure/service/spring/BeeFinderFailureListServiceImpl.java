package intf.dream.bee.finder.failure.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.failure.dao.BeeFinderFailureListDAO;
import intf.dream.bee.finder.failure.service.BeeFinderFailureListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderFailureListServiceTarget"
 * @spring.txbn id="beeFinderFailureListService"
 * @spring.property name="beeFinderFailureListDAO" ref="beeFinderFailureListDAO"
 */
public class BeeFinderFailureListServiceImpl implements BeeFinderFailureListService
{
    private BeeFinderFailureListDAO beeFinderFailureListDAO = null;

	public BeeFinderFailureListDAO getBeeFinderFailureListDAO() {
		return beeFinderFailureListDAO;
	}
	public void setBeeFinderFailureListDAO(BeeFinderFailureListDAO beeFinderFailureListDAO) {
		this.beeFinderFailureListDAO = beeFinderFailureListDAO;
	}
	
	public List findFailureList(Map map) throws Exception
	{      
		return beeFinderFailureListDAO.findFailureList(map);
	}
}

