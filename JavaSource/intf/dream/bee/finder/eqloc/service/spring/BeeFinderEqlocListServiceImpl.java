package intf.dream.bee.finder.eqloc.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.eqloc.dao.BeeFinderEqlocListDAO;
import intf.dream.bee.finder.eqloc.service.BeeFinderEqlocListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderEqlocListServiceTarget"
 * @spring.txbn id="beeFinderEqlocListService"
 * @spring.property name="beeFinderEqlocListDAO" ref="beeFinderEqlocListDAO"
 */
public class BeeFinderEqlocListServiceImpl implements BeeFinderEqlocListService
{
    private BeeFinderEqlocListDAO beeFinderEqlocListDAO = null;

	public BeeFinderEqlocListDAO getBeeFinderEqlocListDAO() {
		return beeFinderEqlocListDAO;
	}
	public void setBeeFinderEqlocListDAO(BeeFinderEqlocListDAO beeFinderEqlocListDAO) {
		this.beeFinderEqlocListDAO = beeFinderEqlocListDAO;
	}
	
	public List findEqlocList(Map map) throws Exception
	{      
		return beeFinderEqlocListDAO.findEqlocList(map);
	}
}

