package dream.work.rpt.wopmwcmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;


/**
 * �����۾� ��ȹ��� ���� ���� ��� - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptWoPmwCmptRateListForm"
 * */
public class WorkRptWoPmwCmptRateListForm extends BaseForm {
    
    private WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO = new WorkRptWoPmwCmptRateCommonDTO();
    
    private WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO = new WorkRptWoPmwCmptDetailDTO();

    public WorkRptWoPmwCmptRateCommonDTO getWorkRptWoPmwCmptRateCommonDTO() {
        return workRptWoPmwCmptRateCommonDTO;
    }

    public void setWorkRptWoPmwCmptRateCommonDTO(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO) {
        this.workRptWoPmwCmptRateCommonDTO = workRptWoPmwCmptRateCommonDTO;
    }

    public WorkRptWoPmwCmptDetailDTO getWorkRptWoPmwCmptDetailDTO()
    {
        return workRptWoPmwCmptDetailDTO;
    }

    public void setWorkRptWoPmwCmptDetailDTO(
            WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO)
    {
        this.workRptWoPmwCmptDetailDTO = workRptWoPmwCmptDetailDTO;
    }

}