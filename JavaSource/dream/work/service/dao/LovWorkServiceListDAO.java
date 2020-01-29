package dream.work.service.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.service.dto.LovWorkServiceListDTO;

/**
 * ¼­ºñ½º LOV - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface LovWorkServiceListDAO
{
	/**
	 * FIND LIST
	 * @param lovWorkServiceListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovWorkServiceListDTO lovWorkServiceListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovWorkServiceListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovWorkServiceListDTO lovWorkServiceListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}