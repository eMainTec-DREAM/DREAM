package intf.dream.bee.finder.dept.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.dept.dao.BeeFinderDeptListDAO;
import intf.dream.bee.finder.dept.service.BeeFinderDeptListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderDeptListServiceTarget"
 * @spring.txbn id="beeFinderDeptListService"
 * @spring.property name="beeFinderDeptListDAO" ref="beeFinderDeptListDAO"
 */
public class BeeFinderDeptListServiceImpl implements BeeFinderDeptListService
{
    private BeeFinderDeptListDAO beeFinderDeptListDAO = null;

	public BeeFinderDeptListDAO getBeeFinderDeptListDAO() {
		return beeFinderDeptListDAO;
	}
	public void setBeeFinderDeptListDAO(BeeFinderDeptListDAO beeFinderDeptListDAO) {
		this.beeFinderDeptListDAO = beeFinderDeptListDAO;
	}
	
	public List findDeptList(Map map) throws Exception
	{      
		return beeFinderDeptListDAO.findDeptList(map);
	}
}

