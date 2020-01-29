package dream.work.pm.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.pm.list.dto.LovPmNoListDTO;

/**
 * �������˰˻� �˾�
 * @author  kim21017
 * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovPmNoListDAO
{
    /**
     * �������� �˻�
     * @author  kim21017
     * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @param loginUser
     * @return
     */
    public List findPmList(LovPmNoListDTO lovPmNoListDTO, User loginUser);
    /**
     * �������� �˻� AC
     * @author  kim21017
     * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findPmAcList(LovPmNoListDTO lovPmNoListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
    /**
     * �������� �˻�
     * @author  kim21017
     * @version $Id: LovPmNoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public String findTotalCount(LovPmNoListDTO lovPmNoListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
}