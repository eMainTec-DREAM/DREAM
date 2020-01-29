package dream.pers.priv.info.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.LovUsrPlantAuthListDTO;
import dream.pers.priv.info.form.LovUsrPlantAuthListForm;

/**
 * ������� Service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public interface LovUsrPlantAuthListService
{

    /**
     * ���� �˻�
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
     * ���� AC �˻�
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