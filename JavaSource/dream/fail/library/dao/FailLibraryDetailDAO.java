package dream.fail.library.dao;

import common.bean.User;
import dream.fail.library.dto.FailLibraryDetailDTO;

/**
 * 고장library - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface FailLibraryDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public FailLibraryDetailDTO findDetail(User user, String failureId);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int insertDetail(FailLibraryDetailDTO failLibraryDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int updateDetail(FailLibraryDetailDTO  failLibraryDetailDTO);

}