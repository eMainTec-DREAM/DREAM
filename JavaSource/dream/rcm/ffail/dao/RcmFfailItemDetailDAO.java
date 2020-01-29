package dream.rcm.ffail.dao;

import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFfailItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemListDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public RcmFfailItemDetailDTO findDetail(RcmFfailItemListDTO rcmFfailItemListDTO, RcmFfailCommonDTO rcmFfailCommonDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public int updateDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailItemDetailDTO
     * @param rcmFfailCommonDTO
     * @return
     */
    public int insertDetail(RcmFfailItemDetailDTO rcmFfailItemDetailDTO, RcmFfailCommonDTO rcmFfailCommonDTO);
}