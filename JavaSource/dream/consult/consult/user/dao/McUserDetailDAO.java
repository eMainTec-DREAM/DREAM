package dream.consult.consult.user.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.consult.consult.user.dto.McUserCommonDTO;
import dream.consult.consult.user.dto.McUserDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface McUserDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param userId
     * @return
     */
    public McUserDetailDTO findDetail(McUserCommonDTO mcUserCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public int insertDetail(McUserDetailDTO mcUserDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public int updateDetail(McUserDetailDTO mcUserDetailDTO, User loginUser);
    
    /**
     * valid userId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param mcUserDetailDTO
     * @return
     */
    public String validUserNo(McUserDetailDTO mcUserDetailDTO, User loginUser);
}