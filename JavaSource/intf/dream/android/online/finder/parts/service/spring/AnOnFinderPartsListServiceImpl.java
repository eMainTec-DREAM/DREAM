package intf.dream.android.online.finder.parts.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.parts.dao.AnOnFinderPartsListDAO;
import intf.dream.android.online.finder.parts.service.AnOnFinderPartsListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderPartsListServiceTarget"
 * @spring.txbn id="anOnFinderPartsListService"
 * @spring.property name="anOnFinderPartsListDAO" ref="anOnFinderPartsListDAO"
 */
public class AnOnFinderPartsListServiceImpl implements AnOnFinderPartsListService
{
    private AnOnFinderPartsListDAO anOnFinderPartsListDAO = null;

	public AnOnFinderPartsListDAO getAnOnFinderPartsListDAO() {
		return anOnFinderPartsListDAO;
	}
	public void setAnOnFinderPartsListDAO(AnOnFinderPartsListDAO anOnFinderPartsListDAO) {
		this.anOnFinderPartsListDAO = anOnFinderPartsListDAO;
	}
	
	public List findPartsList(Map map) throws Exception
	{      
		return anOnFinderPartsListDAO.findPartsList(map);
	}
}

