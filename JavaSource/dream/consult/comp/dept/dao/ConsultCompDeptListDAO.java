package dream.consult.comp.dept.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;

/**
 * 보전부서 - 목록 dao
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
     * 삭제
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