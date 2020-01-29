package dream.consult.program.table.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.table.dto.TableValLovDTO;

/**
 * Å×ÀÌºí LOV - List DAO
 * @author kim21017
 * @version $Id: TableValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface TableValLovDAO
{
	/**
	 * FIND LIST
	 * @param tableValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(TableValLovDTO tableValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param tableValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(TableValLovDTO tableValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}