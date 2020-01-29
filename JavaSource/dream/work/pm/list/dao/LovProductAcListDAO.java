package dream.work.pm.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.list.dto.LovProductAcListDTO;

/**
 * ������ǰ �˾�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovProductAcListDAO
{
    /**
     * �˻�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovProductAcListDTO
     * @param loginUser
     * @return
     */
    public List findProductAcList(LovProductAcListDTO lovProductAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovProductAcListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findProductAcAcList(LovProductAcListDTO lovProductAcListDTO, User user, Map<String, String> conditionMap);
}