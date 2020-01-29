package dream.mgr.usage.cal.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usage.cal.form.LovUsageWrkAcListForm;
/**
 * 사용달력 LOV - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface LovUsageWrkAcListService {
	/**
	 * FIND LIST
	 * @param lovUsageWrkAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovUsageWrkAcListForm lovUsageWrkAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovUsageWrkAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovUsageWrkAcListForm lovUsageWrkAcListForm, User user) throws Exception;
}
