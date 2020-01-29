package dream.ass.base.service;

import java.util.List;

import common.bean.User;
import dream.ass.base.form.AssBasePointValValLovForm;
/**
 * 평가항목 평가기준 LOV - List Service
 * @author kim21017
 * @version $Id: AssBaseListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBasePointValValLovService {
	/**
	 * FIND LIST
	 * @param assBasePointValValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(AssBasePointValValLovForm assBasePointValValLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assBasePointValValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(AssBasePointValValLovForm assBasePointValValLovForm, User user) throws Exception;
}
