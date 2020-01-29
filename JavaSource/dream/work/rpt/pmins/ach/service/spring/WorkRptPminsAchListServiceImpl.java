package dream.work.rpt.pmins.ach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.ach.dao.WorkRptPminsAchListDAO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;
import dream.work.rpt.pmins.ach.service.WorkRptPminsAchListService;

/**
 * 예방점검 이행율(담당자) 목록
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsAchListServiceTarget"
 * @spring.txbn id="workRptPminsAchListService"
 * @spring.property name="workRptPminsAchListDAO" ref="workRptPminsAchListDAO"
 */
public class WorkRptPminsAchListServiceImpl implements WorkRptPminsAchListService
{
    private WorkRptPminsAchListDAO workRptPminsAchListDAO = null;
    
	public WorkRptPminsAchListDAO getWorkRptPminsAchListDAO()
    {
        return workRptPminsAchListDAO;
    }
	
    public void setWorkRptPminsAchListDAO(
            WorkRptPminsAchListDAO workRptPminsAchListDAO)
    {
        this.workRptPminsAchListDAO = workRptPminsAchListDAO;
    }
    
    public List findList(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User loginUser)
    {
        return workRptPminsAchListDAO.findList(workRptPminsAchCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO, User user)
    {
        return workRptPminsAchListDAO.findTotalCount(workRptPminsAchCommonDTO, user);
    }
	
}

