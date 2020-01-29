package intf.dream.android.online.finder.wh.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.wh.dao.AnOnFinderWhListDAO;
import intf.dream.android.online.finder.wh.service.AnOnFinderWhListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderWhListServiceTarget"
 * @spring.txbn id="anOnFinderWhListService"
 * @spring.property name="anOnFinderWhListDAO" ref="anOnFinderWhListDAO"
 */
public class AnOnFinderWhListServiceImpl implements AnOnFinderWhListService
{
    private AnOnFinderWhListDAO anOnFinderWhListDAO = null;

	public AnOnFinderWhListDAO getAnOnFinderWhListDAO() {
		return anOnFinderWhListDAO;
	}
	public void setAnOnFinderWhListDAO(AnOnFinderWhListDAO anOnFinderWhListDAO) {
		this.anOnFinderWhListDAO = anOnFinderWhListDAO;
	}
	
	public List findWhList(Map map) throws Exception
	{      
		return anOnFinderWhListDAO.findWhList(map);
	}
}

