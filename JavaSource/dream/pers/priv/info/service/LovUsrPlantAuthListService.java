package dream.pers.priv.info.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;
import dream.pers.priv.info.form.LovUsrPlantAuthListForm;

/**
 * 공장권한 Service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public interface LovUsrPlantAuthListService
{

    /**
     * 공장 검색
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param LovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    List findUsrPlantAuthList(LovUsrPlantAuthListDTO lovUsrPlantAuthListDTO, User loginUser);
    /**
     * 공장 AC 검색
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param LovUsrPlantAuthListDTO
     * @param loginUser
     * @return
     */
    List findPlantAcList(LovUsrPlantAuthListForm lovUsrPlantAuthListForm, User loginUser);
}