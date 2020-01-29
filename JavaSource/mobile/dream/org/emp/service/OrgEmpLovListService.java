package mobile.dream.org.emp.service;

import java.util.List;

import common.bean.User;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * ����˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface OrgEmpLovListService
{

    /**
     * ����˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param OrgEmpLovListDTO
     * @return
     */
    List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser);
}