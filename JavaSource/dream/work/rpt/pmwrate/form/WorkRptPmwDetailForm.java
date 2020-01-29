package dream.work.rpt.pmwrate.form;

import common.struts.BaseForm;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;

/**
 * 주기정비 실행 비율 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptPmwDetailForm"
 */
public class WorkRptPmwDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO = new WorkRptPmwCmptRateCommonDTO();
    
    private WorkRptPmwDetailDTO workRptPmwDetailDTO = new WorkRptPmwDetailDTO();
    
    public WorkRptPmwCmptRateCommonDTO getWorkRptPmwCmptRateCommonDTO()
    {
        return workRptPmwCmptRateCommonDTO;
    }

    public void setWorkRptPmwCmptRateCommonDTO(
            WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO)
    {
        this.workRptPmwCmptRateCommonDTO = workRptPmwCmptRateCommonDTO;
    }
    
    public WorkRptPmwDetailDTO getWorkRptPmwDetailDTO()
    {
        return workRptPmwDetailDTO;
    }

    public void setWorkRptPmwDetailDTO(
            WorkRptPmwDetailDTO workRptPmwDetailDTO)
    {
        this.workRptPmwDetailDTO = workRptPmwDetailDTO;
    }
	
}
