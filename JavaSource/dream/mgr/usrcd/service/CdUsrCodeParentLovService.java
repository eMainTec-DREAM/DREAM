package dream.mgr.usrcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.form.CdUsrCodeParentLovForm;
/**
 * 사용자코드 부모 LOV - List Service
 * @author kim21017
 * @version $Id: CdUsrCodeParentLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface CdUsrCodeParentLovService {
	/**
	 * FIND LIST
	 * @param cdUsrCodeParentLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(CdUsrCodeParentLovForm cdUsrCodeParentLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param cdUsrCodeParentLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(CdUsrCodeParentLovForm cdUsrCodeParentLovForm, User user) throws Exception;
}
