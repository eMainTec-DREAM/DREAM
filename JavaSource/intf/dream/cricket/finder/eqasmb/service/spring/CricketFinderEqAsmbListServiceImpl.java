package intf.dream.cricket.finder.eqasmb.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.eqasmb.dao.CricketFinderEqAsmbListDAO;
import intf.dream.cricket.finder.eqasmb.service.CricketFinderEqAsmbListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderEqAsmbListServiceTarget"
 * @spring.txbn id="cricketFinderEqAsmbListService"
 * @spring.property name="cricketFinderEqAsmbListDAO" ref="cricketFinderEqAsmbListDAO"
 */
public class CricketFinderEqAsmbListServiceImpl implements CricketFinderEqAsmbListService
{
    private CricketFinderEqAsmbListDAO cricketFinderEqAsmbListDAO = null;

	public CricketFinderEqAsmbListDAO getCricketFinderEqAsmbListDAO() {
		return cricketFinderEqAsmbListDAO;
	}
	public void setCricketFinderEqAsmbListDAO(CricketFinderEqAsmbListDAO cricketFinderEqAsmbListDAO) {
		this.cricketFinderEqAsmbListDAO = cricketFinderEqAsmbListDAO;
	}
	
	public List findEqAsmbList(Map map) throws Exception
	{      
		return cricketFinderEqAsmbListDAO.findEqAsmbList(map);
	}
}

