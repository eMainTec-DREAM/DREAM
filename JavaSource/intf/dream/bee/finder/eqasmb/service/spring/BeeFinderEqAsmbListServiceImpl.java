package intf.dream.bee.finder.eqasmb.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.eqasmb.dao.BeeFinderEqAsmbListDAO;
import intf.dream.bee.finder.eqasmb.service.BeeFinderEqAsmbListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderEqAsmbListServiceTarget"
 * @spring.txbn id="beeFinderEqAsmbListService"
 * @spring.property name="beeFinderEqAsmbListDAO" ref="beeFinderEqAsmbListDAO"
 */
public class BeeFinderEqAsmbListServiceImpl implements BeeFinderEqAsmbListService
{
    private BeeFinderEqAsmbListDAO beeFinderEqAsmbListDAO = null;

	public BeeFinderEqAsmbListDAO getBeeFinderEqAsmbListDAO() {
		return beeFinderEqAsmbListDAO;
	}
	public void setBeeFinderEqAsmbListDAO(BeeFinderEqAsmbListDAO beeFinderEqAsmbListDAO) {
		this.beeFinderEqAsmbListDAO = beeFinderEqAsmbListDAO;
	}
	
	public List findEqAsmbList(Map map) throws Exception
	{      
		return beeFinderEqAsmbListDAO.findEqAsmbList(map);
	}
}

