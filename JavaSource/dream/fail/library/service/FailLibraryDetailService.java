package dream.fail.library.service;

import common.bean.User;
import dream.fail.library.dto.FailLibraryDetailDTO;

/**
 * 고장library - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface FailLibraryDetailService
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
    public FailLibraryDetailDTO findDetail(User user, String failureId)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(FailLibraryDetailDTO failLibraryDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(FailLibraryDetailDTO failLibraryDetailDTO) throws Exception;
}
