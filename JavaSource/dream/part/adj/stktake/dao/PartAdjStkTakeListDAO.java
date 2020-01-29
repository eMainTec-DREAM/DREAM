package dream.part.adj.stktake.dao;

import java.util.List;

import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;

/**
 * 부품실사 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return List
     */
    public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteItem(String id, User user);
    
    public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;
    
}