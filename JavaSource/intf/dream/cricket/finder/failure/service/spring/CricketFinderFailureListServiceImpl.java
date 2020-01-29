package intf.dream.cricket.finder.failure.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.failure.dao.CricketFinderFailureListDAO;
import intf.dream.cricket.finder.failure.service.CricketFinderFailureListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderFailureListServiceTarget"
 * @spring.txbn id="cricketFinderFailureListService"
 * @spring.property name="cricketFinderFailureListDAO" ref="cricketFinderFailureListDAO"
 */
public class CricketFinderFailureListServiceImpl implements CricketFinderFailureListService
{
    private CricketFinderFailureListDAO cricketFinderFailureListDAO = null;

	public CricketFinderFailureListDAO getCricketFinderFailureListDAO() {
		return cricketFinderFailureListDAO;
	}
	public void setCricketFinderFailureListDAO(CricketFinderFailureListDAO cricketFinderFailureListDAO) {
		this.cricketFinderFailureListDAO = cricketFinderFailureListDAO;
	}
	
	public List findFailureList(Map map) throws Exception
	{      
		return cricketFinderFailureListDAO.findFailureList(map);
	}
}

