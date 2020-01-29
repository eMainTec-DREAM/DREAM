package intf.dream.bee.finder.plant.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.plant.dao.BeeFinderPlantListDAO;
import intf.dream.bee.finder.plant.service.BeeFinderPlantListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderPlantListServiceTarget"
 * @spring.txbn id="beeFinderPlantListService"
 * @spring.property name="beeFinderPlantListDAO" ref="beeFinderPlantListDAO"
 */
public class BeeFinderPlantListServiceImpl implements BeeFinderPlantListService
{
    private BeeFinderPlantListDAO beeFinderPlantListDAO = null;

	public BeeFinderPlantListDAO getBeeFinderPlantListDAO() {
		return beeFinderPlantListDAO;
	}
	public void setBeeFinderPlantListDAO(BeeFinderPlantListDAO beeFinderPlantListDAO) {
		this.beeFinderPlantListDAO = beeFinderPlantListDAO;
	}
	
	public List findPlantList(Map map) throws Exception
	{      
		return beeFinderPlantListDAO.findPlantList(map);
	}
}

