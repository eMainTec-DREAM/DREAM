package mobile.dream.org.dept.service;

import java.util.List;

import common.bean.User;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;

/**
 * �μ��˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface OrgDeptLovListService
{

    /**
     * �μ��˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param OrgDeptLovListDTO
     * @param loginUser
     * @return
     */
    List findDeptList(OrgDeptLovListDTO orgDeptLovListDTO, User loginUser);
}