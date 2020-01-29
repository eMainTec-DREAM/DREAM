package dream.fail.code.service;

import common.bean.User;
import dream.fail.code.dto.MaFailureDetailDTO;

/**
 * 고장코드 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaFailureDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     * @throws Exception
     */
    public MaFailureDetailDTO findDetail(User user, String failureId)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaFailureDetailDTO maFailureDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaFailureDetailDTO maFailureDetailDTO) throws Exception;
    
    /**
     * valid No
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maFailureDetailDTO
     * @return
     * @throws Exception
     */
    public String validFailureNo(MaFailureDetailDTO maFailureDetailDTO) throws Exception;
}
