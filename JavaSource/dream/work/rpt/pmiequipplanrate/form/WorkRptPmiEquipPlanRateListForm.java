package dream.work.rpt.pmiequipplanrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;


/**
 * �������� ���� ��ȹ��� ���� ���� ��� - List Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="workRptPmiEquipPlanRateListForm"
 * */
public class WorkRptPmiEquipPlanRateListForm extends BaseForm {
    
    private WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO = new WorkRptPmiEquipPlanRateCommonDTO();

    private WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO = new WorkRptPmiEquipPlanDetailDTO();
    
    public WorkRptPmiEquipPlanRateCommonDTO getWorkRptPmiEquipPlanRateCommonDTO() {
        return workRptPmiEquipPlanRateCommonDTO;
    }

    public void setWorkRptPmiEquipPlanRateCommonDTO(WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO) {
        this.workRptPmiEquipPlanRateCommonDTO = workRptPmiEquipPlanRateCommonDTO;
    }

    public WorkRptPmiEquipPlanDetailDTO getWorkRptPmiEquipPlanDetailDTO()
    {
        return workRptPmiEquipPlanDetailDTO;
    }

    public void setWorkRptPmiEquipPlanDetailDTO(
            WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO)
    {
        this.workRptPmiEquipPlanDetailDTO = workRptPmiEquipPlanDetailDTO;
    }

}