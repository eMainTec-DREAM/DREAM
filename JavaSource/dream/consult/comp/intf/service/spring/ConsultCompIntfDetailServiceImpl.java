package dream.consult.comp.intf.service.spring;

import common.bean.User;
import dream.consult.comp.intf.dao.ConsultCompIntfDetailDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;
import dream.consult.comp.intf.service.ConsultCompIntfDetailService;

/**
 * Interface Page - Detail Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompIntfDetailServiceTarget"
 * @spring.txbn id="consultCompIntfDetailService"
 * @spring.property name="consultCompIntfDetailDAO" ref="consultCompIntfDetailDAO"
 */
public class ConsultCompIntfDetailServiceImpl implements ConsultCompIntfDetailService
{
    private ConsultCompIntfDetailDAO consultCompIntfDetailDAO = null;
    
    public ConsultCompIntfDetailDTO findDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception
    {
    	return consultCompIntfDetailDAO.findDetail(consultCompIntfCommonDTO, user);
    }
    
    public int insertDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception
    {
    	return consultCompIntfDetailDAO.insertDetail(consultCompIntfDetailDTO, user);
    }
    
    public int updateDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception
    {
    	 return consultCompIntfDetailDAO.updateDetail(consultCompIntfDetailDTO, user);
    }

	public ConsultCompIntfDetailDAO getConsultCompIntfDetailDAO() {
		return consultCompIntfDetailDAO;
	}

	public void setConsultCompIntfDetailDAO(ConsultCompIntfDetailDAO consultCompIntfDetailDAO) {
		this.consultCompIntfDetailDAO = consultCompIntfDetailDAO;
	}
}
