package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;

/**
 * 고정표준값 - List DAO
 * @author kim21017
 * @version $Id: WorkPmStdCalibListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkPmStdCalibListDAO
{
	/**
	 * FIND LIST
	 * @param workPmStdCalibCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception;
    
    /**
     * DELETE HDR LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE VALUE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteValList(String id, User user) throws Exception;

	/**
	 * FIND LIST COUNT
	 * @param workPmStdCalibCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception;
}