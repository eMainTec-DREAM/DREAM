package dream.asset.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.list.dto.LovEquipListDTO;

/**
 * 설비 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovEquipListDAO
{
    /**
     * 설비 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEquipListDTO
     * @param loginUser
     * @return
     */
    public List findEquipList(LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap);

    
	public List findEquipAcList(LovEquipListDTO lovEquipListDTO, User user, Map<String, String> conditionMap);
	
	/**
     * FIND TOTAL LIST
     * @param lovEquipListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovEquipListDTO lovEquipListDTO, User loginUser, Map<String, String> conditionMap) throws Exception;
	
}