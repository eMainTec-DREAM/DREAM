package dream.org.emp.service;

import common.bean.User;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.MaEmpDetailDTO;

/**
 * 사원 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaEmpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param user
     * @return
     * @throws Exception
     */
    public MaEmpDetailDTO findDetail(MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEmpDetailDTO maEmpDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEmpDetailDTO maEmpDetailDTO, User user) throws Exception;
    
    /**
     * valid empNo 사원 번호 
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEmpDetailDTO
     * @return
     * @throws Exception
     */
    public String validEmpNo(String empId, String empNo, User user) throws Exception;
}
