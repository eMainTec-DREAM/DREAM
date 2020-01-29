package dream.pers.priv.info.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;

/**
 * 공장권한 팝업
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface LovUsrPlantAuthListDAO
{
    /**
     *  검색
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param lovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    public List findUsrPlantAuthList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser);
    /**
     *  AC 검색 
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param lovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    public List findPlantAcList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
}