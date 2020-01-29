package dream.part.stk.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.stk.dto.LovPtStckListDTO;

/**
 * 재고검색 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovPtStckListDAO
{
    /**
     * 재고 검색 AC LOV
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPtStckListForm
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findAcList(LovPtStckListDTO lovPtStckListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
    /**
     * FIND LIST COUNT
     * @param lovPtStckListDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovPtStckListDTO lovPtStckListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;

}