package dream.consult.program.btn.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.btn.dto.LovButtonListDTO;

/**
 * ¹öÆ° Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovButtonListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovButtonListDTO
     * @param loginUser
     * @return
     */
    List findButtonList(LovButtonListDTO lovButtonListDTO, User loginUser);
}