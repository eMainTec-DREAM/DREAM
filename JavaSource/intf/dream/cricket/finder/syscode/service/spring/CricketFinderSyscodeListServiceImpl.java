package intf.dream.cricket.finder.syscode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.syscode.dao.CricketFinderSyscodeListDAO;
import intf.dream.cricket.finder.syscode.service.CricketFinderSyscodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderSyscodeListServiceTarget"
 * @spring.txbn id="cricketFinderSyscodeListService"
 * @spring.property name="cricketFinderSyscodeListDAO" ref="cricketFinderSyscodeListDAO"
 */
public class CricketFinderSyscodeListServiceImpl implements CricketFinderSyscodeListService
{
    private CricketFinderSyscodeListDAO cricketFinderSyscodeListDAO = null;

	public CricketFinderSyscodeListDAO getCricketFinderSyscodeListDAO() {
		return cricketFinderSyscodeListDAO;
	}
	public void setCricketFinderSyscodeListDAO(CricketFinderSyscodeListDAO cricketFinderSyscodeListDAO) {
		this.cricketFinderSyscodeListDAO = cricketFinderSyscodeListDAO;
	}
	
	public List findSyscodeList(Map map) throws Exception
	{      
		return cricketFinderSyscodeListDAO.findSyscodeList(map);
	}
}

