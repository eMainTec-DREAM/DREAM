package dream.rcm.funceq.service;

import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * ´äº¯ - detail
 * @author  kim210117
 * @version $Id: RcmFuncEqItemDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmFuncEqItemDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemListDTO
     * @param rcmFuncEqCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFuncEqItemDetailDTO findDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) throws Exception;
}
