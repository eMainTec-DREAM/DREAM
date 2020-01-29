package dream.work.rpt.pmiequipcmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;


/**
 * 예방점검 실행 상세 목록 - List Form
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmiEquipCmptDetailListForm"
 * */
public class WorkRptPmiEquipCmptDetailListForm extends BaseForm {
    
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