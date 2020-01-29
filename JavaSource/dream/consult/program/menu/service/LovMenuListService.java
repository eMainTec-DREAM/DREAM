package dream.consult.program.menu.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.menu.dto.LovMenuListDTO;
import dream.consult.program.menu.form.LovMenuListForm;

/**
 * ¸Þ´º Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovMenuListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovMenuListDTO
     * @param loginUser
     * @return
     */
    List findMenuList(LovMenuListDTO lovMenuListDTO, User loginUser);

    List findMenuAcList(LovMenuListDTO lovMenuListDTO, User user,
            LovMenuListForm lovMenuListForm);
}