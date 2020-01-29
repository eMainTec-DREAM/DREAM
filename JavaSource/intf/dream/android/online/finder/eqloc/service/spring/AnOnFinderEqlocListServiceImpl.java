package intf.dream.android.online.finder.eqloc.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.eqloc.dao.AnOnFinderEqlocListDAO;
import intf.dream.android.online.finder.eqloc.service.AnOnFinderEqlocListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderEqlocListServiceTarget"
 * @spring.txbn id="anOnFinderEqlocListService"
 * @spring.property name="anOnFinderEqlocListDAO" ref="anOnFinderEqlocListDAO"
 */
public class AnOnFinderEqlocListServiceImpl implements AnOnFinderEqlocListService
{
    private AnOnFinderEqlocListDAO anOnFinderEqlocListDAO = null;

	public AnOnFinderEqlocListDAO getAnOnFinderEqlocListDAO() {
		return anOnFinderEqlocListDAO;
	}
	public void setAnOnFinderEqlocListDAO(AnOnFinderEqlocListDAO anOnFinderEqlocListDAO) {
		this.anOnFinderEqlocListDAO = anOnFinderEqlocListDAO;
	}
	
	public List findEqlocList(Map map) throws Exception
	{      
		return anOnFinderEqlocListDAO.findEqlocList(map);
	}
}

