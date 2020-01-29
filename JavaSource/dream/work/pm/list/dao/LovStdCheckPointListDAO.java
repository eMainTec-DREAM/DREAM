package dream.work.pm.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;

/**
 * ǥ�������׸� �˾�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovStdCheckPointListDAO
{
    /**
     * �˻�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdCheckPointListDTO
     * @param loginUser
     * @return
     */
    public List findStdCheckPointList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovStdCheckPointListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findStdCheckPointAcList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User user, Map<String, String> conditionMap);
}