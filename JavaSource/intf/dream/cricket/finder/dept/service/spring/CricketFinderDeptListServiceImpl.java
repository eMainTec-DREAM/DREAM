package intf.dream.cricket.finder.dept.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.dept.dao.CricketFinderDeptListDAO;
import intf.dream.cricket.finder.dept.service.CricketFinderDeptListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderDeptListServiceTarget"
 * @spring.txbn id="cricketFinderDeptListService"
 * @spring.property name="cricketFinderDeptListDAO" ref="cricketFinderDeptListDAO"
 */
public class CricketFinderDeptListServiceImpl implements CricketFinderDeptListService
{
    private CricketFinderDeptListDAO cricketFinderDeptListDAO = null;

	public CricketFinderDeptListDAO getCricketFinderDeptListDAO() {
		return cricketFinderDeptListDAO;
	}
	public void setCricketFinderDeptListDAO(CricketFinderDeptListDAO cricketFinderDeptListDAO) {
		this.cricketFinderDeptListDAO = cricketFinderDeptListDAO;
	}
	
	public List findDeptList(Map map) throws Exception
	{      
		return cricketFinderDeptListDAO.findDeptList(map);
	}
}

