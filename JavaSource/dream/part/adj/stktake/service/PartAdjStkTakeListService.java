package dream.part.adj.stktake.service;

import java.util.List;

import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;

/**
 * 부품실사 - 목록 service
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param partAdjStkTakeCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBuyList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user);    

    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDTOList
     * @return
     * @throws Exception
     */
    public int deleteBuy(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;

}
