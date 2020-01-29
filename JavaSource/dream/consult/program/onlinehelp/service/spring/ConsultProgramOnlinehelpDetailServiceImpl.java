package dream.consult.program.onlinehelp.service.spring;

import common.bean.User;
import dream.consult.program.onlinehelp.dao.ConsultProgramOnlinehelpDetailDAO;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpDetailDTO;
import dream.consult.program.onlinehelp.service.ConsultProgramOnlinehelpDetailService;

/**
 * 도움말 - 상세 serviceimpl 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="consultProgramOnlinehelpDetailServiceTarget"
 * @spring.txbn id="consultProgramOnlinehelpDetailService"
 * @spring.property name="consultProgramOnlinehelpDetailDAO" ref="consultProgramOnlinehelpDetailDAO"
 */
public class ConsultProgramOnlinehelpDetailServiceImpl implements ConsultProgramOnlinehelpDetailService
{
    private ConsultProgramOnlinehelpDetailDAO consultProgramOnlinehelpDetailDAO = null;
    
    public ConsultProgramOnlinehelpDetailDAO getConsultProgramOnlinehelpDetailDAO() {
		return consultProgramOnlinehelpDetailDAO;
	}

	public void setConsultProgramOnlinehelpDetailDAO(ConsultProgramOnlinehelpDetailDAO consultProgramOnlinehelpDetailDAO) {
		this.consultProgramOnlinehelpDetailDAO = consultProgramOnlinehelpDetailDAO;
	}

	public ConsultProgramOnlinehelpDetailDTO findDetail(String id, User user)throws Exception
    {
        return consultProgramOnlinehelpDetailDAO.findDetail(id, user);
    }
	
	public int insertDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO) throws Exception
    {        
        return consultProgramOnlinehelpDetailDAO.insertDetail(consultProgramOnlinehelpDetailDTO);
    }
	
	public int updateDetail(ConsultProgramOnlinehelpDetailDTO consultProgramOnlinehelpDetailDTO) throws Exception
    {        
        return consultProgramOnlinehelpDetailDAO.updateDetail(consultProgramOnlinehelpDetailDTO);
    }
}
