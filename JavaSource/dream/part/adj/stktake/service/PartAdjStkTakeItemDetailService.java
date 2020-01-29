package dream.part.adj.stktake.service;

import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import common.bean.User;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사 item - detail
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface PartAdjStkTakeItemDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeListDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     * @throws Exception
     */
    public PartAdjStkTakeItemDetailDTO findDetail(PartAdjStkTakeListDTO partAdjStkTakeListDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;
    
    public String validItem(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception;
}
