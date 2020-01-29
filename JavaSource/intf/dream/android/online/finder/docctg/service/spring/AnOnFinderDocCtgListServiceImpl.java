package intf.dream.android.online.finder.docctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.docctg.dao.AnOnFinderDocCtgListDAO;
import intf.dream.android.online.finder.docctg.service.AnOnFinderDocCtgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderDocCtgListServiceTarget"
 * @spring.txbn id="anOnFinderDocCtgListService"
 * @spring.property name="anOnFinderDocCtgListDAO" ref="anOnFinderDocCtgListDAO"
 */
public class AnOnFinderDocCtgListServiceImpl implements AnOnFinderDocCtgListService
{
    private AnOnFinderDocCtgListDAO anOnFinderDocCtgListDAO = null;

	public AnOnFinderDocCtgListDAO getAnOnFinderDocCtgListDAO() {
		return anOnFinderDocCtgListDAO;
	}
	public void setAnOnFinderDocCtgListDAO(AnOnFinderDocCtgListDAO anOnFinderDocCtgListDAO) {
		this.anOnFinderDocCtgListDAO = anOnFinderDocCtgListDAO;
	}
	
	public List findDocCtgList(Map map) throws Exception
	{      
		return anOnFinderDocCtgListDAO.findDocCtgList(map);
	}
}

