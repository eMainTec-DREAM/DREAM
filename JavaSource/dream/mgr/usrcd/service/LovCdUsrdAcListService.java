package dream.mgr.usrcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.LovCdUsrdAcListDTO;
import dream.mgr.usrcd.form.LovCdUsrdAcListForm;

/**
 * 惑技内靛 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovCdUsrdAcListService
{

    /**
     * 惑技内靛
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovCdUsrdAcListDTO
     * @return
     */
    List findCdUsrdList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovCdUsrdAcListDTO
	 * @param user
	 * @param lovCdUsrdAcListForm
	 * @return
	 */
	List findCdUsrdAcList(LovCdUsrdAcListDTO lovCdUsrdAcListDTO, User user, LovCdUsrdAcListForm lovCdUsrdAcListForm);
}