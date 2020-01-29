package dream.rcm.fmea.dao;

import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFmeaDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @return
     */
    public RcmFmeaDetailDTO findDetail(RcmFmeaCommonDTO rcmFmeaCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     */
    public int insertDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     */
    public int updateDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO);
}