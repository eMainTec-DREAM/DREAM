package intf.dream.cricket.finder.wh.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.wh.dao.CricketFinderWhListDAO;
import intf.dream.cricket.finder.wh.service.CricketFinderWhListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderWhListServiceTarget"
 * @spring.txbn id="cricketFinderWhListService"
 * @spring.property name="cricketFinderWhListDAO" ref="cricketFinderWhListDAO"
 */
public class CricketFinderWhListServiceImpl implements CricketFinderWhListService
{
    private CricketFinderWhListDAO cricketFinderWhListDAO = null;

	public CricketFinderWhListDAO getCricketFinderWhListDAO() {
		return cricketFinderWhListDAO;
	}
	public void setCricketFinderWhListDAO(CricketFinderWhListDAO cricketFinderWhListDAO) {
		this.cricketFinderWhListDAO = cricketFinderWhListDAO;
	}
	
	public List findWhList(Map map) throws Exception
	{      
		return cricketFinderWhListDAO.findWhList(map);
	}
}

