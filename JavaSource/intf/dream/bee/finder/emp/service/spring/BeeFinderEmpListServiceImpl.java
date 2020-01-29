package intf.dream.bee.finder.emp.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.emp.dao.BeeFinderEmpListDAO;
import intf.dream.bee.finder.emp.service.BeeFinderEmpListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderEmpListServiceTarget"
 * @spring.txbn id="beeFinderEmpListService"
 * @spring.property name="beeFinderEmpListDAO" ref="beeFinderEmpListDAO"
 */
public class BeeFinderEmpListServiceImpl implements BeeFinderEmpListService
{
    private BeeFinderEmpListDAO beeFinderEmpListDAO = null;

	public BeeFinderEmpListDAO getBeeFinderEmpListDAO() {
		return beeFinderEmpListDAO;
	}
	public void setBeeFinderEmpListDAO(BeeFinderEmpListDAO beeFinderEmpListDAO) {
		this.beeFinderEmpListDAO = beeFinderEmpListDAO;
	}
	
	public List findEmpList(Map map) throws Exception
	{      
		return beeFinderEmpListDAO.findEmpList(map);
	}
}

