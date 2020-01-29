package dream.rcm.crity.service;

import java.util.List;


import common.bean.User;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.form.RcmCrityValLovForm;

/**
 * Criticality Matrix Page - List Service
 * @author kim21017
 * @version $Id: RcmCrityValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface RcmCrityValLovService {
	/**
	 * FIND LIST
	 * @param rcmCrityValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(RcmCrityValLovForm rcmCrityValLovForm, User user) throws Exception;

}
