package dream.rcm.system.service;

import common.bean.User;

import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경 - detail
 * @author  kim210117
 * @version $Id: RcmSysFEnvDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface RcmSysFEnvDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvListDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public RcmSysFEnvDetailDTO findDetail(RcmSysFEnvListDTO rcmSysFEnvListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO) throws Exception;
}
