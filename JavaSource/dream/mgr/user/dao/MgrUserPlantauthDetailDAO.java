package dream.mgr.user.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MgrUserPlantauthDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MgrUserPlantauthDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public int insertDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser);
    
    public int updateDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser);

    public String validPlant(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User user, String isRegbatch) throws Exception;
}