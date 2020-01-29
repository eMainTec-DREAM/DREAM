package dream.work.rpt.pmwrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;


/**
 * 주기정비 실행 비율 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmwRateListForm"
 * */
public class WorkRptPmwRateListForm extends BaseForm {
    
    private WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO = new WorkRptPmwRateCommonDTO();
    
    private WorkRptPmwDetailDTO workRptPmwDetailDTO = new WorkRptPmwDetailDTO();

    public WorkRptPmwRateCommonDTO getWorkRptPmwRateCommonDTO() {
        return workRptPmwRateCommonDTO;
    }

    public void setWorkRptPmwRateCommonDTO(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO) {
        this.workRptPmwRateCommonDTO = workRptPmwRateCommonDTO;
    }

    public WorkRptPmwDetailDTO getWorkRptPmwDetailDTO()
    {
        return workRptPmwDetailDTO;
    }

    public void setWorkRptPmwDetailDTO(WorkRptPmwDetailDTO workRptPmwDetailDTO)
    {
        this.workRptPmwDetailDTO = workRptPmwDetailDTO;
    }

}