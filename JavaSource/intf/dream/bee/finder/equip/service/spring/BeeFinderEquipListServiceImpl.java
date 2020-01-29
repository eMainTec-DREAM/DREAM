package intf.dream.bee.finder.equip.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.equip.dao.BeeFinderEquipListDAO;
import intf.dream.bee.finder.equip.service.BeeFinderEquipListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderEquipListServiceTarget"
 * @spring.txbn id="beeFinderEquipListService"
 * @spring.property name="beeFinderEquipListDAO" ref="beeFinderEquipListDAO"
 */
public class BeeFinderEquipListServiceImpl implements BeeFinderEquipListService
{
    private BeeFinderEquipListDAO beeFinderEquipListDAO = null;

	public BeeFinderEquipListDAO getBeeFinderEquipListDAO() {
		return beeFinderEquipListDAO;
	}
	public void setBeeFinderEquipListDAO(BeeFinderEquipListDAO beeFinderEquipListDAO) {
		this.beeFinderEquipListDAO = beeFinderEquipListDAO;
	}
	
	public List findEquipList(Map map) throws Exception
	{      
		return beeFinderEquipListDAO.findEquipList(map);
	}
}

