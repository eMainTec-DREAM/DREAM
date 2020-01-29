package dream.work.list.dept.sched.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;

/**
 * 업체별 작업스케줄탭부서별 작업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkListDeptSchedListDeptListService
{     
	/**
	 * grid find
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param workListDeptSchedListDeptListDTO
	 * @param user
	 * @return
	 */
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user);
    
    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user);
    
}
