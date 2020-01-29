package dream.consult.comp.plant.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.plant.dto.LovPlantListDTO;

/**
 * 플랜트 팝업
 * @author  kim21017
 * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovPlantListDAO
{
    /**
     *  검색
     * @author  kim21017
     * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPlantListDTO
     * @param loginUser
     * @return
     */
    public List findPlantList(LovPlantListDTO lovPlantListDTO, User loginUser);
    /**
     *  AC 검색 
     * @author  kim21017
     * @version $Id: LovPlantListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPlantListDTO
     * @param loginUser
     * @return
     */
    public List findPlantAcList(LovPlantListDTO lovPlantListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
}