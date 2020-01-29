package dream.mgr.msgrec.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.msgrec.dto.LovMsgCategListDTO;

/**
 * 메시지타입 팝업 DAO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovMsgCategListDAO
{

	/**
	 * AC Find List
	 * @param lovMsgCategListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findMsgCategAcList(LovMsgCategListDTO lovMsgCategListDTO, User user, Map<String, String> conditionMap);

	/**
     * FIND TOTAL LIST
     * @param lovMsgCategListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovMsgCategListDTO lovMsgCategListDTO, User user, Map<String, String> conditionMap) throws Exception;
    
}