package dream.work.rpt.pmicmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;

/**
 * ����TOP(��ġ) ��
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPmiCmptDetailForm"
 */
public class WorkRptPmiCmptDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO = new WorkRptPmiCmptRateCommonDTO();
    
    private WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO = new WorkRptPmiCmptDetailDTO();
    
    public WorkRptPmiCmptRateCommonDTO getWorkRptPmiCmptRateCommonDTO()
    {
        return workRptPmiCmptRateCommonDTO;
    }

    public void setWorkRptPmiCmptRateCommonDTO(
            WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO)
    {
        this.workRptPmiCmptRateCommonDTO = workRptPmiCmptRateCommonDTO;
    }
    
    public WorkRptPmiCmptDetailDTO getWorkRptPmiCmptDetailDTO()
    {
        return workRptPmiCmptDetailDTO;
    }

    public void setWorkRptPmiCmptDetailDTO(
            WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO)
    {
        this.workRptPmiCmptDetailDTO = workRptPmiCmptDetailDTO;
    }
	
}
