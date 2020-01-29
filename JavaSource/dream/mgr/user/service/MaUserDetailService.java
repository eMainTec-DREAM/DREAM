package dream.mgr.user.service;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;

/**
 * 사용자 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaUserDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaUserDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid userId
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validUserNo(MaUserDetailDTO maUserDetailDTO, User loginUser) throws Exception;

    public int sendUserInfoMail(MaUserCommonDTO maUserCommonDTO, User user) throws Exception;

    public int sendUserPwMail(MaUserCommonDTO maUserCommonDTO, User user) throws Exception;

    public String getNextSequence() throws Exception;
}
