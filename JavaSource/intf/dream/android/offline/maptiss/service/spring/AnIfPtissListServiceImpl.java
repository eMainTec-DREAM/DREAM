package intf.dream.android.offline.maptiss.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.offline.maptiss.dao.AnIfPtissListDAO;
import intf.dream.android.offline.maptiss.service.AnIfPtissListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPtissListServiceTarget"
 * @spring.txbn id="anIfPtissListService"
 * @spring.property name="anIfPtissListDAO" ref="anIfPtissListDAO"
 */
public class AnIfPtissListServiceImpl implements AnIfPtissListService
{
    private AnIfPtissListDAO anIfPtissListDAO = null;

	public AnIfPtissListDAO getAnIfPtissListDAO() {
		return anIfPtissListDAO;
	}
	public void setAnIfPtissListDAO(AnIfPtissListDAO anIfPtissListDAO) {
		this.anIfPtissListDAO = anIfPtissListDAO;
	}
	
	public int savePtiss(List list)  throws Exception
	{
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;

            String ptIssListId = anIfPtissListDAO.getNextSequence("SQAPTISSLIST_ID");
            String ptIssueId   = anIfPtissListDAO.getNextSequence("SQAPTISSUE_ID");
			anIfPtissListDAO.savePtissList(map,ptIssListId, ptIssueId);
			anIfPtissListDAO.savePtissue(map,ptIssueId);
        }
        return resultQty;
	}
}

