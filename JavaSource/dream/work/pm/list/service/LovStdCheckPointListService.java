package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;
import dream.work.pm.list.form.LovStdCheckPointListForm;

/**
 * ǥ�������׸� Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovStdCheckPointListService
{

    /**
     * ǥ�������׸�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovStdCheckPointListDTO
     * @return
     */
    List findStdCheckPointList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovStdCheckPointListDTO
	 * @param user
	 * @param lovStdCheckPointListForm
	 * @return
	 */
	List findStdCheckPointAcList(LovStdCheckPointListDTO lovStdCheckPointListDTO, User user, LovStdCheckPointListForm lovStdCheckPointListForm);
}