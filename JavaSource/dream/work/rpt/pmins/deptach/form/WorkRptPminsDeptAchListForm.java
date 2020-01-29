package dream.work.rpt.pmins.deptach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;

/**
 * 예방점검 이행율(부서)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsDeptAchListForm"
 */
public class WorkRptPminsDeptAchListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
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
