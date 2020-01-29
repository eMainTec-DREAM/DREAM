package dream.work.list.dept.sched.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dao.WorkListDeptSchedListDeptListDAO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;
import dream.work.list.dept.sched.list.service.WorkListDeptSchedListDeptListService;

/**
 * 업체별 작업스케줄탭부서별 작업
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workListDeptSchedListDeptListServiceTarget"
 * @spring.txbn id="workListDeptSchedListDeptListService"
 * @spring.property name="workListDeptSchedListDeptListDAO" ref="workListDeptSchedListDeptListDAO"
 */
public class WorkListDeptSchedListDeptListServiceImpl implements WorkListDeptSchedListDeptListService
{
    private WorkListDeptSchedListDeptListDAO workListDeptSchedListDeptListDAO = null;
    
    public WorkListDeptSchedListDeptListDAO getWorkListDeptSchedListDeptListDAO()
    {
        return workListDeptSchedListDeptListDAO;
    }
    
    public void setWorkListDeptSchedListDeptListDAO(
            WorkListDeptSchedListDeptListDAO workListDeptSchedListDeptListDAO)
    {
        this.workListDeptSchedListDeptListDAO = workListDeptSchedListDeptListDAO;
    }
    
    @Override
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user)
    {
        return workListDeptSchedListDeptListDAO.findList(workListDeptSchedCommonDTO, workListDeptSchedListDeptListDTO, user);
        
    }

    @Override
    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user)
    {
        return workListDeptSchedListDeptListDAO.findTotalCount(workListDeptSchedCommonDTO, workListDeptSchedListDeptListDTO, user);
    }
	
}

