package intf.dream.ant.ptiss.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.ant.ptiss.dao.AntPtissListDAO;
import intf.dream.ant.ptiss.service.AntPtissListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPtissListServiceTarget"
 * @spring.txbn id="antPtissListService"
 * @spring.property name="antPtissListDAO" ref="antPtissListDAO"
 */
public class AntPtissListServiceImpl implements AntPtissListService
{
    private AntPtissListDAO antPtissListDAO = null;

	public AntPtissListDAO getAntPtissListDAO() {
		return antPtissListDAO;
	}
	public void setAntPtissListDAO(AntPtissListDAO antPtissListDAO) {
		this.antPtissListDAO = antPtissListDAO;
	}
	
	public int savePtiss(List list)  throws Exception
	{
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;

            String ptIssListId = antPtissListDAO.getNextSequence("SQAPTISSLIST_ID");
            String ptIssueId   = antPtissListDAO.getNextSequence("SQAPTISSUE_ID");
			antPtissListDAO.savePtissList(map,ptIssListId, ptIssueId);
			antPtissListDAO.savePtissue(map,ptIssueId);
        }
        return resultQty;
	}
}

