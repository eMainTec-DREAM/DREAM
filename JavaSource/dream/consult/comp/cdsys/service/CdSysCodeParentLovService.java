package dream.consult.comp.cdsys.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.cdsys.form.CdSysCodeParentLovForm;
/**
 * 시스템코드 부모 LOV - List Service
 * @author kim21017
 * @version $Id: CdSysCodeParentLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface CdSysCodeParentLovService {
	/**
	 * FIND LIST
	 * @param cdSysCodeParentLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(CdSysCodeParentLovForm cdSysCodeParentLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param cdSysCodeParentLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(CdSysCodeParentLovForm cdSysCodeParentLovForm, User user) throws Exception;
}
