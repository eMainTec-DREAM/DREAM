package intf.dream.android.online.finder.equip.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.equip.dao.AnOnFinderEquipListDAO;
import intf.dream.android.online.finder.equip.service.AnOnFinderEquipListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderEquipListServiceTarget"
 * @spring.txbn id="anOnFinderEquipListService"
 * @spring.property name="anOnFinderEquipListDAO" ref="anOnFinderEquipListDAO"
 */
public class AnOnFinderEquipListServiceImpl implements AnOnFinderEquipListService
{
    private AnOnFinderEquipListDAO anOnFinderEquipListDAO = null;

	public AnOnFinderEquipListDAO getAnOnFinderEquipListDAO() {
		return anOnFinderEquipListDAO;
	}
	public void setAnOnFinderEquipListDAO(AnOnFinderEquipListDAO anOnFinderEquipListDAO) {
		this.anOnFinderEquipListDAO = anOnFinderEquipListDAO;
	}
	
	public List findEquipList(Map map) throws Exception
	{      
		return anOnFinderEquipListDAO.findEquipList(map);
	}
}

