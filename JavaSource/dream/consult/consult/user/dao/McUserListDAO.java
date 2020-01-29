package dream.consult.consult.user.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserListDTO;


/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface McUserListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param user
     * @return List
     */
    public List findUserList(McUserCommonDTO mcUserCommonDTO, User loginUser);
    
    public int updateUsers(Map map);
    
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
    public int deleteUser(McUserListDTO mcUserListDTO, User loginUser);
}