package dream.mgr.usrrpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.form.LovUsrRptTabAcListForm;
/**
 * LOV - List Service
 * @author youngjoo38
 * @version $Id: LovUsrRptTabAcListService.java,v 1.0 2017/09/12 16:12:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface LovUsrRptTabAcListService {
	/**
	 * FIND LIST
	 * @param lovUsrRptTabAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovUsrRptTabAcListForm lovUsrRptTabAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovUsrRptTabAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovUsrRptTabAcListForm lovUsrRptTabAcListForm, User user) throws Exception;
}
