package dream.rcm.fmea.dao;

import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFmeaCrityDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityListDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public RcmFmeaCrityDetailDTO findDetail(RcmFmeaCrityListDTO rcmFmeaCrityListDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public int updateDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaCrityDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCrityDetailDTO
     * @param rcmFmeaCommonDTO
     * @return
     */
    public int insertDetail(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);

	public int findVal(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO, RcmFmeaCommonDTO rcmFmeaCommonDTO);
}