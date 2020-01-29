package intf.dream.android.offline.maptstock.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.offline.maptstock.dao.AnIfPtstockListDAO;
import intf.dream.android.offline.maptstock.service.AnIfPtstockListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPtstockListServiceTarget"
 * @spring.txbn id="anIfPtstockListService"
 * @spring.property name="anIfPtstockListDAO" ref="anIfPtstockListDAO"
 */
public class AnIfPtstockListServiceImpl implements AnIfPtstockListService
{
    private AnIfPtstockListDAO anIfPtstockListDAO = null;

	public AnIfPtstockListDAO getAnIfPtstockListDAO() {
		return anIfPtstockListDAO;
	}
	public void setAnIfPtstockListDAO(AnIfPtstockListDAO anIfPtstockListDAO) {
		this.anIfPtstockListDAO = anIfPtstockListDAO;
	}
	
	public List findPartsList(Map map) throws Exception
	{      
		return anIfPtstockListDAO.findPartsList(map);
	}
	public List findStockList(Map map) throws Exception
	{      
		return anIfPtstockListDAO.findStockList(map);
	}
	public int savePtstock(List list)  throws Exception
	{
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			anIfPtstockListDAO.savePtstock(map);

            String ptStockAdjId = anIfPtstockListDAO.getNextSequence("SQAPTSTOCKADJ_ID");
            anIfPtstockListDAO.savePtstockLog(map, ptStockAdjId);
            
            //PDA POINT LOG
            //ifPmSchedListDAO.insertPdaWoPoint(map);
        }
        return resultQty;
	}
}

