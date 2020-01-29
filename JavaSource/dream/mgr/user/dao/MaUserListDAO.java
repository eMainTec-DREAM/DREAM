package dream.mgr.user.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserListDTO;

/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaUserListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param user
     * @return List
     */
    public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser);
    public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser);
    
    public int updateUsers(Map map, User user);
    
    /**
     * 사용자 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param userId
     * @return
     */
    public int deleteUser(MaUserListDTO maUserListDTO, User loginUser);
}