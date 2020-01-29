package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;

/**
 * 표준교정값 - List DAO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListDAO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public interface WorkPmStdCalibValListDAO
{
	/**
	 * FIND LIST
	 * @param workPmStdCalibCommonDTO
	 * @param workPmStdCalibValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param workPmStdCalibCommonDTO
	 * @param workPmStdCalibValListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO,WorkPmStdCalibValListDTO workPmStdCalibValListDTO, User user) throws Exception;
    
}