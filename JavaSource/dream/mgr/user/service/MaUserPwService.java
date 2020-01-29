package dream.mgr.user.service;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;

/**
 * 비밀번호설정
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaUserPwService
{    
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaUserPwDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserPwDTO maUserPwDTO, User loginUser) throws Exception;
    
    public String checkPwHist(MaUserPwDTO maUserPwDTO, String pwChangeHistCnt) throws Exception;
    
}
