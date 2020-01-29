package intf.dream.ant.ptstktake.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.ant.ptstktake.dao.AntPtstktakeListDAO;
import intf.dream.ant.ptstktake.service.AntPtstktakeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPtstktakeListServiceTarget"
 * @spring.txbn id="antPtstktakeListService"
 * @spring.property name="antPtstktakeListDAO" ref="antPtstktakeListDAO"
 */
public class AntPtstktakeListServiceImpl implements AntPtstktakeListService
{
    private AntPtstktakeListDAO antPtstktakeListDAO = null;

	public AntPtstktakeListDAO getAntPtstktakeListDAO() {
		return antPtstktakeListDAO;
	}
	public void setAntPtstktakeListDAO(AntPtstktakeListDAO antPtstktakeListDAO) {
		this.antPtstktakeListDAO = antPtstktakeListDAO;
	}
	
	public List findStktakeList(Map map) throws Exception
	{      
		return antPtstktakeListDAO.findStktakeList(map);
	}
	public List findStktakeItem(Map map) throws Exception
	{      
		return antPtstktakeListDAO.findStktakeItem(map);
	}
	public int savePtstktakeItem(List list)  throws Exception
	{
		int resultQty = 0;
		for(Object obj : list){
			Map map = (Map)obj;
			antPtstktakeListDAO.savePtstktakeItem(map);
        }
        return resultQty;
	}
}

