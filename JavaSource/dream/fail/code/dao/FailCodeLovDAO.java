package dream.fail.code.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface FailCodeLovDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureCommonDTO
     * @param conditionMap 
     * @param columnMap 
     * @return List
     */
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);

}