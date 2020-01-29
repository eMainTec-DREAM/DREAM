package dream.work.rpt.pmicmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;


/**
 * 예방점검항목 실행 비율 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmiCmptRateListForm"
 * */
public class WorkRptPmiCmptRateListForm extends BaseForm {
    
    private WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO = new WorkRptPmiCmptRateCommonDTO();

    private WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO = new WorkRptPmiCmptDetailDTO();
    
    public WorkRptPmiCmptRateCommonDTO getWorkRptPmiCmptRateCommonDTO() {
        return workRptPmiCmptRateCommonDTO;
    }

    public void setWorkRptPmiCmptRateCommonDTO(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO) {
        this.workRptPmiCmptRateCommonDTO = workRptPmiCmptRateCommonDTO;
    }

    public WorkRptPmiCmptDetailDTO getWorkRptPmiCmptDetailDTO()
    {
        return workRptPmiCmptDetailDTO;
    }

    public void setWorkRptPmiCmptDetailDTO(
            WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO)
    {
        this.workRptPmiCmptDetailDTO = workRptPmiCmptDetailDTO;
    }

}