package dream.rcm.taskmap.service;

import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapDetailDTO;

/**
 * 질의 - 상세 service
 * 
 * @author kim21017
 * @version $Id: RcmTaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmTaskMapDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmTaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapCommonDTO
     * @return
     * @throws Exception
     */
    public RcmTaskMapDetailDTO findDetail(RcmTaskMapCommonDTO rcmTaskMapCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmTaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmTaskMapDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmTaskMapDetailDTO rcmTaskMapDetailDTO) throws Exception;

}
