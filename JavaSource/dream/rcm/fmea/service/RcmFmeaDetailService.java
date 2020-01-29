package dream.rcm.fmea.service;

import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id: RcmFmeaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFmeaDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFmeaDetailDTO findDetail(RcmFmeaCommonDTO rcmFmeaCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO) throws Exception;
}
