package dream.consult.comp.plant.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.plant.dto.LovPlantListDTO;
import dream.consult.comp.plant.form.LovPlantListForm;

/**
 * 플랜트 Service
 * @author  kim21017
 * @version $Id: LovPlantListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovPlantListService
{

    /**
     * 공장 검색
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
     * 공장 AC 검색
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