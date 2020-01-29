package dream.rcm.taskmap.service;

import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import common.bean.User;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * ´äº¯ - detail
 * @author  kim210117
 * @version $Id: RcmTaskMapItemDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmTaskMapItemDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemListDTO
     * @param rcmTaskMapCommonDTO
     * @return
     * @throws Exception
     */
    public RcmTaskMapItemDetailDTO findDetail(RcmTaskMapItemListDTO rcmTaskMapItemListDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception;
    
    public String taskNo(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) throws Exception;
}
