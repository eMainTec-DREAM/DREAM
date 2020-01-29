package dream.mgr.user.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.user.dto.MaUserCommonDTO;

/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MgrUserPlantauthListDAO extends BaseJdbcDaoSupportIntf
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
    public int deleteUser(MaUserCommonDTO maUserCommonDTO, User loginUser);
}