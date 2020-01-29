package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.form.WorkPmStdCalibValLovForm;
/**
 * 교정표준값 LOV - List Service
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkPmStdCalibValLovService {
	/**
	 * FIND LIST
	 * @param workPmStdCalibValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(WorkPmStdCalibValLovForm workPmStdCalibValLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param workPmStdCalibValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(WorkPmStdCalibValLovForm workPmStdCalibValLovForm, User user) throws Exception;
}
