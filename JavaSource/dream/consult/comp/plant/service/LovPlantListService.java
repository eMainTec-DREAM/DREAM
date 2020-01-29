package dream.consult.comp.plant.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.plant.dto.LovPlantListDTO;
import dream.consult.comp.plant.form.LovPlantListForm;

/**
 * �÷�Ʈ Service
 * @author  kim21017
 * @version $Id: LovPlantListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovPlantListService
{

    /**
     * ���� �˻�
     * @author  kim21017
     * @version $Id: LovPlantListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovPlantListDTO
     * @param loginUser
     * @return
     */
    List findPlantList(LovPlantListDTO lovPlantListDTO, User loginUser);
    /**
     * ���� AC �˻�
     * @author  kim21017
     * @version $Id: LovPlantListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovPlantListDTO
     * @param loginUser
     * @return
     */
    List findPlantAcList(LovPlantListForm lovPlantListForm, User loginUser);
}