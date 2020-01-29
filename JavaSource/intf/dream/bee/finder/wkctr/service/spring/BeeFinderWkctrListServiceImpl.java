package intf.dream.bee.finder.wkctr.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.wkctr.dao.BeeFinderWkctrListDAO;
import intf.dream.bee.finder.wkctr.service.BeeFinderWkctrListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderWkctrListServiceTarget"
 * @spring.txbn id="beeFinderWkctrListService"
 * @spring.property name="beeFinderWkctrListDAO" ref="beeFinderWkctrListDAO"
 */
public class BeeFinderWkctrListServiceImpl implements BeeFinderWkctrListService
{
    private BeeFinderWkctrListDAO beeFinderWkctrListDAO = null;

	public BeeFinderWkctrListDAO getBeeFinderWkctrListDAO() {
		return beeFinderWkctrListDAO;
	}
	public void setBeeFinderWkctrListDAO(BeeFinderWkctrListDAO beeFinderWkctrListDAO) {
		this.beeFinderWkctrListDAO = beeFinderWkctrListDAO;
	}
	
	public List findWkctrList(Map map) throws Exception
	{      
		return beeFinderWkctrListDAO.findWkctrList(map);
	}
}

