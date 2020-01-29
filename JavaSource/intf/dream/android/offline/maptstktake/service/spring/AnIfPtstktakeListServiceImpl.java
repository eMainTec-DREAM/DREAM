package intf.dream.android.offline.maptstktake.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.offline.maptstktake.dao.AnIfPtstktakeListDAO;
import intf.dream.android.offline.maptstktake.service.AnIfPtstktakeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPtstktakeListServiceTarget"
 * @spring.txbn id="anIfPtstktakeListService"
 * @spring.property name="anIfPtstktakeListDAO" ref="anIfPtstktakeListDAO"
 */
public class AnIfPtstktakeListServiceImpl implements AnIfPtstktakeListService
{
    private AnIfPtstktakeListDAO anIfPtstktakeListDAO = null;

	public AnIfPtstktakeListDAO getAnIfPtstktakeListDAO() {
		return anIfPtstktakeListDAO;
	}
	public void setAnIfPtstktakeListDAO(AnIfPtstktakeListDAO anIfPtstktakeListDAO) {
		this.anIfPtstktakeListDAO = anIfPtstktakeListDAO;
	}
	
	public List findStktakeList(Map map) throws Exception
	{      
		return anIfPtstktakeListDAO.findStktakeList(map);
	}
	public List findStktakeItem(Map map) throws Exception
	{      
		return anIfPtstktakeListDAO.findStktakeItem(map);
	}
	public int savePtstktakeItem(List list)  throws Exception
	{
		int resultQty = 0;
		for(Object obj : list){
			Map map = (Map)obj;
			anIfPtstktakeListDAO.savePtstktakeItem(map);
        }
        return resultQty;
	}
}

