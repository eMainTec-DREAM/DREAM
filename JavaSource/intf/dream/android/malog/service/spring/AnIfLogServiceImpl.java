package intf.dream.android.malog.service.spring;

import java.util.Map;

import intf.dream.android.malog.dao.AnIfLogDAO;
import intf.dream.android.malog.service.AnIfLogService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfLogServiceTarget"
 * @spring.txbn id="anIfLogService"
 * @spring.property name="anIfLogDAO" ref="anIfLogDAO"
 */
public class AnIfLogServiceImpl implements AnIfLogService
{
    private AnIfLogDAO anIfLogDAO = null;

	public AnIfLogDAO getAnIfLogDAO() {
		return anIfLogDAO;
	}
	public void setAnIfLogDAO(AnIfLogDAO anIfLogDAO) {
		this.anIfLogDAO = anIfLogDAO;
	}
	
	public int saveLoginLog(Map map)  throws Exception
	{
        return anIfLogDAO.saveLoginLog(map);
	}
}

