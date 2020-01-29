package intf.dream.cricket.finder.plant.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.plant.dao.CricketFinderPlantListDAO;
import intf.dream.cricket.finder.plant.service.CricketFinderPlantListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderPlantListServiceTarget"
 * @spring.txbn id="cricketFinderPlantListService"
 * @spring.property name="cricketFinderPlantListDAO" ref="cricketFinderPlantListDAO"
 */
public class CricketFinderPlantListServiceImpl implements CricketFinderPlantListService
{
    private CricketFinderPlantListDAO cricketFinderPlantListDAO = null;

	public CricketFinderPlantListDAO getCricketFinderPlantListDAO() {
		return cricketFinderPlantListDAO;
	}
	public void setCricketFinderPlantListDAO(CricketFinderPlantListDAO cricketFinderPlantListDAO) {
		this.cricketFinderPlantListDAO = cricketFinderPlantListDAO;
	}
	
	public List findPlantList(Map map) throws Exception
	{      
		return cricketFinderPlantListDAO.findPlantList(map);
	}
}

