package dream.work.rpt.pmwcmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;


/**
 * �ֱ����� ��ȹ��� ���� ���� ��� - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmwCmptRateListForm"
 * */
public class WorkRptPmwCmptRateListForm extends BaseForm {
    
    private WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO = new WorkRptPmwCmptRateCommonDTO();
    
    private WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO = new WorkRptPmwCmptDetailDTO();

    public WorkRptPmwCmptRateCommonDTO getWorkRptPmwCmptRateCommonDTO() {
        return workRptPmwCmptRateCommonDTO;
    }

    public void setWorkRptPmwCmptRateCommonDTO(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO) {
        this.workRptPmwCmptRateCommonDTO = workRptPmwCmptRateCommonDTO;
    }

    public WorkRptPmwCmptDetailDTO getWorkRptPmwCmptDetailDTO()
    {
        return workRptPmwCmptDetailDTO;
    }

    public void setWorkRptPmwCmptDetailDTO(
            WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO)
    {
        this.workRptPmwCmptDetailDTO = workRptPmwCmptDetailDTO;
    }

}