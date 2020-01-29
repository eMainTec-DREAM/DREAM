package dream.part.adj.stktake.service;

import java.util.List;

import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사item 목록
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeItemListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @param partAdjStkTakeListDTO
     * @throws Exception
     */
    public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO,User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteItemList(String[] deleteRows, User user) throws Exception;
    /**
     *  TOTALCOUNT
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * @param partAdjStkTakeCommonDTO
     * @param partAdjStkTakeListDTO
     * @param user 
     * @throws Exception
     */
    public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user) throws Exception;
    /**
     *  INSERT
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @param user
     * @throws Exception
     */
    public int insertItemList(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;

    public String getData(User user);

}
