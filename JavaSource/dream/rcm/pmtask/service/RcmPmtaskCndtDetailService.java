package dream.rcm.pmtask.service;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;

/**
 * detail
 * @author  kim210117
 * @version $Id: RcmPmtaskCndtDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmPmtaskCndtDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public RcmPmtaskCndtDetailDTO findDetail(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception;
}
