package dream.consult.comp.dept.service;


import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;

/**
 * 보전부서 - 상세 service
 * 
 * @author hyosung
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompDeptDetailService
{    
    /**
     * detail find
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param deptNo
     * @return
     * @throws Exception
     */
    public ConsultCompDeptDetailDTO findDetail(ConsultCompDeptCommonDTO compDeptCommonDTO,String langId)throws Exception;
    
    /**
     * detail insert
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception;
    
    /**
     * valid deptNo
     * @author  hyosung
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     * @throws Exception
     */
    public String validDeptNo(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception;
}
