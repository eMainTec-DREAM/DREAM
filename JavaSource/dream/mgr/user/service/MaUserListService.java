package dream.mgr.user.service;

import java.util.List;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;

/**
 * 사용자 - 목록 service
 * @author ssong 
 * @version $Id:  $
 * @since   1.0
 */
public interface MaUserListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser);    
    public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser);
    /**
     * delete List
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    
    
    public void resetPassword(MaUserCommonDTO maUserCommonDTO, User loginUser);
}
