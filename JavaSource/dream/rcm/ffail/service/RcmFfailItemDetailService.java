package dream.rcm.ffail.service;

import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * ´äº¯ - detail
 * @author  kim210117
 * @version $Id: RcmFfailItemDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmFfailItemDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemListDTO
     * @param rcmFfailCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFfailItemDetailDTO findDetail(RcmFfailItemListDTO rcmFfailItemListDTO, RcmFfailCommonDTO rcmFfailCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailItemDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO) throws Exception;
}
