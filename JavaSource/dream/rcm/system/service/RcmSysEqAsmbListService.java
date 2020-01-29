package dream.rcm.system.service;

import java.util.List;

import common.bean.User;

import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비부위 목록
 * @author  kim21017
 * @version $Id: RcmSysEqAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqAsmbListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEqAsmbListDTO
     * @throws Exception
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: RcmSysEqAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
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
     * @version $Id: RcmSysEqAsmbListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysEqAsmbListDTO
     * @throws Exception
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO) throws Exception;
}
