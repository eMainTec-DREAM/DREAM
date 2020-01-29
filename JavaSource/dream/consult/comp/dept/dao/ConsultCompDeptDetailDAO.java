package dream.consult.comp.dept.dao;


import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;

/**
 * 보전부서 - 상세 dao
 * 
 * @author hyosung
 * @version $Id:  $
 * @since 1.0
 */
public interface ConsultCompDeptDetailDAO
{
    /**
     * detail find
     * @author hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public ConsultCompDeptDetailDTO findDetail(ConsultCompDeptCommonDTO consultCompDeptCommonDTO,String langId);
    
    /**
     * detail insert
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO);
    
    /**
     * detail update
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO);
    
    /**
     * valid deptNo
     * @author  hyosung
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public String validDeptNo(ConsultCompDeptDetailDTO consultCompDeptDetailDTO);
}