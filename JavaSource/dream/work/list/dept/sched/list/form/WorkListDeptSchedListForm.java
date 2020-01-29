package dream.work.list.dept.sched.list.form;

import common.struts.BaseForm;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;

/**
 * 업체별 작업스케줄
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workListDeptSchedListForm"
 */
public class WorkListDeptSchedListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO = new WorkListDeptSchedCommonDTO();
    
    
    public WorkListDeptSchedCommonDTO getWorkListDeptSchedCommonDTO()
    {
        return workListDeptSchedCommonDTO;
    }

    public void setWorkListDeptSchedCommonDTO(
            WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO)
    {
        this.workListDeptSchedCommonDTO = workListDeptSchedCommonDTO;
    }
    
	
}
