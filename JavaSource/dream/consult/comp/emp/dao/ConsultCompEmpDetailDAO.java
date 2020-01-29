package dream.consult.comp.emp.dao;

import common.bean.User;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;

/**
 * 사원 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface ConsultCompEmpDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param empId
     * @return
     */
    public ConsultCompEmpDetailDTO findDetail(User user, ConsultCompEmpCommonDTO consultCompEmpCommonDTO);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO);

    /**
     * valid empId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompEmpDetailDTO
     * @return
     */
    public String validEmpNo(ConsultCompEmpDetailDTO consultCompEmpDetailDTO);
}