package dream.consult.program.menu.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.menu.dto.LovConsultMenuAcListDTO;

/**
 * 컨설트용 메뉴 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovConsultMenuAcListDAO
{
    /**
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovConsultMenuAcListDTO
     * @param loginUser
     * @return
     */
    public List findMenuList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User loginUser);

    public List findMenuAcList(LovConsultMenuAcListDTO lovConsultMenuAcListDTO, User user,
            Map<String, String> conditionMap);
}