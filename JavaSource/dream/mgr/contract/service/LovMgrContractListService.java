package dream.mgr.contract.service;

import java.util.List;

import common.bean.User;
import dream.mgr.contract.form.LovMgrContractListForm;
/**
 * 단가계약 LOV - List Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface LovMgrContractListService {
	/**
	 * FIND LIST
	 * @param lovMgrContractListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(LovMgrContractListForm lovMgrContractListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param lovMgrContractListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(LovMgrContractListForm lovMgrContractListForm, User user) throws Exception;
}
