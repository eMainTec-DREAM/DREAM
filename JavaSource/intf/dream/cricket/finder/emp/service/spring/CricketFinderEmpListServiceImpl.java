package intf.dream.cricket.finder.emp.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.emp.dao.CricketFinderEmpListDAO;
import intf.dream.cricket.finder.emp.service.CricketFinderEmpListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderEmpListServiceTarget"
 * @spring.txbn id="cricketFinderEmpListService"
 * @spring.property name="cricketFinderEmpListDAO" ref="cricketFinderEmpListDAO"
 */
public class CricketFinderEmpListServiceImpl implements CricketFinderEmpListService
{
    private CricketFinderEmpListDAO cricketFinderEmpListDAO = null;

	public CricketFinderEmpListDAO getCricketFinderEmpListDAO() {
		return cricketFinderEmpListDAO;
	}
	public void setCricketFinderEmpListDAO(CricketFinderEmpListDAO cricketFinderEmpListDAO) {
		this.cricketFinderEmpListDAO = cricketFinderEmpListDAO;
	}
	
	public List findEmpList(Map map) throws Exception
	{      
		return cricketFinderEmpListDAO.findEmpList(map);
	}
}

