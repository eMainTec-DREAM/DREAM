package dream.rcm.pmtask.service;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.form.RcmPmtaskAcListForm;
/**
 * LOV - List Service
 * @author kim21017
 * @version $Id: RcmPmtaskAcListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmPmtaskAcListService {
	/**
	 * FIND LIST
	 * @param rcmPmtaskAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(RcmPmtaskAcListForm rcmPmtaskAcListForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param rcmPmtaskAcListForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(RcmPmtaskAcListForm rcmPmtaskAcListForm, User user) throws Exception;
}
