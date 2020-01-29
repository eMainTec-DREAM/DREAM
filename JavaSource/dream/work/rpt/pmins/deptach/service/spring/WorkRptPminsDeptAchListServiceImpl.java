package dream.work.rpt.pmins.deptach.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmins.deptach.dao.WorkRptPminsDeptAchListDAO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;
import dream.work.rpt.pmins.deptach.service.WorkRptPminsDeptAchListService;

/**
 * 예방점검 이행율(부서) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptPminsDeptAchListServiceTarget"
 * @spring.txbn id="workRptPminsDeptAchListService"
 * @spring.property name="workRptPminsDeptAchListDAO" ref="workRptPminsDeptAchListDAO"
 */
public class WorkRptPminsDeptAchListServiceImpl implements WorkRptPminsDeptAchListService
{
    private WorkRptPminsDeptAchListDAO workRptPminsDeptAchListDAO = null;
    
	public WorkRptPminsDeptAchListDAO getWorkRptPminsDeptAchListDAO()
    {
        return workRptPminsDeptAchListDAO;
    }
	
    public void setWorkRptPminsDeptAchListDAO(
            WorkRptPminsDeptAchListDAO workRptPminsDeptAchListDAO)
    {
        this.workRptPminsDeptAchListDAO = workRptPminsDeptAchListDAO;
    }
    
    public List findList(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User loginUser)
    {
        return workRptPminsDeptAchListDAO.findList(workRptPminsDeptAchCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO, User user)
    {
        return workRptPminsDeptAchListDAO.findTotalCount(workRptPminsDeptAchCommonDTO, user);
    }
	
}

