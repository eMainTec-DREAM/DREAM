package dream.work.rpt.pmins.ach.form;

import common.struts.BaseForm;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;

/**
 * 예방점검 이행율(담당자) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPminsAchDetailForm"
 */
public class WorkRptPminsAchDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO = new WorkRptPminsAchCommonDTO();
    
    private WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO = new WorkRptPminsAchDetailDTO();
    
    public WorkRptPminsAchCommonDTO getWorkRptPminsAchCommonDTO()
    {
        return workRptPminsAchCommonDTO;
    }

    public void setWorkRptPminsAchCommonDTO(
            WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO)
    {
        this.workRptPminsAchCommonDTO = workRptPminsAchCommonDTO;
    }
    
    public WorkRptPminsAchDetailDTO getWorkRptPminsAchDetailDTO()
    {
        return workRptPminsAchDetailDTO;
    }

    public void setWorkRptPminsAchDetailDTO(
            WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO)
    {
        this.workRptPminsAchDetailDTO = workRptPminsAchDetailDTO;
    }
	
}
