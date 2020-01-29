package dream.consult.comp.time.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.time.form.LovLnWrkAcListForm;
/**
 * 가동달력 LOV - List Service
 * @author youngjoo38
 * @version $Id: LovLnWrkAcListService.java,v 1.0 2017/12/14 11:09:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface LovLnWrkAcListService {
	/**
	 * FIND LIST
	 * @param lovLnWrkAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovLnWrkAcListForm lovLnWrkAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovLnWrkAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovLnWrkAcListForm lovLnWrkAcListForm, User user) throws Exception;
}
