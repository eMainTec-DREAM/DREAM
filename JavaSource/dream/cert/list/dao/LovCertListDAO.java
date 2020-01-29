package dream.cert.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.cert.list.dto.LovCertListDTO;

/**
 * �ڰ���Lov
 * @author  hyosung
 * @version $Id: LovCertListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 */
public interface LovCertListDAO
{
    /**
     * �ڰ��� �˻�
     * @author  hyosung
     * @version $Id: LovCertListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovCertListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findCertList(LovCertListDTO lovCertListDTO, User loginUser, Map<String, String> conditionMap);
}