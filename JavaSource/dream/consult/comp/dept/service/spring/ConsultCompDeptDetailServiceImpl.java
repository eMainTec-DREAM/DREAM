package dream.consult.comp.dept.service.spring;


import dream.consult.comp.dept.dao.ConsultCompDeptDetailDAO;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;
import dream.consult.comp.dept.service.ConsultCompDeptDetailService;

/**
 * 보전부서 - 상세 serviceimpl 
 * @author  hyosung
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="consultCompDeptDetailServiceTarget"
 * @spring.txbn id="consultCompDeptDetailService"
 * @spring.property name="consultCompDeptDetailDAO" ref="consultCompDeptDetailDAO"
 */
public class ConsultCompDeptDetailServiceImpl implements ConsultCompDeptDetailService
{
    private ConsultCompDeptDetailDAO consultCompDeptDetailDAO = null;
    
    public ConsultCompDeptDetailDAO getConsultCompDeptDetailDAO() {
        return consultCompDeptDetailDAO;
    }

    public void setConsultCompDeptDetailDAO(ConsultCompDeptDetailDAO consultCompDeptDetailDAO) {
        this.consultCompDeptDetailDAO = consultCompDeptDetailDAO;
    }

    public ConsultCompDeptDetailDTO findDetail(ConsultCompDeptCommonDTO consultCompDeptCommonDTO,String langId)throws Exception
    {
        return consultCompDeptDetailDAO.findDetail(consultCompDeptCommonDTO,langId);
    }
    
    public int insertDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception
    {        
        
        return consultCompDeptDetailDAO.insertDetail(consultCompDeptDetailDTO);
    }
    
    public int updateDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception
    {   
        
        return consultCompDeptDetailDAO.updateDetail(consultCompDeptDetailDTO);
    }
    
    public String validDeptNo(ConsultCompDeptDetailDTO consultCompDeptDetailDTO) throws Exception
    {
        return consultCompDeptDetailDAO.validDeptNo(consultCompDeptDetailDTO);
    }
}
