package intf.dream.bee.finder.syscode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.syscode.dao.BeeFinderSyscodeListDAO;
import intf.dream.bee.finder.syscode.service.BeeFinderSyscodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderSyscodeListServiceTarget"
 * @spring.txbn id="beeFinderSyscodeListService"
 * @spring.property name="beeFinderSyscodeListDAO" ref="beeFinderSyscodeListDAO"
 */
public class BeeFinderSyscodeListServiceImpl implements BeeFinderSyscodeListService
{
    private BeeFinderSyscodeListDAO beeFinderSyscodeListDAO = null;

	public BeeFinderSyscodeListDAO getBeeFinderSyscodeListDAO() {
		return beeFinderSyscodeListDAO;
	}
	public void setBeeFinderSyscodeListDAO(BeeFinderSyscodeListDAO beeFinderSyscodeListDAO) {
		this.beeFinderSyscodeListDAO = beeFinderSyscodeListDAO;
	}
	
	public List findSyscodeList(Map map) throws Exception
	{      
		return beeFinderSyscodeListDAO.findSyscodeList(map);
	}
}

