package dream.rcm.pmtask.dao;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtDetailDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskCndtDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtListDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskCndtDetailDTO findDetail(RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int updateDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskCndtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCndtDetailDTO
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public int insertDetail(RcmPmtaskCndtDetailDTO rcmPmtaskCndtDetailDTO, RcmPmtaskCommonDTO rcmPmtaskCommonDTO);
}