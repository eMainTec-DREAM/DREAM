package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovStdCheckPointListDTO;
import dream.work.pm.list.form.LovStdCheckPointListForm;

/**
 * 표준점검항목 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovStdCheckPointListService
{

    /**
     * 표준점검항목
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