package dream.rcm.pmtask.dao;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskMapDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskMapDetailDTO findDetail(RcmPmtaskMapListDTO rcmPmtaskMapListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int updateDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskMapDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskMapDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int insertDetail(RcmPmtaskMapDetailDTO rcmPmtaskMapDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);
}