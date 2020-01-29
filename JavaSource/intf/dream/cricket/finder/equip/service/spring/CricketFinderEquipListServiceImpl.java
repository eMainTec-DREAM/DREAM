package intf.dream.cricket.finder.equip.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.equip.dao.CricketFinderEquipListDAO;
import intf.dream.cricket.finder.equip.service.CricketFinderEquipListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderEquipListServiceTarget"
 * @spring.txbn id="cricketFinderEquipListService"
 * @spring.property name="cricketFinderEquipListDAO" ref="cricketFinderEquipListDAO"
 */
public class CricketFinderEquipListServiceImpl implements CricketFinderEquipListService
{
    private CricketFinderEquipListDAO cricketFinderEquipListDAO = null;

	public CricketFinderEquipListDAO getCricketFinderEquipListDAO() {
		return cricketFinderEquipListDAO;
	}
	public void setCricketFinderEquipListDAO(CricketFinderEquipListDAO cricketFinderEquipListDAO) {
		this.cricketFinderEquipListDAO = cricketFinderEquipListDAO;
	}
	
	public List findEquipList(Map map) throws Exception
	{      
		return cricketFinderEquipListDAO.findEquipList(map);
	}
}

