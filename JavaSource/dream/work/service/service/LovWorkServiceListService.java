package dream.work.service.service;

import java.util.List;

import common.bean.User;
import dream.work.service.form.LovWorkServiceListForm;
/**
 * ¼­ºñ½º LOV - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface LovWorkServiceListService {
	/**
	 * FIND LIST
	 * @param lovWorkServiceListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovWorkServiceListForm lovWorkServiceListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovWorkServiceListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovWorkServiceListForm lovWorkServiceListForm, User user) throws Exception;
}
