package intf.dream.cricket.log.service.spring;

import java.util.Map;

import intf.dream.cricket.log.dao.CricketLogDAO;
import intf.dream.cricket.log.service.CricketLogService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketLogServiceTarget"
 * @spring.txbn id="cricketLogService"
 * @spring.property name="cricketLogDAO" ref="cricketLogDAO"
 */
public class CricketLogServiceImpl implements CricketLogService
{
    private CricketLogDAO cricketLogDAO = null;

	public CricketLogDAO getCricketLogDAO() {
		return cricketLogDAO;
	}
	public void setCricketLogDAO(CricketLogDAO cricketLogDAO) {
		this.cricketLogDAO = cricketLogDAO;
	}
	
	public int saveLoginLog(Map map)  throws Exception
	{
        return cricketLogDAO.saveLoginLog(map);
	}
}

