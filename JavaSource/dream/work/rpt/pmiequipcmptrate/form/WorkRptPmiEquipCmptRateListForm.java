package dream.work.rpt.pmiequipcmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;


/**
 * 예방점검설비 실행 비율 목록 - List Form
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmiEquipCmptRateListForm"
 * */
public class WorkRptPmiEquipCmptRateListForm extends BaseForm {
    
    private WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO = new WorkRptPmiEquipCmptRateCommonDTO();
    
    private WorkRptPmiEquipCmptDetailListDTO WorkRptPmiEquipCmptDetailListDTO = new WorkRptPmiEquipCmptDetailListDTO();

    public WorkRptPmiEquipCmptRateCommonDTO getWorkRptPmiEquipCmptRateCommonDTO() {
        return workRptPmiEquipCmptRateCommonDTO;
    }

    public void setWorkRptPmiEquipCmptRateCommonDTO(WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO) {
        this.workRptPmiEquipCmptRateCommonDTO = workRptPmiEquipCmptRateCommonDTO;
    }

	public WorkRptPmiEquipCmptDetailListDTO getWorkRptPmiEquipCmptDetailListDTO() {
		return WorkRptPmiEquipCmptDetailListDTO;
	}

	public void setWorkRptPmiEquipCmptDetailListDTO(WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO) {
		WorkRptPmiEquipCmptDetailListDTO = workRptPmiEquipCmptDetailListDTO;
	}

}