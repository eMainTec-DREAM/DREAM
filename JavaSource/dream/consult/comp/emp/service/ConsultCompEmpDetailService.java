package dream.consult.comp.emp.service;

import common.bean.User;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;

/**
 * 사원 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompEmpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     * @throws Exception
     */
    public ConsultCompEmpDetailDTO findDetail(User user, ConsultCompEmpCommonDTO consultCompEmpCommonDTO)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception;
    
    /**
     * valid empNo 사원 번호 
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     * @throws Exception
     */
    public String validEmpNo(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception;
}
