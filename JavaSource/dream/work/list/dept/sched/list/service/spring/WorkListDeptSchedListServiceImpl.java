package dream.work.list.dept.sched.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dao.WorkListDeptSchedListDAO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.service.WorkListDeptSchedListService;

/**
 * 업체별 작업스케줄
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workListDeptSchedListServiceTarget"
 * @spring.txbn id="workListDeptSchedListService"
 * @spring.property name="workListDeptSchedListDAO" ref="workListDeptSchedListDAO"
 */
public class WorkListDeptSchedListServiceImpl implements WorkListDeptSchedListService
{
    private WorkListDeptSchedListDAO workListDeptSchedListDAO = null;
    
	public WorkListDeptSchedListDAO getWorkListDeptSchedListDAO()
    {
        return workListDeptSchedListDAO;
    }
	
    public void setWorkListDeptSchedListDAO(
            WorkListDeptSchedListDAO workListDeptSchedListDAO)
    {
        this.workListDeptSchedListDAO = workListDeptSchedListDAO;
    }
    
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User loginUser)
    {
        return workListDeptSchedListDAO.findList(workListDeptSchedCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User user)
    {
        return workListDeptSchedListDAO.findTotalCount(workListDeptSchedCommonDTO, user);
    }
	
}

