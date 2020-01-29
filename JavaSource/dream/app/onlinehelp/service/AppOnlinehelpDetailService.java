package dream.app.onlinehelp.service;

import common.bean.User;
import dream.app.onlinehelp.dto.AppOnlinehelpCommonDTO;
import dream.app.onlinehelp.dto.AppOnlinehelpDetailDTO;

/**
 * 도움말 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AppOnlinehelpDetailService
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
     * @throws Exception
     */
    public AppOnlinehelpDetailDTO findDetail(AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user)throws Exception;
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
     * @throws Exception
     */
    public int updateDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user) throws Exception;
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
     * @throws Exception
     */
    public int insertDetail(AppOnlinehelpDetailDTO appOnlinehelpDetailDTO, AppOnlinehelpCommonDTO appOnlinehelpCommonDTO, User user) throws Exception;
}
