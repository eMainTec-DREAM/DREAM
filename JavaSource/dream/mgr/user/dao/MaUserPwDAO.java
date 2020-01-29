package dream.mgr.user.dao;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserPwDTO;

/**
 * 비밀번호설정 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaUserPwDAO
{
    /**
     * detail find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return
     */
    public MaUserPwDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser);

    /**
     * detail update
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserPwDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaUserPwDTO maUserPwDTO, User loginUser);
    public int insertPwChanHist(MaUserPwDTO maUserPwDTO, User loginUser);
    public String checkPwHist(MaUserPwDTO maUserPwDTO, String pwChangeHistCnt);
}