package dream.work.list.dept.sched.list.dto;

import common.bean.BaseDTO;

/**
 * 업체별 작업스케줄탭부서별 작업 DTO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class WorkListDeptSchedListDeptListDTO extends BaseDTO
{
	/** title */
	private String title    	= "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}