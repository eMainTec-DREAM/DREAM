package intf.dream.bee.initcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.initcode.dao.BeeInitcodeListDAO;
import intf.dream.bee.initcode.service.BeeInitcodeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeInitcodeListServiceTarget"
 * @spring.txbn id="beeInitcodeListService"
 * @spring.property name="beeInitcodeListDAO" ref="beeInitcodeListDAO"
 */
public class BeeInitcodeListServiceImpl implements BeeInitcodeListService
{
    private BeeInitcodeListDAO beeInitcodeListDAO = null;

	public BeeInitcodeListDAO getBeeInitcodeListDAO() {
		return beeInitcodeListDAO;
	}
	public void setBeeInitcodeListDAO(BeeInitcodeListDAO beeInitcodeListDAO) {
		this.beeInitcodeListDAO = beeInitcodeListDAO;
	}
	
	public List findLangList(Map map)
	{      
		return beeInitcodeListDAO.findLangList(map);
	}
	public List findSyscodeList(Map map)
	{      
		return beeInitcodeListDAO.findSyscodeList(map);
	}
	
}

