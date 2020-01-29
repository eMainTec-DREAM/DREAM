package dream.mgr.user.service;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * 사용자 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MgrUserPlantauthDetailService
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
    public MgrUserPlantauthDetailDTO findDetail(MaUserCommonDTO maUserCommonDTO, User loginUser)throws Exception;
    
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
    public int insertDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser) throws Exception;
    
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
    public int updateDetail(MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser) throws Exception;
    public String validPlant(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User user, String isRegbatch) throws Exception;
 
}
