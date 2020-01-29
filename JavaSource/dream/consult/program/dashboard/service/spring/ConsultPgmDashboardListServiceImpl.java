package dream.consult.program.dashboard.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.dashboard.dao.ConsultPgmDashboardListDAO;
import dream.consult.program.dashboard.dto.ConsultPgmDashboardCommonDTO;
import dream.consult.program.dashboard.service.ConsultPgmDashboardListService;

/**
 * 대시보드 Contents - 목록 serviceimpl
 * @author kim21017
 * @version $Id: ConsultPgmDashboardListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="consultPgmDashboardListServiceTarget"
 * @spring.txbn id="consultPgmDashboardListService"
 * @spring.property name="consultPgmDashboardListDAO" ref="consultPgmDashboardListDAO"
 */
public class ConsultPgmDashboardListServiceImpl implements ConsultPgmDashboardListService
{
    private ConsultPgmDashboardListDAO consultPgmDashboardListDAO = null;

    public ConsultPgmDashboardListDAO getConsultPgmDashboardListDAO() {
		return consultPgmDashboardListDAO;
	}

	public void setConsultPgmDashboardListDAO(ConsultPgmDashboardListDAO consultPgmDashboardListDAO) {
		this.consultPgmDashboardListDAO = consultPgmDashboardListDAO;
	}

	public List findList(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)
    {
        return consultPgmDashboardListDAO.findList(consultPgmDashboardCommonDTO, user);
    }
	public String findTotalCount(ConsultPgmDashboardCommonDTO consultPgmDashboardCommonDTO, User user)
    {
        return consultPgmDashboardListDAO.findTotalCount(consultPgmDashboardCommonDTO, user);
    }

	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
        	for(int i=0; i<deleteRows.length; i++){
        		result = result + consultPgmDashboardListDAO.deleteList(deleteRows[i], user);
        		
        	}
        return result;
    }
}

