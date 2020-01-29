package dream.work.rpt.maintrate.form;

import common.struts.BaseForm;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateDetailDTO;


/**
 * WorkRptMaintRate Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptMaintRateListForm"
 * */
public class WorkRptMaintRateListForm extends BaseForm {
    
    private WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO = new WorkRptMaintRateCommonDTO();
    private WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO = new WorkRptMaintRateDetailDTO();

    public WorkRptMaintRateCommonDTO getWorkRptMaintRateCommonDTO() {
        return workRptMaintRateCommonDTO;
    }

    public void setWorkRptMaintRateCommonDTO(WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO) {
        this.workRptMaintRateCommonDTO = workRptMaintRateCommonDTO;
    }

    public WorkRptMaintRateDetailDTO getWorkRptMaintRateDetailDTO()
    {
        return workRptMaintRateDetailDTO;
    }

    public void setWorkRptMaintRateDetailDTO(
            WorkRptMaintRateDetailDTO workRptMaintRateDetailDTO)
    {
        this.workRptMaintRateDetailDTO = workRptMaintRateDetailDTO;
    }
    
}