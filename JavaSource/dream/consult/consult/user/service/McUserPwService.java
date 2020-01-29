package dream.consult.consult.user.service;

import common.bean.User;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserPwDTO;


/**
 * 비밀번호설정
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface McUserPwService
{    
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcMenuCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public McUserPwDTO findDetail(McUserCommonDTO mcMenuCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcMenuPwDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(McUserPwDTO mcMenuPwDTO, User loginUser) throws Exception;
}
