package dream.rcm.funceq.service;

import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqDetailDTO;

/**
 * 질의 - 상세 service
 * 
 * @author kim21017
 * @version $Id: RcmFuncEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFuncEqDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFuncEqDetailDTO findDetail(RcmFuncEqCommonDTO rcmFuncEqCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFuncEqDetailDTO rcmFuncEqDetailDTO) throws Exception;
}
