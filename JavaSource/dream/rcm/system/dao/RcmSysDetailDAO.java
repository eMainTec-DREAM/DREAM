package dream.rcm.system.dao;

import common.bean.User;
import dream.rcm.system.dto.RcmSysDetailDTO;

/**
 * System분석 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface RcmSysDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public RcmSysDetailDTO findDetail(User user, String id);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     */
    public int insertDetail(User user, RcmSysDetailDTO rcmSysDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysDetailDTO
     * @return
     */
    public int updateDetail(User user, RcmSysDetailDTO rcmSysDetailDTO);
}