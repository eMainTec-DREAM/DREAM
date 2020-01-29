package dream.consult.program.table.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.table.dto.TableColValLovDTO;

/**
 * Å×ÀÌºí Column LOV - List DAO
 * @author kim21017
 * @version $Id: TableColValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface TableColValLovDAO
{
	/**
	 * FIND LIST
	 * @param tableColValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(TableColValLovDTO tableColValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param tableColValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(TableColValLovDTO tableColValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}