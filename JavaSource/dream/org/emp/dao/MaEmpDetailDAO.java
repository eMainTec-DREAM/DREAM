package dream.org.emp.dao;

import common.bean.User;
import dream.org.emp.dto.MaEmpDetailDTO;

/**
 * 사원 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaEmpDetailDAO
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public int insertDetail(MaEmpDetailDTO maEmpDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public int updateDetail(MaEmpDetailDTO maEmpDetailDTO);

    /**
     * valid empId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     */
    public String validEmpNo(String empId, String empNo, User user);
    
    public int updateUserStatus(MaEmpDetailDTO maEmpDetailDTO, User user);
}