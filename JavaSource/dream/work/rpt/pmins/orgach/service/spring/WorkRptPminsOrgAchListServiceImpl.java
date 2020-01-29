package dream.work.rpt.pmins.orgach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.orgach.dao.WorkRptPminsOrgAchListDAO;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchCommonDTO;
import dream.work.rpt.pmins.orgach.service.WorkRptPminsOrgAchListService;

/**
 * 예방점검 이행율(조직) 목록
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsOrgAchListServiceTarget"
 * @spring.txbn id="workRptPminsOrgAchListService"
 * @spring.property name="workRptPminsOrgAchListDAO" ref="workRptPminsOrgAchListDAO"
 */
public class WorkRptPminsOrgAchListServiceImpl implements WorkRptPminsOrgAchListService
{
    private WorkRptPminsOrgAchListDAO workRptPminsOrgAchListDAO = null;
    
	public WorkRptPminsOrgAchListDAO getWorkRptPminsOrgAchListDAO()
    {
        return workRptPminsOrgAchListDAO;
    }
	
    public void setWorkRptPminsOrgAchListDAO(
            WorkRptPminsOrgAchListDAO workRptPminsOrgAchListDAO)
    {
        this.workRptPminsOrgAchListDAO = workRptPminsOrgAchListDAO;
    }
    
    public List findList(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User loginUser)
    {
        return workRptPminsOrgAchListDAO.findList(workRptPminsOrgAchCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptPminsOrgAchCommonDTO workRptPminsOrgAchCommonDTO, User user)
    {
        return workRptPminsOrgAchListDAO.findTotalCount(workRptPminsOrgAchCommonDTO, user);
    }
	
}

