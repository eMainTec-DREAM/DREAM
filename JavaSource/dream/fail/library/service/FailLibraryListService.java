package dream.fail.library.service;

import java.util.List;

import common.bean.User;
import dream.fail.library.dto.FailLibraryCommonDTO;

/**
 * 고장library - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface FailLibraryListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @param failLibraryCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(FailLibraryCommonDTO failLibraryCommonDTO, User user);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
}
