package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;
import dream.work.pm.std.form.LovStdWrkWorkListForm;

/**
 * 표준점검항목 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovStdWrkWorkListService
{

    /**
     * 표준점검항목
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovStdWrkWorkListDTO
     * @return
     */
    List findStdCheckPointList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovStdWrkWorkListDTO
	 * @param user
	 * @param lovStdWrkWorkListForm
	 * @return
	 */
	List findStdCheckPointAcList(LovStdWrkWorkListDTO lovStdWrkWorkListDTO, User user, LovStdWrkWorkListForm lovStdWrkWorkListForm);
}