package dream.consult.comp.dept.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;

/**
 * �����μ� - ��� dao
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 */
public interface ConsultCompDeptListDAO
{
    /**
     * grid find
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptCommonDTO
     * @return List
     */
    public List findDeptList(ConsultCompDeptCommonDTO consultCompDeptCommonDTO, User user);
    
    /**
     * ����
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deptId
     * @return
     */
    public int deleteDept(String compNo, String deptId);
}