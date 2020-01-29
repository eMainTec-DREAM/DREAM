package intf.dream.cricket.finder.docctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.docctg.dao.CricketFinderDocCtgListDAO;
import intf.dream.cricket.finder.docctg.service.CricketFinderDocCtgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderDocCtgListServiceTarget"
 * @spring.txbn id="cricketFinderDocCtgListService"
 * @spring.property name="cricketFinderDocCtgListDAO" ref="cricketFinderDocCtgListDAO"
 */
public class CricketFinderDocCtgListServiceImpl implements CricketFinderDocCtgListService
{
    private CricketFinderDocCtgListDAO cricketFinderDocCtgListDAO = null;

	public CricketFinderDocCtgListDAO getCricketFinderDocCtgListDAO() {
		return cricketFinderDocCtgListDAO;
	}
	public void setCricketFinderDocCtgListDAO(CricketFinderDocCtgListDAO cricketFinderDocCtgListDAO) {
		this.cricketFinderDocCtgListDAO = cricketFinderDocCtgListDAO;
	}
	
	public List findDocCtgList(Map map) throws Exception
	{      
		return cricketFinderDocCtgListDAO.findDocCtgList(map);
	}
}

