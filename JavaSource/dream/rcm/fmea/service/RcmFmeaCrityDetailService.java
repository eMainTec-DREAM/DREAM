package dream.rcm.fmea.service;

import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * detail
 * @author  kim210117
 * @version $Id: RcmFmeaCrityDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmFmeaCrityDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityListDTO
     * @param rcmFmeaCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFmeaCrityDetailDTO findDetail(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO) throws Exception;
	/**
	 * @param rcmFmeaCrityDetailDTO
	 * @param rcmFmeaCommonDTO
	 * @return
	 */
	public int findVal(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);
}
