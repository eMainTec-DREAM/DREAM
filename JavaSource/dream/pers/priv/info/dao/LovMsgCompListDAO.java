package dream.pers.priv.info.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.pers.priv.info.dto.LovMsgCompListDTO;

/**
 * 메시지타입 팝업 DAO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovMsgCompListDAO
{

	/**
	 * AC Find List
	 * @param lovMsgCompListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 */
	public List findMsgCompAcList(LovMsgCompListDTO lovMsgCompListDTO, User user, Map<String, String> conditionMap);

	/**
     * FIND TOTAL LIST
     * @param lovMsgCompListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovMsgCompListDTO lovMsgCompListDTO, User user, Map<String, String> conditionMap) throws Exception;
    
}