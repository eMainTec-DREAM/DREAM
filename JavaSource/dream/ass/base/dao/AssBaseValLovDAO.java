package dream.ass.base.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.base.dto.AssBaseValLovDTO;

/**
 * 설비등급 평가기준 LOV - List DAO
 * @author kim21017
 * @version $Id: AssBaseValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBaseValLovDAO
{
	/**
	 * FIND LIST
	 * @param assBaseValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(AssBaseValLovDTO assBaseValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param assBaseValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBaseValLovDTO assBaseValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}