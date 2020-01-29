package dream.work.pm.std.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;

/**
 * 표준점검항목 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovStdWrkWorkListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovStdWrkWorkListDTO
     * @param loginUser
     * @return
     */
    public List findStdCheckPointList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovStdWrkWorkListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findStdCheckPointAcList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User user, Map<String, String> conditionMap);
}