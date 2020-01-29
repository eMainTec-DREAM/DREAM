package intf.dream.android.online.finder.eqctg.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.eqctg.dao.AnOnFinderEqctgListDAO;
import intf.dream.android.online.finder.eqctg.service.AnOnFinderEqctgListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderEqctgListServiceTarget"
 * @spring.txbn id="anOnFinderEqctgListService"
 * @spring.property name="anOnFinderEqctgListDAO" ref="anOnFinderEqctgListDAO"
 */
public class AnOnFinderEqctgListServiceImpl implements AnOnFinderEqctgListService
{
    private AnOnFinderEqctgListDAO anOnFinderEqctgListDAO = null;

	public AnOnFinderEqctgListDAO getAnOnFinderEqctgListDAO() {
		return anOnFinderEqctgListDAO;
	}
	public void setAnOnFinderEqctgListDAO(AnOnFinderEqctgListDAO anOnFinderEqctgListDAO) {
		this.anOnFinderEqctgListDAO = anOnFinderEqctgListDAO;
	}
	
	public List findEqctgList(Map map) throws Exception
	{      
		return anOnFinderEqctgListDAO.findEqctgList(map);
	}
}

