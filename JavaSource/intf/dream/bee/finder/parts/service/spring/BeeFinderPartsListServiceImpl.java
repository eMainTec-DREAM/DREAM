package intf.dream.bee.finder.parts.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.parts.dao.BeeFinderPartsListDAO;
import intf.dream.bee.finder.parts.service.BeeFinderPartsListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderPartsListServiceTarget"
 * @spring.txbn id="beeFinderPartsListService"
 * @spring.property name="beeFinderPartsListDAO" ref="beeFinderPartsListDAO"
 */
public class BeeFinderPartsListServiceImpl implements BeeFinderPartsListService
{
    private BeeFinderPartsListDAO beeFinderPartsListDAO = null;

	public BeeFinderPartsListDAO getBeeFinderPartsListDAO() {
		return beeFinderPartsListDAO;
	}
	public void setBeeFinderPartsListDAO(BeeFinderPartsListDAO beeFinderPartsListDAO) {
		this.beeFinderPartsListDAO = beeFinderPartsListDAO;
	}
	
	public List findPartsList(Map map) throws Exception
	{      
		return beeFinderPartsListDAO.findPartsList(map);
	}
}

