package dream.work.rpt.pmiequipplanrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;

/**
 * ����TOP(��ġ) ��
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPmiEquipPlanDetailForm"
 */
public class WorkRptPmiEquipPlanDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO = new WorkRptPmwCmptRateCommonDTO();
    
    private WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO = new WorkRptPmiEquipPlanDetailDTO();
    
    public WorkRptPmwCmptRateCommonDTO getWorkRptPmwCmptRateCommonDTO()
    {
        return workRptPmwCmptRateCommonDTO;
    }

    public void setWorkRptPmwCmptRateCommonDTO(
            WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO)
    {
        this.workRptPmwCmptRateCommonDTO = workRptPmwCmptRateCommonDTO;
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
