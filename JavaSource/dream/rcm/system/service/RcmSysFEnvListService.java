package dream.rcm.system.service;

import java.util.List;

import common.bean.User;

import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경 목록
 * @author  kim21017
 * @version $Id: RcmSysFEnvListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFEnvListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmSysFEnvListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmSysFEnvListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteList(String[] m_id, String[] d_id) throws Exception;
    /**
     *  input
     * @author  kim21017
     * @version $Id: RcmSysFEnvListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO 
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO);

}
