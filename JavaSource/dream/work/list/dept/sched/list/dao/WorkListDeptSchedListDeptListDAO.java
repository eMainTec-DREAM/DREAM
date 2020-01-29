package dream.work.list.dept.sched.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;

/**
 * ��ü�� �۾��������Ǻμ��� �۾� DAO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkListDeptSchedListDeptListDAO
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