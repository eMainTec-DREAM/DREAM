package intf.dream.android.online.iss.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.iss.dao.AnOnIssListDAO;
import intf.dream.android.online.iss.service.AnOnIssListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnIssListServiceTarget"
 * @spring.txbn id="anOnIssListService"
 * @spring.property name="anOnIssListDAO" ref="anOnIssListDAO"
 */
public class AnOnIssListServiceImpl implements AnOnIssListService
{
    private AnOnIssListDAO anOnIssListDAO = null;
    
	public AnOnIssListDAO getAnOnIssListDAO() {
		return anOnIssListDAO;
	}
	public void setAnOnIssListDAO(AnOnIssListDAO anOnIssListDAO) {
		this.anOnIssListDAO = anOnIssListDAO;
	}
	
	public List findIssList(Map map) throws Exception
	{      
		return anOnIssListDAO.findIssList(map);
	}
	public int deleteIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnIssListDAO.deleteIss(map);
		}
		return resultQty;
	}
	public int insertIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnIssListDAO.insertIss(map);
			anOnIssListDAO.insertIssHdr(map);
		}
		return resultQty;
	}
	public int updateIss(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnIssListDAO.updateIss(map);
		}
		return resultQty;
	}
}

