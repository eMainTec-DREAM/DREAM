package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPageListDTO;

/**
 * 페이지 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovPageListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPageListDTO
     * @param loginUser
     * @return
     */
    public List findPageList(LovPageListDTO lovPageListDTO, User loginUser);

    public List findDeptAcList(LovPageListDTO lovPageListDTO, User user,
            Map<String, String> columnMap, Map<String, String> conditionMap);
    
    public String findTotalCount(LovPageListDTO lovPageListDTO, User user,
            Map<String, String> columnMap, Map<String, String> conditionMap);
}