package dream.fail.library.dao;

import java.util.List;

import common.bean.User;
import dream.fail.library.dto.FailLibraryCommonDTO;

/**
 * 고장코드 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface FailLibraryListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryCommonDTO
     * @return List
     */
    public List findList(FailLibraryCommonDTO failLibraryCommonDTO, User user);

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public int deleteParts(String compNo, String failureId);
}