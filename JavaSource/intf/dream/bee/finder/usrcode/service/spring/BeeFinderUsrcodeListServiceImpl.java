package intf.dream.bee.finder.usrcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.usrcode.dao.BeeFinderUsrcodeListDAO;
import intf.dream.bee.finder.usrcode.service.BeeFinderUsrcodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderUsrcodeListServiceTarget"
 * @spring.txbn id="beeFinderUsrcodeListService"
 * @spring.property name="beeFinderUsrcodeListDAO" ref="beeFinderUsrcodeListDAO"
 */
public class BeeFinderUsrcodeListServiceImpl implements BeeFinderUsrcodeListService
{
    private BeeFinderUsrcodeListDAO beeFinderUsrcodeListDAO = null;

	public BeeFinderUsrcodeListDAO getBeeFinderUsrcodeListDAO() {
		return beeFinderUsrcodeListDAO;
	}
	public void setBeeFinderUsrcodeListDAO(BeeFinderUsrcodeListDAO beeFinderUsrcodeListDAO) {
		this.beeFinderUsrcodeListDAO = beeFinderUsrcodeListDAO;
	}
	
	public List findUsrcodeList(Map map) throws Exception
	{      
		return beeFinderUsrcodeListDAO.findUsrcodeList(map);
	}
}

