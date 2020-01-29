package dream.part.adj.stkmove.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.adj.stkmove.dao.PartAdjStkMoveListDAO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.service.PartAdjStkMoveListService;

/**
 * 재고이동 - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partAdjStkMoveListServiceTarget"
 * @spring.txbn id="partAdjStkMoveListService"
 * @spring.property name="partAdjStkMoveListDAO" ref="partAdjStkMoveListDAO"
 */
public class PartAdjStkMoveListServiceImpl implements PartAdjStkMoveListService
{
    private PartAdjStkMoveListDAO partAdjStkMoveListDAO = null;

    public PartAdjStkMoveListDAO getPartAdjStkMoveListDAO() {
		return partAdjStkMoveListDAO;
	}

	public void setPartAdjStkMoveListDAO(PartAdjStkMoveListDAO partAdjStkMoveListDAO) {
		this.partAdjStkMoveListDAO = partAdjStkMoveListDAO;
	}

	public List findList(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user)
    {      
        return partAdjStkMoveListDAO.findItemList(partAdjStkMoveCommonDTO,user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + partAdjStkMoveListDAO.deleteItem(id,user);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception {
		return partAdjStkMoveListDAO.findTotalCount(partAdjStkMoveCommonDTO, user);
	}
	
}

