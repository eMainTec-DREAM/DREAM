package dream.work.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dto.LovPmEquipAcListDTO;

/**
 * ǥ�������׸� �˾�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovPmEquipAcListDAO
{
    /**
     * �˻�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPmEquipAcListDTO
     * @param loginUser
     * @return
     */
    public List findPmEquipAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovPmEquipAcListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findPmEquipAcAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User user, Map<String, String> conditionMap);
}