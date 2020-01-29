package dream.rcm.pmtask.service;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id: RcmPmtaskDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmPmtaskDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @return
     * @throws Exception
     */
    public RcmPmtaskDetailDTO findDetail(RcmPmtaskCommonDTO rcmPmtaskCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO) throws Exception;
}
