package dream.consult.comp.emp.dao;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface ConsultCompEmpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findEmpList(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user) throws IOException;
    
    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteEmp(String compNo, String empId);

    public String findTotalCount(ConsultCompEmpCommonDTO consultCompEmpCommonDTO, User user);
}