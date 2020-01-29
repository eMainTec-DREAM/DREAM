package dream.consult.program.menu.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.program.menu.dto.LovMenuListDTO;

/**
 * ¸Þ´º ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovMenuListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovMenuListDTO
     * @param loginUser
     * @return
     */
    public List findMenuList(LovMenuListDTO lovMenuListDTO, User loginUser);

    public List findMenuAcList(LovMenuListDTO lovMenuListDTO, User user,
            Map<String, String> conditionMap);
}