package dream.org.emp.dao;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaEmpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findEmpList(MaEmpCommonDTO maEmpCommonDTO, User user) throws IOException;
    
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

    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, User user);
}