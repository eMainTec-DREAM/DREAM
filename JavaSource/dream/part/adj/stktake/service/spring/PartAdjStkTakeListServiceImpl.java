package dream.part.adj.stktake.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.adj.stktake.dao.PartAdjStkTakeListDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.service.PartAdjStkTakeListService;

/**
 * 부품실사 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: PartAdjStkTakeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="partAdjStkTakeListServiceTarget"
 * @spring.txbn id="partAdjStkTakeListService"
 * @spring.property name="partAdjStkTakeListDAO" ref="partAdjStkTakeListDAO"
 */
public class PartAdjStkTakeListServiceImpl implements PartAdjStkTakeListService
{
    private PartAdjStkTakeListDAO partAdjStkTakeListDAO = null;

    public PartAdjStkTakeListDAO getPartAdjStkTakeListDAO() {
		return partAdjStkTakeListDAO;
	}

	public void setPartAdjStkTakeListDAO(PartAdjStkTakeListDAO partAdjStkTakeListDAO) {
		this.partAdjStkTakeListDAO = partAdjStkTakeListDAO;
	}

	public List findBuyList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {      
        return partAdjStkTakeListDAO.findItemList(partAdjStkTakeCommonDTO,user);
    }
	
	public int deleteBuy(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + partAdjStkTakeListDAO.deleteItem(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception {
		return partAdjStkTakeListDAO.findTotalCount(partAdjStkTakeCommonDTO, user);
	}
	
}

