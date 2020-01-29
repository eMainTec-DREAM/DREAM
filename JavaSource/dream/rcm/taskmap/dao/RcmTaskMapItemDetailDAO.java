package dream.rcm.taskmap.dao;

import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import common.bean.User;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmTaskMapItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemListDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public RcmTaskMapItemDetailDTO findDetail(RcmTaskMapItemListDTO rcmTaskMapItemListDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public int updateDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapItemDetailDTO
     * @param rcmTaskMapCommonDTO
     * @return
     */
    public int insertDetail(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO, RcmTaskMapCommonDTO rcmTaskMapCommonDTO);
    
    public String taskNo(RcmTaskMapCommonDTO rcmTaskMapCommonDTO);
}