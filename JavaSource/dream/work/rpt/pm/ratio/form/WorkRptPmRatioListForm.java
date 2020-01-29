package dream.work.rpt.pm.ratio.form;

import common.struts.BaseForm;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;

/**
 * ��ȹ������(��ġ)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPmRatioListForm"
 */
public class WorkRptPmRatioListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO = new WorkRptPmRatioCommonDTO();
    
    private WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO = new WorkRptPmRatioDetailDTO();
    
    public WorkRptPmRatioCommonDTO getWorkRptPmRatioCommonDTO()
    {
        return workRptPmRatioCommonDTO;
    }

    public void setWorkRptPmRatioCommonDTO(
            WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO)
    {
        this.workRptPmRatioCommonDTO = workRptPmRatioCommonDTO;
    }
    
    public WorkRptPmRatioDetailDTO getWorkRptPmRatioDetailDTO()
    {
        return workRptPmRatioDetailDTO;
    }

    public void setWorkRptPmRatioDetailDTO(
            WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO)
    {
        this.workRptPmRatioDetailDTO = workRptPmRatioDetailDTO;
    }
	
}
