package intf.dream.cricket.finder.eqloc.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.eqloc.dao.CricketFinderEqlocListDAO;
import intf.dream.cricket.finder.eqloc.service.CricketFinderEqlocListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderEqlocListServiceTarget"
 * @spring.txbn id="cricketFinderEqlocListService"
 * @spring.property name="cricketFinderEqlocListDAO" ref="cricketFinderEqlocListDAO"
 */
public class CricketFinderEqlocListServiceImpl implements CricketFinderEqlocListService
{
    private CricketFinderEqlocListDAO cricketFinderEqlocListDAO = null;

	public CricketFinderEqlocListDAO getCricketFinderEqlocListDAO() {
		return cricketFinderEqlocListDAO;
	}
	public void setCricketFinderEqlocListDAO(CricketFinderEqlocListDAO cricketFinderEqlocListDAO) {
		this.cricketFinderEqlocListDAO = cricketFinderEqlocListDAO;
	}
	
	public List findEqlocList(Map map) throws Exception
	{      
		return cricketFinderEqlocListDAO.findEqlocList(map);
	}
}

