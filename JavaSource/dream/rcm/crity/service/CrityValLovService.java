package dream.rcm.crity.service;

import java.util.List;


import common.bean.User;
import dream.rcm.crity.dto.CrityValLovDTO;
import dream.rcm.crity.form.CrityValLovForm;


/**
 * Criticality Matrix Page - List Service
 * @author hyosung
 * @version $Id: CrityValLovService.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 */
public interface CrityValLovService {
    /**
     * 질의검색
     * @author  hyosung
     * @version $Id: CrityValLovService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param crityValLovDTO
     * @param loginUser
     * @param crityValLovForm
     * @return
     */
	public List findList(CrityValLovDTO crityValLovDTO,User loginUser,CrityValLovForm crityValLovForm);

}
