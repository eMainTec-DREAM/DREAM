package intf.dream.ant.ptstock.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.ant.ptstock.dao.AntPtstockListDAO;
import intf.dream.ant.ptstock.service.AntPtstockListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPtstockListServiceTarget"
 * @spring.txbn id="antPtstockListService"
 * @spring.property name="antPtstockListDAO" ref="antPtstockListDAO"
 */
public class AntPtstockListServiceImpl implements AntPtstockListService
{
    private AntPtstockListDAO antPtstockListDAO = null;

	public AntPtstockListDAO getAntPtstockListDAO() {
		return antPtstockListDAO;
	}
	public void setAntPtstockListDAO(AntPtstockListDAO antPtstockListDAO) {
		this.antPtstockListDAO = antPtstockListDAO;
	}
	
	public List findPartsList(Map map) throws Exception
	{      
		return antPtstockListDAO.findPartsList(map);
	}
	public List findStockList(Map map) throws Exception
	{      
		return antPtstockListDAO.findStockList(map);
	}
	public int savePtstock(List list)  throws Exception
	{
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			antPtstockListDAO.savePtstock(map);

            String ptStockAdjId = antPtstockListDAO.getNextSequence("SQAPTSTOCKADJ_ID");
            antPtstockListDAO.savePtstockLog(map, ptStockAdjId);
            
            //PDA POINT LOG
            //ifPmSchedListDAO.insertPdaWoPoint(map);
        }
        return resultQty;
	}
}

