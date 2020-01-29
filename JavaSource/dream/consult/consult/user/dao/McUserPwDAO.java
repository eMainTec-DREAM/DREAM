package dream.consult.consult.user.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;


/**
 * 비밀번호설정 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface McUserPwDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param loginUser
     * @return
     */
    public McUserPwDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserPwDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(McUserPwDTO mcUserPwDTO, User loginUser);
}