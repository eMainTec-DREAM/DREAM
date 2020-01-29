package dream.work.pm.std.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibValLovDTO;

/**
 * 교정표준값 LOV - List DAO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkPmStdCalibValLovDAO
{
	/**
	 * FIND LIST
	 * @param workPmStdCalibValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param workPmStdCalibValLovDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}