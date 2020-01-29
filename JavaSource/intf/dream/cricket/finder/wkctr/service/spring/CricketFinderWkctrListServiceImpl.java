package intf.dream.cricket.finder.wkctr.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.wkctr.dao.CricketFinderWkctrListDAO;
import intf.dream.cricket.finder.wkctr.service.CricketFinderWkctrListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderWkctrListServiceTarget"
 * @spring.txbn id="cricketFinderWkctrListService"
 * @spring.property name="cricketFinderWkctrListDAO" ref="cricketFinderWkctrListDAO"
 */
public class CricketFinderWkctrListServiceImpl implements CricketFinderWkctrListService
{
    private CricketFinderWkctrListDAO cricketFinderWkctrListDAO = null;

	public CricketFinderWkctrListDAO getCricketFinderWkctrListDAO() {
		return cricketFinderWkctrListDAO;
	}
	public void setCricketFinderWkctrListDAO(CricketFinderWkctrListDAO cricketFinderWkctrListDAO) {
		this.cricketFinderWkctrListDAO = cricketFinderWkctrListDAO;
	}
	
	public List findWkctrList(Map map) throws Exception
	{      
		return cricketFinderWkctrListDAO.findWkctrList(map);
	}
}

