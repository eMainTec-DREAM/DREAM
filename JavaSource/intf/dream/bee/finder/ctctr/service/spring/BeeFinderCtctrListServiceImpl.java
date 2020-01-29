package intf.dream.bee.finder.ctctr.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.ctctr.dao.BeeFinderCtctrListDAO;
import intf.dream.bee.finder.ctctr.service.BeeFinderCtctrListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderCtctrListServiceTarget"
 * @spring.txbn id="beeFinderCtctrListService"
 * @spring.property name="beeFinderCtctrListDAO" ref="beeFinderCtctrListDAO"
 */
public class BeeFinderCtctrListServiceImpl implements BeeFinderCtctrListService
{
    private BeeFinderCtctrListDAO beeFinderCtctrListDAO = null;

	public BeeFinderCtctrListDAO getBeeFinderCtctrListDAO() {
		return beeFinderCtctrListDAO;
	}
	public void setBeeFinderCtctrListDAO(BeeFinderCtctrListDAO beeFinderCtctrListDAO) {
		this.beeFinderCtctrListDAO = beeFinderCtctrListDAO;
	}
	
	public List findCtctrList(Map map) throws Exception
	{      
		return beeFinderCtctrListDAO.findCtctrList(map);
	}
}

