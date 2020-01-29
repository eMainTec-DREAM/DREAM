package intf.dream.android.online.count.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.count.dao.AnOnCountListDAO;
import intf.dream.android.online.count.service.AnOnCountListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnCountListServiceTarget"
 * @spring.txbn id="anOnCountListService"
 * @spring.property name="anOnCountListDAO" ref="anOnCountListDAO"
 */
public class AnOnCountListServiceImpl implements AnOnCountListService
{
    private AnOnCountListDAO anOnCountListDAO = null;

	public AnOnCountListDAO getAnOnCountListDAO() {
		return anOnCountListDAO;
	}
	public void setAnOnCountListDAO(AnOnCountListDAO anOnCountListDAO) {
		this.anOnCountListDAO = anOnCountListDAO;
	}
	
	public List findMainList(Map map) throws Exception
	{      
		return anOnCountListDAO.findMainList(map);
	}
}

