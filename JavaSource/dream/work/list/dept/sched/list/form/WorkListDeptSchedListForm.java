package dream.work.list.dept.sched.list.form;

import common.struts.BaseForm;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;

/**
 * ��ü�� �۾�������
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workListDeptSchedListForm"
 */
public class WorkListDeptSchedListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
