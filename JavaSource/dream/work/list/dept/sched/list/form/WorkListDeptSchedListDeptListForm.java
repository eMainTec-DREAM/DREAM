package dream.work.list.dept.sched.list.form;

import common.struts.BaseForm;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;

/**
 * ��ü�� �۾��������Ǻμ��� �۾�
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workListDeptSchedListDeptListForm"
 */
public class WorkListDeptSchedListDeptListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
