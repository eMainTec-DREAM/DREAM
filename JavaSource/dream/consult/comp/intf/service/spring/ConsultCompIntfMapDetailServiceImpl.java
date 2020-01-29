package dream.consult.comp.intf.service.spring;

import common.bean.User;
import dream.consult.comp.intf.dao.ConsultCompIntfMapDetailDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapDetailDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;
import dream.consult.comp.intf.service.ConsultCompIntfMapDetailService;

/**
 * Interface Log Page - Detail Service implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompIntfMapDetailServiceTarget"
 * @spring.txbn id="consultCompIntfMapDetailService"
 * @spring.property name="consultCompIntfMapDetailDAO" ref="consultCompIntfMapDetailDAO"
 */
public class ConsultCompIntfMapDetailServiceImpl implements ConsultCompIntfMapDetailService
{
    private ConsultCompIntfMapDetailDAO consultCompIntfMapDetailDAO = null;
    
    public ConsultCompIntfMapDetailDTO findDetail(ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception
    {
    	return consultCompIntfMapDetailDAO.findDetail(consultCompIntfMapListDTO, user);
    }

	public ConsultCompIntfMapDetailDAO getConsultCompIntfMapDetailDAO() {
		return consultCompIntfMapDetailDAO;
	}

	public void setConsultCompIntfMapDetailDAO(ConsultCompIntfMapDetailDAO consultCompIntfMapDetailDAO) {
		this.consultCompIntfMapDetailDAO = consultCompIntfMapDetailDAO;
	}

	public int insertDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO, User user) throws Exception 
	{
		return consultCompIntfMapDetailDAO.insertDetail(consultCompIntfCommonDTO, consultCompIntfMapDetailDTO, user);
	}

	public int updateDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO, User user) throws Exception 
	{
		return consultCompIntfMapDetailDAO.updateDetail(consultCompIntfCommonDTO, consultCompIntfMapDetailDTO, user);
	}
}
