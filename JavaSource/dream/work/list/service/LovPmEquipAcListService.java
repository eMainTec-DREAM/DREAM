package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.LovPmEquipAcListDTO;
import dream.work.list.form.LovPmEquipAcListForm;

/**
 * 积魂前格 Lov Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPmEquipAcListService
{

    /**
     * 积魂前格 Lov
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovPmEquipAcListDTO
     * @return
     */
    List findPmEquipAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovPmEquipAcListDTO
	 * @param user
	 * @param lovPmEquipAcListForm
	 * @return
	 */
	List findPmEquipAcAcList(LovPmEquipAcListDTO lovPmEquipAcListDTO, User user, LovPmEquipAcListForm lovPmEquipAcListForm);
}