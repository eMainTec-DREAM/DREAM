package dream.consult.program.error.service.spring;

import common.bean.User;
import dream.consult.program.error.dao.ConsultPgmErrorDetailDAO;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;
import dream.consult.program.error.service.ConsultPgmErrorDetailService;

/**
 * Error Page - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmErrorDetailServiceTarget"
 * @spring.txbn id="consultPgmErrorDetailService"
 * @spring.property name="consultPgmErrorDetailDAO" ref="consultPgmErrorDetailDAO"
 */
public class ConsultPgmErrorDetailServiceImpl implements ConsultPgmErrorDetailService
{
    private ConsultPgmErrorDetailDAO consultPgmErrorDetailDAO = null;
    
    public ConsultPgmErrorDetailDTO findPgmErrorDetail(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception
    {
    	return consultPgmErrorDetailDAO.findPgmErrorDetail(consultPgmErrorCommonDTO, user);
    }
    
    public int updatePgmErrorDetail(ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO, User user) throws Exception
    {
    	 return consultPgmErrorDetailDAO.updatePgmErrorDetail(consultPgmErrorDetailDTO, user);
    }

	public ConsultPgmErrorDetailDAO getConsultPgmErrorDetailDAO() {
		return consultPgmErrorDetailDAO;
	}

	public void setConsultPgmErrorDetailDAO(ConsultPgmErrorDetailDAO consultPgmErrorDetailDAO) {
		this.consultPgmErrorDetailDAO = consultPgmErrorDetailDAO;
	}
}
