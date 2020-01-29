package intf.dream.android.online.finder.syscode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.syscode.dao.AnOnFinderSyscodeListDAO;
import intf.dream.android.online.finder.syscode.service.AnOnFinderSyscodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderSyscodeListServiceTarget"
 * @spring.txbn id="anOnFinderSyscodeListService"
 * @spring.property name="anOnFinderSyscodeListDAO" ref="anOnFinderSyscodeListDAO"
 */
public class AnOnFinderSyscodeListServiceImpl implements AnOnFinderSyscodeListService
{
    private AnOnFinderSyscodeListDAO anOnFinderSyscodeListDAO = null;

	public AnOnFinderSyscodeListDAO getAnOnFinderSyscodeListDAO() {
		return anOnFinderSyscodeListDAO;
	}
	public void setAnOnFinderSyscodeListDAO(AnOnFinderSyscodeListDAO anOnFinderSyscodeListDAO) {
		this.anOnFinderSyscodeListDAO = anOnFinderSyscodeListDAO;
	}
	
	public List findSyscodeList(Map map) throws Exception
	{      
		return anOnFinderSyscodeListDAO.findSyscodeList(map);
	}
}

