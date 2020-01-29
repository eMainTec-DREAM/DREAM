package dream.part.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.list.dto.LovPartsListDTO;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovPartsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPartsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovPartsListDTO lovPartsListDTO, User loginUser);
    /**
     * 자재 검색 AC LOV
     * @author  kim21017
     * @version $Id: LovPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPartsListForm
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findPartsAcList(LovPartsListDTO lovPartsListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
    /**
     * FIND LIST COUNT
     * @param lovPartsListDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovPartsListDTO lovPartsListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;

}