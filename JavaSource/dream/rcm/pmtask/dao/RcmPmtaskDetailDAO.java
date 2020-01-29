package dream.rcm.pmtask.dao;

import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface RcmPmtaskDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @return
     */
    public RcmPmtaskDetailDTO findDetail(RcmPmtaskCommonDTO rcmPmtaskCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     */
    public int insertDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmPmtaskDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskDetailDTO
     * @return
     */
    public int updateDetail(RcmPmtaskDetailDTO rcmPmtaskDetailDTO);
}