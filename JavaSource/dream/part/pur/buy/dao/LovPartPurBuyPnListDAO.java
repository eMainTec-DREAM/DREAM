package dream.part.pur.buy.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.pur.buy.dto.LovPartPurBuyPnListDTO;

/**
 * 현장신청부품 선택 Lov
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface LovPartPurBuyPnListDAO
{
    /**
     * 현장신청부품 검색 LOV
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartPurBuyPnListForm
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findAcList(LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);
    /**
     * FIND LIST COUNT
     * @param lovPartPurBuyPnListDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovPartPurBuyPnListDTO lovPartPurBuyPnListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;

}