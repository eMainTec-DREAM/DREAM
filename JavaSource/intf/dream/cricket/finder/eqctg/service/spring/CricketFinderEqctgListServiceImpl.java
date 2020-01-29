package intf.dream.cricket.finder.eqctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.eqctg.dao.CricketFinderEqctgListDAO;
import intf.dream.cricket.finder.eqctg.service.CricketFinderEqctgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderEqctgListServiceTarget"
 * @spring.txbn id="cricketFinderEqctgListService"
 * @spring.property name="cricketFinderEqctgListDAO" ref="cricketFinderEqctgListDAO"
 */
public class CricketFinderEqctgListServiceImpl implements CricketFinderEqctgListService
{
    private CricketFinderEqctgListDAO cricketFinderEqctgListDAO = null;

	public CricketFinderEqctgListDAO getCricketFinderEqctgListDAO() {
		return cricketFinderEqctgListDAO;
	}
	public void setCricketFinderEqctgListDAO(CricketFinderEqctgListDAO cricketFinderEqctgListDAO) {
		this.cricketFinderEqctgListDAO = cricketFinderEqctgListDAO;
	}
	
	public List findEqctgList(Map map) throws Exception
	{      
		return cricketFinderEqctgListDAO.findEqctgList(map);
	}
}

