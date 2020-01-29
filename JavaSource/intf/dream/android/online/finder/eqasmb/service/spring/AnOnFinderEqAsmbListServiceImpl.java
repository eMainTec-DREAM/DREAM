package intf.dream.android.online.finder.eqasmb.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.eqasmb.dao.AnOnFinderEqAsmbListDAO;
import intf.dream.android.online.finder.eqasmb.service.AnOnFinderEqAsmbListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderEqAsmbListServiceTarget"
 * @spring.txbn id="anOnFinderEqAsmbListService"
 * @spring.property name="anOnFinderEqAsmbListDAO" ref="anOnFinderEqAsmbListDAO"
 */
public class AnOnFinderEqAsmbListServiceImpl implements AnOnFinderEqAsmbListService
{
    private AnOnFinderEqAsmbListDAO anOnFinderEqAsmbListDAO = null;

	public AnOnFinderEqAsmbListDAO getAnOnFinderEqAsmbListDAO() {
		return anOnFinderEqAsmbListDAO;
	}
	public void setAnOnFinderEqAsmbListDAO(AnOnFinderEqAsmbListDAO anOnFinderEqAsmbListDAO) {
		this.anOnFinderEqAsmbListDAO = anOnFinderEqAsmbListDAO;
	}
	
	public List findEqAsmbList(Map map) throws Exception
	{      
		return anOnFinderEqAsmbListDAO.findEqAsmbList(map);
	}
}

