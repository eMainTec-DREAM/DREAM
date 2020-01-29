package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.form.LovEqToolAcListForm;
/**
 * LOV - List Service
 * @author youngjoo38
 * @version $Id: LovEqToolAcListService.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface LovEqToolAcListService {
	/**
	 * FIND LIST
	 * @param lovEqToolAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovEqToolAcListForm lovEqToolAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovEqToolAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovEqToolAcListForm lovEqToolAcListForm, User user) throws Exception;
}
