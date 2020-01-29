package dream.rcm.ffail.service;

import dream.rcm.ffail.dto.RcmFfailCommonDTO;
import dream.rcm.ffail.dto.RcmFfailDetailDTO;

/**
 * 질의 - 상세 service
 * 
 * @author kim21017
 * @version $Id: RcmFfailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmFfailDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFfailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailCommonDTO
     * @return
     * @throws Exception
     */
    public RcmFfailDetailDTO findDetail(RcmFfailCommonDTO rcmFfailCommonDTO)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFfailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFfailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: RcmFfailDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(RcmFfailDetailDTO rcmFfailDetailDTO) throws Exception;
}
