package dream.consult.program.menu.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;
import dream.consult.program.menu.form.LovConsultMenuAcListForm;

/**
 * 컨설트용 메뉴 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovConsultMenuAcListService
{

    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovConsultMenuAcListDTO
     * @param loginUser
     * @return
     */
    List findMenuList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User loginUser);

    List findMenuAcList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User user,
            LovConsultMenuAcListForm lovConsultMenuAcListForm);
}