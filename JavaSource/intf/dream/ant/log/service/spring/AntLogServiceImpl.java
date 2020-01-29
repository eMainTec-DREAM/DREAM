package intf.dream.ant.log.service.spring;

import java.util.Map;

import intf.dream.ant.log.dao.AntLogDAO;
import intf.dream.ant.log.service.AntLogService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antLogServiceTarget"
 * @spring.txbn id="antLogService"
 * @spring.property name="antLogDAO" ref="antLogDAO"
 */
public class AntLogServiceImpl implements AntLogService
{
    private AntLogDAO antLogDAO = null;

	public AntLogDAO getAntLogDAO() {
		return antLogDAO;
	}
	public void setAntLogDAO(AntLogDAO antLogDAO) {
		this.antLogDAO = antLogDAO;
	}
	
	public int saveLoginLog(Map map)  throws Exception
	{
        return antLogDAO.saveLoginLog(map);
	}
}

