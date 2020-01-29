package dream.consult.consult.user.service;

import common.bean.User;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;


/**
 * 사용자 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface McUserDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param mcUserCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public McUserDetailDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid userId
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validUserNo(McUserDetailDTO mcUserDetailDTO, User loginUser) throws Exception;
}
