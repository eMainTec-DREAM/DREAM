package dream.rcm.system.dao;

import common.bean.User;

import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFEnvDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvListDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public RcmSysFEnvDetailDTO findDetail(RcmSysFEnvListDTO rcmSysFEnvListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int updateDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int insertDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO);
}