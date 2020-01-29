package dream.mgr.usrcd.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;

/**
 * ���ڵ� �˾�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovCdUsrdAcListDAO
{
    /**
     * �˻�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovCdUsrdAcListDTO
     * @param loginUser
     * @return
     */
    public List findCdUsrdList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovCdUsrdAcListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findCdUsrdAcList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User user, Map<String, String> conditionMap);
}