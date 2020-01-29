package dream.work.list.dept.sched.list.form;

import common.struts.BaseForm;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;

/**
 * 업체별 작업스케줄탭부서별 작업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workListDeptSchedListDeptListForm"
 */
public class WorkListDeptSchedListDeptListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO = new WorkListDeptSchedCommonDTO();
    
    private WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO = new WorkListDeptSchedListDeptListDTO();
    
    public WorkListDeptSchedCommonDTO getWorkListDeptSchedCommonDTO()
    {
        return workListDeptSchedCommonDTO;
    }

    public void setWorkListDeptSchedCommonDTO(
            WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO)
    {
        this.workListDeptSchedCommonDTO = workListDeptSchedCommonDTO;
    }
    
    public WorkListDeptSchedListDeptListDTO getWorkListDeptSchedListDeptListDTO()
    {
        return workListDeptSchedListDeptListDTO;
    }

    public void setWorkListDeptSchedListDeptListDTO(
            WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO)
    {
        this.workListDeptSchedListDeptListDTO = workListDeptSchedListDeptListDTO;
    }
	
}
