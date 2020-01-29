package dream.consult.program.btn.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.btn.dto.LovButtonListDTO;

/**
 * ¹öÆ° ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovButtonListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovButtonListDTO
     * @param loginUser
     * @return
     */
    public List findButtonList(LovButtonListDTO lovButtonListDTO, User loginUser);
}