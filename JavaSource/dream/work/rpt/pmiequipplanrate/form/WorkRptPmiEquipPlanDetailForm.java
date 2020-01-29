package dream.work.rpt.pmiequipplanrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;

/**
 * 고장TOP(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPmiEquipPlanDetailForm"
 */
public class WorkRptPmiEquipPlanDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
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
