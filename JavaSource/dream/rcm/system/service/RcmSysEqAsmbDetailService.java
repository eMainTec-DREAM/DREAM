package dream.rcm.system.service;

import common.bean.User;

import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비부위 - detail
 * @author  kim210117
 * @version $Id: RcmSysEqAsmbDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmSysEqAsmbDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbListDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public RcmSysEqAsmbDetailDTO findDetail(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqAsmbDetailDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception;
}
