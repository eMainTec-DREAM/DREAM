package intf.dream.cricket.finder.usrcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.usrcode.dao.CricketFinderUsrcodeListDAO;
import intf.dream.cricket.finder.usrcode.service.CricketFinderUsrcodeListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderUsrcodeListServiceTarget"
 * @spring.txbn id="cricketFinderUsrcodeListService"
 * @spring.property name="cricketFinderUsrcodeListDAO" ref="cricketFinderUsrcodeListDAO"
 */
public class CricketFinderUsrcodeListServiceImpl implements CricketFinderUsrcodeListService
{
    private CricketFinderUsrcodeListDAO cricketFinderUsrcodeListDAO = null;

	public CricketFinderUsrcodeListDAO getCricketFinderUsrcodeListDAO() {
		return cricketFinderUsrcodeListDAO;
	}
	public void setCricketFinderUsrcodeListDAO(CricketFinderUsrcodeListDAO cricketFinderUsrcodeListDAO) {
		this.cricketFinderUsrcodeListDAO = cricketFinderUsrcodeListDAO;
	}
	
	public List findUsrcodeList(Map map) throws Exception
	{      
		return cricketFinderUsrcodeListDAO.findUsrcodeList(map);
	}
}

