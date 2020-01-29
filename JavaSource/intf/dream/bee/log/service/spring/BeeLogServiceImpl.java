package intf.dream.bee.log.service.spring;

import java.util.Map;

import intf.dream.bee.log.dao.BeeLogDAO;
import intf.dream.bee.log.service.BeeLogService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeLogServiceTarget"
 * @spring.txbn id="beeLogService"
 * @spring.property name="beeLogDAO" ref="beeLogDAO"
 */
public class BeeLogServiceImpl implements BeeLogService
{
    private BeeLogDAO beeLogDAO = null;

	public BeeLogDAO getBeeLogDAO() {
		return beeLogDAO;
	}
	public void setBeeLogDAO(BeeLogDAO beeLogDAO) {
		this.beeLogDAO = beeLogDAO;
	}
	
	public int saveLoginLog(Map map)  throws Exception
	{
        return beeLogDAO.saveLoginLog(map);
	}
}

