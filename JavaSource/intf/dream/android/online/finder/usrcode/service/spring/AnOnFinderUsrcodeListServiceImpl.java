package intf.dream.android.online.finder.usrcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.usrcode.dao.AnOnFinderUsrcodeListDAO;
import intf.dream.android.online.finder.usrcode.service.AnOnFinderUsrcodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderUsrcodeListServiceTarget"
 * @spring.txbn id="anOnFinderUsrcodeListService"
 * @spring.property name="anOnFinderUsrcodeListDAO" ref="anOnFinderUsrcodeListDAO"
 */
public class AnOnFinderUsrcodeListServiceImpl implements AnOnFinderUsrcodeListService
{
    private AnOnFinderUsrcodeListDAO anOnFinderUsrcodeListDAO = null;

	public AnOnFinderUsrcodeListDAO getAnOnFinderUsrcodeListDAO() {
		return anOnFinderUsrcodeListDAO;
	}
	public void setAnOnFinderUsrcodeListDAO(AnOnFinderUsrcodeListDAO anOnFinderUsrcodeListDAO) {
		this.anOnFinderUsrcodeListDAO = anOnFinderUsrcodeListDAO;
	}
	
	public List findUsrcodeList(Map map) throws Exception
	{      
		return anOnFinderUsrcodeListDAO.findUsrcodeList(map);
	}
}

