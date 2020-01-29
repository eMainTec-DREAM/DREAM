package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.LovStdWrkWorkListDTO;
import dream.work.pm.std.form.LovStdWrkWorkListForm;

/**
 * ǥ�������׸� Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovStdWrkWorkListService
{

    /**
     * ǥ�������׸�
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