package intf.dream.android.online.finder.emp.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.emp.dao.AnOnFinderEmpListDAO;
import intf.dream.android.online.finder.emp.service.AnOnFinderEmpListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderEmpListServiceTarget"
 * @spring.txbn id="anOnFinderEmpListService"
 * @spring.property name="anOnFinderEmpListDAO" ref="anOnFinderEmpListDAO"
 */
public class AnOnFinderEmpListServiceImpl implements AnOnFinderEmpListService
{
    private AnOnFinderEmpListDAO anOnFinderEmpListDAO = null;

	public AnOnFinderEmpListDAO getAnOnFinderEmpListDAO() {
		return anOnFinderEmpListDAO;
	}
	public void setAnOnFinderEmpListDAO(AnOnFinderEmpListDAO anOnFinderEmpListDAO) {
		this.anOnFinderEmpListDAO = anOnFinderEmpListDAO;
	}
	
	public List findEmpList(Map map) throws Exception
	{      
		return anOnFinderEmpListDAO.findEmpList(map);
	}
}

