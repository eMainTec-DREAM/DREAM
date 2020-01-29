package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovProductAcListDTO;
import dream.work.pm.list.form.LovProductAcListForm;

/**
 * ������ǰ Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovProductAcListService
{

    /**
     * ������ǰ
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovProductAcListDTO
     * @return
     */
    List findProductAcList(LovProductAcListDTO lovProductAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovProductAcListDTO
	 * @param user
	 * @param lovProductAcListForm
	 * @return
	 */
	List findProductAcAcList(LovProductAcListDTO lovProductAcListDTO, User user, LovProductAcListForm lovProductAcListForm);
}