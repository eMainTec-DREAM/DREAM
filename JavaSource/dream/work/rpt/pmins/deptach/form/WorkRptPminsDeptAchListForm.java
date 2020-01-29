package dream.work.rpt.pmins.deptach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;

/**
 * �������� ������(�μ�)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsDeptAchListForm"
 */
public class WorkRptPminsDeptAchListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO = new WorkRptPminsDeptAchCommonDTO();
    
    private WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO = new WorkRptPminsDeptAchDetailDTO();
    
    public WorkRptPminsDeptAchCommonDTO getWorkRptPminsDeptAchCommonDTO()
    {
        return workRptPminsDeptAchCommonDTO;
    }

    public void setWorkRptPminsDeptAchCommonDTO(
            WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO)
    {
        this.workRptPminsDeptAchCommonDTO = workRptPminsDeptAchCommonDTO;
    }
    
    public WorkRptPminsDeptAchDetailDTO getWorkRptPminsDeptAchDetailDTO()
    {
        return workRptPminsDeptAchDetailDTO;
    }

    public void setWorkRptPminsDeptAchDetailDTO(
            WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO)
    {
        this.workRptPminsDeptAchDetailDTO = workRptPminsDeptAchDetailDTO;
    }
	
}
