package dream.rcm.system.service;

import common.bean.User;
import dream.rcm.system.dto.RcmSysDetailDTO;

/**
 * System분석 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface RcmSysDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     * @throws Exception
     */
    public RcmSysDetailDTO findDetail(User user, String id)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(User user, RcmSysDetailDTO rcmSysDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(User user, RcmSysDetailDTO rcmSysDetailDTO) throws Exception;
}
