package dream.ass.base.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.base.dto.AssBasePointValValLovDTO;

/**
 * 평가항목 평가기준 LOV - List DAO
 * @author kim21017
 * @version $Id: AssBasePointValValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface AssBasePointValValLovDAO
{
	/**
	 * FIND LIST
	 * @param assBasePointValValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(AssBasePointValValLovDTO assBasePointValValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param assBasePointValValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AssBasePointValValLovDTO assBasePointValValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}