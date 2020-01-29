package dream.mgr.user.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.user.dto.MaUserDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaUserDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public int insertDetail(MaUserDetailDTO maUserDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public int updateDetail(MaUserDetailDTO maUserDetailDTO, User loginUser);
    
    /**
     * valid userId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public String validUserNo(MaUserDetailDTO maUserDetailDTO, User loginUser);

    public void updateRpw(MaUserDetailDTO maUserDetailDTO);
}