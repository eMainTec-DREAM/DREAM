package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
/**
 * 교정표준값 타입 - List Service
 * @author kim21017
 * @version $Id: WorkPmStdCalibListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkPmStdCalibListService {
	/**
	 * FIND LIST
	 * @param workPmStdCalibCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param workPmStdCalibCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO, User user) throws Exception;
}
