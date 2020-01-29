package dream.main.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.main.dto.MaDbListDTO;

/**
 * Dashboard - ¸ñ·Ï dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaDbListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDbListDTO
     * @param resultMap 
     * @param user 
     * @param admin 
     * @return List
     */
    public List findDbList(MaDbListDTO maDbListDTO, Map resultMap, Map ordNoMap, User user);
    
    public List findAlarmCode(User loginUser);
}