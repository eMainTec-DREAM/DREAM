package intf.dream.cricket.common.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.common.dao.CricketCommonListDAO;
import intf.dream.cricket.common.service.CricketCommonListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketCommonListServiceTarget"
 * @spring.txbn id="cricketCommonListService"
 * @spring.property name="cricketCommonListDAO" ref="cricketCommonListDAO"
 */
public class CricketCommonListServiceImpl implements CricketCommonListService
{
    private CricketCommonListDAO cricketCommonListDAO = null;

	public CricketCommonListDAO getCricketCommonListDAO() {
		return cricketCommonListDAO;
	}
	public void setCricketCommonListDAO(CricketCommonListDAO cricketCommonListDAO) {
		this.cricketCommonListDAO = cricketCommonListDAO;
	}
	
	public List findNextVal(Map map) throws Exception
	{      
		return cricketCommonListDAO.findNextVal(map);
	}
	public List findConfigVal(Map map) throws Exception
	{      
		return cricketCommonListDAO.findConfigVal(map);
	}
}

