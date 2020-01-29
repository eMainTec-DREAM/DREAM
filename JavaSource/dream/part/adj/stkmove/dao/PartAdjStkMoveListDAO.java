package dream.part.adj.stkmove.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;

/**
 * 재고이동 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface PartAdjStkMoveListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveCommonDTO
     * @return List
     */
    public List findItemList(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteItem(String id, User user);
    
    public String findTotalCount(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception;
    
}