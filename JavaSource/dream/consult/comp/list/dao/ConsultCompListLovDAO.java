package dream.consult.comp.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;
import dream.org.dept.dto.LovDeptListDTO;

/**
 * DAO 
 * @author  hyosung
 * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 */
public interface ConsultCompListLovDAO
{
    /**
     *  DAO 
     * @author  hyosung
     * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param consultCompListLovDTO
     * @param loginUser
     * @return
     */
    public List findCompList(ConsultCompListLovDTO consultCompListLovDTO, User loginUser);
    
    
    /**
     *  AC LOV
     * @author  hyosung
     * @version $Id: ConsultCompListLovDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param consultCompListLovDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findCompAcList(ConsultCompListLovDTO consultCompListLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap);
    
    
}