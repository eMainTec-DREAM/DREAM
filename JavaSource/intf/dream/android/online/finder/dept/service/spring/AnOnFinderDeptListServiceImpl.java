package intf.dream.android.online.finder.dept.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.dept.dao.AnOnFinderDeptListDAO;
import intf.dream.android.online.finder.dept.service.AnOnFinderDeptListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderDeptListServiceTarget"
 * @spring.txbn id="anOnFinderDeptListService"
 * @spring.property name="anOnFinderDeptListDAO" ref="anOnFinderDeptListDAO"
 */
public class AnOnFinderDeptListServiceImpl implements AnOnFinderDeptListService
{
    private AnOnFinderDeptListDAO anOnFinderDeptListDAO = null;

	public AnOnFinderDeptListDAO getAnOnFinderDeptListDAO() {
		return anOnFinderDeptListDAO;
	}
	public void setAnOnFinderDeptListDAO(AnOnFinderDeptListDAO anOnFinderDeptListDAO) {
		this.anOnFinderDeptListDAO = anOnFinderDeptListDAO;
	}
	
	public List findDeptList(Map map) throws Exception
	{      
		return anOnFinderDeptListDAO.findDeptList(map);
	}
}

