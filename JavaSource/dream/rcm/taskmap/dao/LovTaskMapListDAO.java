package dream.rcm.taskmap.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.rcm.taskmap.dto.LovTaskMapListDTO;


/**
 * ���� �˾�
 * @author  hyosung
 * @version $Id: LovTaskMapListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 */
public interface LovTaskMapListDAO
{
    /**
     * ������� �˻�
     * @author  hyosung
     * @version $Id: LovTaskMapListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovTaskMapListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findTaskMapList(LovTaskMapListDTO lovTaskMapListDTO, User loginUser, Map<String, String> conditionMap);
}