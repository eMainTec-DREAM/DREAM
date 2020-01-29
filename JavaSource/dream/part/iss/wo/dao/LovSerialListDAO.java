package dream.part.iss.wo.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.iss.wo.dto.LovSerialListDTO;


/**
 * ���� �˾�
 * @author  hyosung
 * @version $Id: LovSerialListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 */
public interface LovSerialListDAO
{
    /**
     * ������� �˻�
     * @author  hyosung
     * @version $Id: LovSerialListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovSerialListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findSerialList(LovSerialListDTO lovSerialListDTO, User loginUser, Map<String, String> conditionMap);
}