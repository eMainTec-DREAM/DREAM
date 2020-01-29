package mobile.dream.org.emp.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.org.emp.dto.OrgEmpLovListDTO;

/**
 * ����˻� �˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface OrgEmpLovListDAO
{
    /**
     * �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgEmpLovListDTO
     * @param loginUser
     * @return
     */
    public List findEmpList(OrgEmpLovListDTO orgEmpLovListDTO, User loginUser);
}