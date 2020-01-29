package intf.dream.android.online.common.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.common.dao.AnOnCommonListDAO;
import intf.dream.android.online.common.service.AnOnCommonListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnCommonListServiceTarget"
 * @spring.txbn id="anOnCommonListService"
 * @spring.property name="anOnCommonListDAO" ref="anOnCommonListDAO"
 */
public class AnOnCommonListServiceImpl implements AnOnCommonListService
{
    private AnOnCommonListDAO anOnCommonListDAO = null;

	public AnOnCommonListDAO getAnOnCommonListDAO() {
		return anOnCommonListDAO;
	}
	public void setAnOnCommonListDAO(AnOnCommonListDAO anOnCommonListDAO) {
		this.anOnCommonListDAO = anOnCommonListDAO;
	}
	
	public List findNextVal(Map map) throws Exception
	{      
		return anOnCommonListDAO.findNextVal(map);
	}
	public List findConfigVal(Map map) throws Exception
	{      
		return anOnCommonListDAO.findConfigVal(map);
	}
}

