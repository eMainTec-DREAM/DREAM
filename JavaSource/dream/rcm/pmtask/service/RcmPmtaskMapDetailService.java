package dream.rcm.pmtask.service;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * detail
 * @author  kim210117
 * @version $Id: RcmPmtaskMapDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmPmtaskMapDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public RcmPmtaskMapDetailDTO findDetail(RcmPmtaskMapListDTO rcmPmtaskMapListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO) throws Exception;
}
