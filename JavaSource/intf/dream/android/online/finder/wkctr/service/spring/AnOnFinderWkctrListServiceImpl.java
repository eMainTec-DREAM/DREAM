package intf.dream.android.online.finder.wkctr.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.wkctr.dao.AnOnFinderWkctrListDAO;
import intf.dream.android.online.finder.wkctr.service.AnOnFinderWkctrListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderWkctrListServiceTarget"
 * @spring.txbn id="anOnFinderWkctrListService"
 * @spring.property name="anOnFinderWkctrListDAO" ref="anOnFinderWkctrListDAO"
 */
public class AnOnFinderWkctrListServiceImpl implements AnOnFinderWkctrListService
{
    private AnOnFinderWkctrListDAO anOnFinderWkctrListDAO = null;

	public AnOnFinderWkctrListDAO getAnOnFinderWkctrListDAO() {
		return anOnFinderWkctrListDAO;
	}
	public void setAnOnFinderWkctrListDAO(AnOnFinderWkctrListDAO anOnFinderWkctrListDAO) {
		this.anOnFinderWkctrListDAO = anOnFinderWkctrListDAO;
	}
	
	public List findWkctrList(Map map) throws Exception
	{      
		return anOnFinderWkctrListDAO.findWkctrList(map);
	}
}

