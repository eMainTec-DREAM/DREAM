package dream.work.rpt.woendrate.form;

import common.struts.BaseForm;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;


/**
 * �۾����� �ϸ��� ó���� ��� - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptWoEndDetailListForm"
 * */
public class WorkRptWoEndDetailListForm extends BaseForm {
    
    private WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO = new WorkRptWoEndDetailListDTO();
    private WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO = new WorkRptWoEndRateCommonDTO();

    public WorkRptWoEndDetailListDTO getWorkRptWoEndDetailListDTO() {
        return workRptWoEndDetailListDTO;
    }

    public void setWorkRptWoEndDetailListDTO(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO) {
        this.workRptWoEndDetailListDTO = workRptWoEndDetailListDTO;
    }

	public WorkRptWoEndRateCommonDTO getWorkRptWoEndRateCommonDTO() {
		return workRptWoEndRateCommonDTO;
	}

	public void setWorkRptWoEndRateCommonDTO(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO) {
		this.workRptWoEndRateCommonDTO = workRptWoEndRateCommonDTO;
	}

}