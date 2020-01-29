package mobile.dream.org.dept.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.org.dept.dto.OrgDeptLovListDTO;

/**
 * �μ��˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface OrgDeptLovListDAO
{
    /**
     * ���� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgDeptLovListDTO
     * @param loginUser
     * @return
     */
    public List findDeptList(OrgDeptLovListDTO orgDeptLovListDTO, User loginUser);
}