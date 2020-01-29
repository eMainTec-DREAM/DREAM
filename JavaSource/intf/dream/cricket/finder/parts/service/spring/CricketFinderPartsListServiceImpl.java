package intf.dream.cricket.finder.parts.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.parts.dao.CricketFinderPartsListDAO;
import intf.dream.cricket.finder.parts.service.CricketFinderPartsListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderPartsListServiceTarget"
 * @spring.txbn id="cricketFinderPartsListService"
 * @spring.property name="cricketFinderPartsListDAO" ref="cricketFinderPartsListDAO"
 */
public class CricketFinderPartsListServiceImpl implements CricketFinderPartsListService
{
    private CricketFinderPartsListDAO cricketFinderPartsListDAO = null;

	public CricketFinderPartsListDAO getCricketFinderPartsListDAO() {
		return cricketFinderPartsListDAO;
	}
	public void setCricketFinderPartsListDAO(CricketFinderPartsListDAO cricketFinderPartsListDAO) {
		this.cricketFinderPartsListDAO = cricketFinderPartsListDAO;
	}
	
	public List findPartsList(Map map) throws Exception
	{      
		return cricketFinderPartsListDAO.findPartsList(map);
	}
}

