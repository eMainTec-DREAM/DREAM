package dream.mgr.usage.cal.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usage.cal.dto.LovUsageWrkAcListDTO;

/**
 * 사용달력 LOV - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface LovUsageWrkAcListDAO
{
	/**
	 * FIND LIST
	 * @param lovUsageWrkAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovUsageWrkAcListDTO lovUsageWrkAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovUsageWrkAcListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovUsageWrkAcListDTO lovUsageWrkAcListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}