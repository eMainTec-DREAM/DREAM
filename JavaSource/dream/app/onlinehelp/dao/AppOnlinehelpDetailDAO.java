package dream.app.onlinehelp.dao;

import common.bean.User;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;

/**
 * 도움말 상세 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AppOnlinehelpDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpCommonDTO
     * @param user 
     * @return
     */
    public AppOnlinehelpDetailDTO findDetail(AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user);

    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailDTO
     * @param appOnlinehelpCommonDTO
     * @param user 
     * @return
     */
    public int updateDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param appOnlinehelpDetailDTO
     * @param appOnlinehelpCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user);
    
}