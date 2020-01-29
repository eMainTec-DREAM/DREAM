package intf.dream.cricket.initcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.initcode.dao.CricketInitcodeListDAO;
import intf.dream.cricket.initcode.service.CricketInitcodeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketInitcodeListServiceTarget"
 * @spring.txbn id="cricketInitcodeListService"
 * @spring.property name="cricketInitcodeListDAO" ref="cricketInitcodeListDAO"
 */
public class CricketInitcodeListServiceImpl implements CricketInitcodeListService
{
    private CricketInitcodeListDAO cricketInitcodeListDAO = null;

	public CricketInitcodeListDAO getCricketInitcodeListDAO() {
		return cricketInitcodeListDAO;
	}
	public void setCricketInitcodeListDAO(CricketInitcodeListDAO cricketInitcodeListDAO) {
		this.cricketInitcodeListDAO = cricketInitcodeListDAO;
	}
	
	public List findLangList(Map map)
	{      
		return cricketInitcodeListDAO.findLangList(map);
	}
	public List findSyscodeList(Map map)
	{      
		return cricketInitcodeListDAO.findSyscodeList(map);
	}
	
}

