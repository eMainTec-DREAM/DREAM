package intf.dream.android.online.finder.plant.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.plant.dao.AnOnFinderPlantListDAO;
import intf.dream.android.online.finder.plant.service.AnOnFinderPlantListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderPlantListServiceTarget"
 * @spring.txbn id="anOnFinderPlantListService"
 * @spring.property name="anOnFinderPlantListDAO" ref="anOnFinderPlantListDAO"
 */
public class AnOnFinderPlantListServiceImpl implements AnOnFinderPlantListService
{
    private AnOnFinderPlantListDAO anOnFinderPlantListDAO = null;

	public AnOnFinderPlantListDAO getAnOnFinderPlantListDAO() {
		return anOnFinderPlantListDAO;
	}
	public void setAnOnFinderPlantListDAO(AnOnFinderPlantListDAO anOnFinderPlantListDAO) {
		this.anOnFinderPlantListDAO = anOnFinderPlantListDAO;
	}
	
	public List findPlantList(Map map) throws Exception
	{      
		return anOnFinderPlantListDAO.findPlantList(map);
	}
}

