package dream.rcm.ffail.dao;

import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;

/**
 * 질의 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFfailDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return
     */
    public RcmFfailDetailDTO findDetail(RcmFfailCommonDTO rcmFfailCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int insertDetail(RcmFfailDetailDTO rcmFfailDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int updateDetail(RcmFfailDetailDTO rcmFfailDetailDTO);

    /**
     * detail confirm
     * @author kim21017
     * @version $Id: RcmFfailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     */
    public int confirmDetail(RcmFfailDetailDTO rcmFfailDetailDTO);
}