package dream.part.adj.stktake.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사 item 목록 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeItemListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @param partAdjStkTakeListDTO
     * @return List
     */
    public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO,User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String id, User user);
    
    public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user) throws Exception;

    public String getData(User user);
    
}