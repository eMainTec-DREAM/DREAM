package dream.work.rpt.woendrate.form;

import common.struts.BaseForm;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;


/**
 * 작업오더 일마감 처리율 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptWoEndRateListForm"
 * */
public class WorkRptWoEndRateListForm extends BaseForm {
    
    private WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO = new WorkRptWoEndRateCommonDTO();
    private WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO = new WorkRptWoEndDetailListDTO();
    
    
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