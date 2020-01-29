package intf.dream.bee.finder.eqctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.eqctg.dao.BeeFinderEqctgListDAO;
import intf.dream.bee.finder.eqctg.service.BeeFinderEqctgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderEqctgListServiceTarget"
 * @spring.txbn id="beeFinderEqctgListService"
 * @spring.property name="beeFinderEqctgListDAO" ref="beeFinderEqctgListDAO"
 */
public class BeeFinderEqctgListServiceImpl implements BeeFinderEqctgListService
{
    private BeeFinderEqctgListDAO beeFinderEqctgListDAO = null;

	public BeeFinderEqctgListDAO getBeeFinderEqctgListDAO() {
		return beeFinderEqctgListDAO;
	}
	public void setBeeFinderEqctgListDAO(BeeFinderEqctgListDAO beeFinderEqctgListDAO) {
		this.beeFinderEqctgListDAO = beeFinderEqctgListDAO;
	}
	
	public List findEqctgList(Map map) throws Exception
	{      
		return beeFinderEqctgListDAO.findEqctgList(map);
	}
}

