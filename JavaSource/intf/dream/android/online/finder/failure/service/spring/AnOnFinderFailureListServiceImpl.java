package intf.dream.android.online.finder.failure.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.failure.dao.AnOnFinderFailureListDAO;
import intf.dream.android.online.finder.failure.service.AnOnFinderFailureListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderFailureListServiceTarget"
 * @spring.txbn id="anOnFinderFailureListService"
 * @spring.property name="anOnFinderFailureListDAO" ref="anOnFinderFailureListDAO"
 */
public class AnOnFinderFailureListServiceImpl implements AnOnFinderFailureListService
{
    private AnOnFinderFailureListDAO anOnFinderFailureListDAO = null;

	public AnOnFinderFailureListDAO getAnOnFinderFailureListDAO() {
		return anOnFinderFailureListDAO;
	}
	public void setAnOnFinderFailureListDAO(AnOnFinderFailureListDAO anOnFinderFailureListDAO) {
		this.anOnFinderFailureListDAO = anOnFinderFailureListDAO;
	}
	
	public List findFailureList(Map map) throws Exception
	{      
		return anOnFinderFailureListDAO.findFailureList(map);
	}
}

