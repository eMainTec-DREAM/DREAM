package dream.work.rpt.pmins.orgach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dao.WorkRptPminsOrgAchDetailDAO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;
import dream.work.rpt.pmins.orgach.service.WorkRptPminsOrgAchDetailService;

/**
 * �������� ������(����) �� ���
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsOrgAchDetailServiceTarget"
 * @spring.txbn id="workRptPminsOrgAchDetailService"
 * @spring.property name="workRptPminsOrgAchDetailDAO" ref="workRptPminsOrgAchDetailDAO"
 */
public class WorkRptPminsOrgAchDetailServiceImpl implements WorkRptPminsOrgAchDetailService
{
    private WorkRptPminsOrgAchDetailDAO workRptPminsOrgAchDetailDAO = null;
    
    public WorkRptPminsOrgAchDetailDAO getWorkRptPminsOrgAchDetailDAO()
    {
        return workRptPminsOrgAchDetailDAO;
    }
    
    public void setWorkRptPminsOrgAchDetailDAO(
            WorkRptPminsOrgAchDetailDAO workRptPminsOrgAchDetailDAO)
    {
        this.workRptPminsOrgAchDetailDAO = workRptPminsOrgAchDetailDAO;
    }
    
    public List findDetail(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser)
    {
        return workRptPminsOrgAchDetailDAO.findDetail(workRptPminsOrgAchDetailDTO, loginUser);
        
    }

	public String findTotalCount(WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO, User loginUser) 
	{
		return workRptPminsOrgAchDetailDAO.findTotalCount(workRptPminsOrgAchDetailDTO, loginUser);
	}
	
}

