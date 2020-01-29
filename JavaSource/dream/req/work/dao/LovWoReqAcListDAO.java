package dream.req.work.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.req.work.dto.LovWoReqAcListDTO;

/**
 * 작업요청 팝업
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 */
public interface LovWoReqAcListDAO
{
    /**
     * 작업요청 검색
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovWoReqAcListDTO
     * @param user
     * @param conditionMap
     * @return
     */
	public List findWoReqAcList(LovWoReqAcListDTO lovWoReqAcListDTO, User user, Map<String, String> conditionMap);

	/**
     * FIND TOTAL LIST
     * @param lovWoReqAcListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser, Map<String, String> conditionMap) throws Exception;
	
}