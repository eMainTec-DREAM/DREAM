package dream.work.list.dept.sched.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;

/**
 * ��ü�� �۾�������
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkListDeptSchedListService
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
