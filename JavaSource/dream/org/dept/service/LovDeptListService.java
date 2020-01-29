package dream.org.dept.service;

import java.util.List;

import common.bean.User;
import dream.org.dept.dto.LovDeptListDTO;
import dream.org.dept.form.LovDeptListForm;

/**
 * �μ��˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovDeptListService
{

    /**
     * �μ��˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovDeptListDTO
     * @param loginUser
     * @return
     */
    List findDeptList(LovDeptListDTO lovDeptListDTO, User loginUser);

	List findDeptAcList(LovDeptListDTO lovDeptListDTO, User user, LovDeptListForm lovDeptListForm);
}