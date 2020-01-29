package intf.dream.bee.finder.docctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.docctg.dao.BeeFinderDocCtgListDAO;
import intf.dream.bee.finder.docctg.service.BeeFinderDocCtgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderDocCtgListServiceTarget"
 * @spring.txbn id="beeFinderDocCtgListService"
 * @spring.property name="beeFinderDocCtgListDAO" ref="beeFinderDocCtgListDAO"
 */
public class BeeFinderDocCtgListServiceImpl implements BeeFinderDocCtgListService
{
    private BeeFinderDocCtgListDAO beeFinderDocCtgListDAO = null;

	public BeeFinderDocCtgListDAO getBeeFinderDocCtgListDAO() {
		return beeFinderDocCtgListDAO;
	}
	public void setBeeFinderDocCtgListDAO(BeeFinderDocCtgListDAO beeFinderDocCtgListDAO) {
		this.beeFinderDocCtgListDAO = beeFinderDocCtgListDAO;
	}
	
	public List findDocCtgList(Map map) throws Exception
	{      
		return beeFinderDocCtgListDAO.findDocCtgList(map);
	}
}

