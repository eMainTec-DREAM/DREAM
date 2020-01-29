package dream.consult.program.dashboard.service.spring;

import common.bean.User;
import dream.consult.program.dashboard.dao.ConsultPgmDashboardDetailDAO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardDetailDTO;
import dream.consult.program.dashboard.service.ConsultPgmDashboardDetailService;

/**
 * 대시보드 Contents - 상세 serviceimpl
 * @author  kim21017
 * @version $Id: ConsultPgmDashboardDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="consultPgmDashboardDetailServiceTarget"
 * @spring.txbn id="consultPgmDashboardDetailService"
 * @spring.property name="consultPgmDashboardDetailDAO" ref="consultPgmDashboardDetailDAO"
 */
public class ConsultPgmDashboardDetailServiceImpl implements ConsultPgmDashboardDetailService
{
    private ConsultPgmDashboardDetailDAO consultPgmDashboardDetailDAO = null;

    public ConsultPgmDashboardDetailDAO getConsultPgmDashboardDetailDAO() {
		return consultPgmDashboardDetailDAO;
	}

	public void setConsultPgmDashboardDetailDAO(ConsultPgmDashboardDetailDAO consultPgmDashboardDetailDAO) {
		this.consultPgmDashboardDetailDAO = consultPgmDashboardDetailDAO;
	}

	public ConsultPgmDashboardDetailDTO findDetail(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)throws Exception
    {
        return consultPgmDashboardDetailDAO.findDetail(consultPgmDashboardCommonDTO, user);
    }

	public int insertDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user) throws Exception
    {
        return consultPgmDashboardDetailDAO.insertDetail(consultPgmDashboardDetailDTO, user);
    }

	public int updateDetail(ConsultPgmDashboardDetailDTO consultPgmDashboardDetailDTO, User user) throws Exception
    {
        return consultPgmDashboardDetailDAO.updateDetail(consultPgmDashboardDetailDTO, user);
    }
}
