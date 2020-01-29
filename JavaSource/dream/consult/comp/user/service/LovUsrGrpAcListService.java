package dream.consult.comp.user.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.form.LovUsrGrpAcListForm;
/**
 * LOV - List Service
 * @author youngjoo38
 * @version $Id: LovUsrGrpAcListService.java,v 1.0 2017/09/12 16:12:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface LovUsrGrpAcListService {
	/**
	 * FIND LIST
	 * @param lovUsrGrpAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovUsrGrpAcListForm lovUsrGrpAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovUsrGrpAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovUsrGrpAcListForm lovUsrGrpAcListForm, User user) throws Exception;
}
