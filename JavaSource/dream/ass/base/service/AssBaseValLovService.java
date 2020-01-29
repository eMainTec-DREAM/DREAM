package dream.ass.base.service;

import java.util.List;

import common.bean.User;
import dream.ass.base.dto.AssBaseValLovDTO;
import dream.ass.base.form.AssBaseValLovForm;
/**
 * 설비등급 평가기준 LOV - List Service
 * @author kim21017
 * @version $Id: AssBaseValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface AssBaseValLovService {
	/**
	 * FIND LIST
	 * @param assBaseValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(AssBaseValLovForm assBaseValLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param assBaseValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(AssBaseValLovForm assBaseValLovForm, User user) throws Exception;
}
