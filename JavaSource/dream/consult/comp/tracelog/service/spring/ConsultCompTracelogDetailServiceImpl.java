package dream.consult.comp.tracelog.service.spring;

import common.bean.User;
import dream.consult.comp.tracelog.dao.ConsultCompTracelogDetailDAO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;
import dream.consult.comp.tracelog.service.ConsultCompTracelogDetailService;

/**
 * Screen Trace - Detail Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="consultCompTracelogDetailServiceTarget"
 * @spring.txbn id="consultCompTracelogDetailService"
 * @spring.property name="consultCompTracelogDetailDAO" ref="consultCompTracelogDetailDAO"
 */
public class ConsultCompTracelogDetailServiceImpl implements ConsultCompTracelogDetailService
{
    private ConsultCompTracelogDetailDAO consultCompTracelogDetailDAO = null;
    
    public ConsultCompTracelogDetailDTO findCompTracelogDetail(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    {
    	return consultCompTracelogDetailDAO.findCompTracelogDetail(consultCompTracelogCommonDTO, user);
    }
    
    public int insertCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception
    {
    	return consultCompTracelogDetailDAO.insertCompTracelogDetail(consultCompTracelogDetailDTO, user);
    }
    
    public int updateCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception
    {
    	 return consultCompTracelogDetailDAO.updateCompTracelogDetail(consultCompTracelogDetailDTO, user);
    }

	public ConsultCompTracelogDetailDAO getConsultCompTracelogDetailDAO() {
		return consultCompTracelogDetailDAO;
	}

	public void setConsultCompTracelogDetailDAO(ConsultCompTracelogDetailDAO consultCompTracelogDetailDAO) {
		this.consultCompTracelogDetailDAO = consultCompTracelogDetailDAO;
	}
}
