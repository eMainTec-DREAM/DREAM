package dream.work.list.dept.sched.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;

/**
 * 업체별 작업스케줄 목록 dao
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkListDeptSchedListDAO
{
	/**
	 * grid find
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param workListDeptSchedCommonDTO
	 * @param user
	 * @return
	 */
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User user);

    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, User user);
    
}