package dream.consult.comp.emp.service.spring;

import common.bean.User;
import dream.consult.comp.emp.dao.ConsultCompEmpDetailDAO;
import dream.consult.comp.emp.dto.ConsultCompEmpCommonDTO;
import dream.consult.comp.emp.dto.ConsultCompEmpDetailDTO;
import dream.consult.comp.emp.service.ConsultCompEmpDetailService;

/**
 * 사원 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="consultCompEmpDetailServiceTarget"
 * @spring.txbn id="consultCompEmpDetailService"
 * @spring.property name="consultCompEmpDetailDAO" ref="consultCompEmpDetailDAO"
 */
public class ConsultCompEmpDetailServiceImpl implements ConsultCompEmpDetailService
{
    private ConsultCompEmpDetailDAO consultCompEmpDetailDAO = null;
    
    public ConsultCompEmpDetailDAO getConsultCompEmpDetailDAO() 
    {
		return consultCompEmpDetailDAO;
	}

	public void setConsultCompEmpDetailDAO(ConsultCompEmpDetailDAO consultCompEmpDetailDAO) 
	{
		this.consultCompEmpDetailDAO = consultCompEmpDetailDAO;
	}

	public ConsultCompEmpDetailDTO findDetail(User user, ConsultCompEmpCommonDTO consultCompEmpCommonDTO)throws Exception
    {
        return consultCompEmpDetailDAO.findDetail(user, consultCompEmpCommonDTO);
    }
    
	public int insertDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception
    {        
        return consultCompEmpDetailDAO.insertDetail(consultCompEmpDetailDTO);
    }
	
	public int updateDetail(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception
    {        
        return consultCompEmpDetailDAO.updateDetail(consultCompEmpDetailDTO);
    }
	
	public String validEmpNo(ConsultCompEmpDetailDTO consultCompEmpDetailDTO) throws Exception
    {
        return consultCompEmpDetailDAO.validEmpNo(consultCompEmpDetailDTO);
    }
}
