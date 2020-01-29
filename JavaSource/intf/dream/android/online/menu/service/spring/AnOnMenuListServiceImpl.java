package intf.dream.android.online.menu.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.menu.dao.AnOnMenuListDAO;
import intf.dream.android.online.menu.service.AnOnMenuListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnMenuListServiceTarget"
 * @spring.txbn id="anOnMenuListService"
 * @spring.property name="anOnMenuListDAO" ref="anOnMenuListDAO"
 */
public class AnOnMenuListServiceImpl implements AnOnMenuListService
{
    private AnOnMenuListDAO anOnMenuListDAO = null;
    
	public AnOnMenuListDAO getAnOnMenuListDAO() {
		return anOnMenuListDAO;
	}
	public void setAnOnMenuListDAO(AnOnMenuListDAO anOnMenuListDAO) {
		this.anOnMenuListDAO = anOnMenuListDAO;
	}
	
	public List findMenuList(Map map) throws Exception
	{      
		return anOnMenuListDAO.findMenuList(map);
	}
}

