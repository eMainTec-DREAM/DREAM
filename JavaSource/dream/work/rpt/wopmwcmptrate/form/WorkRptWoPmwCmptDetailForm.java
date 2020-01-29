package dream.work.rpt.wopmwcmptrate.form;

import common.struts.BaseForm;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;

/**
 * 예방작업 계획대비 실행 비율 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptWoPmwCmptDetailForm"
 */
public class WorkRptWoPmwCmptDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO = new WorkRptWoPmwCmptRateCommonDTO();
    
    private WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO = new WorkRptWoPmwCmptDetailDTO();
    
    public WorkRptWoPmwCmptRateCommonDTO getWorkRptWoPmwCmptRateCommonDTO()
    {
        return workRptWoPmwCmptRateCommonDTO;
    }

    public void setWorkRptWoPmwCmptRateCommonDTO(
            WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO)
    {
        this.workRptWoPmwCmptRateCommonDTO = workRptWoPmwCmptRateCommonDTO;
    }
    
    public WorkRptWoPmwCmptDetailDTO getWorkRptWoPmwCmptDetailDTO()
    {
        return workRptWoPmwCmptDetailDTO;
    }

    public void setWorkRptWoPmwCmptDetailDTO(
            WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO)
    {
        this.workRptWoPmwCmptDetailDTO = workRptWoPmwCmptDetailDTO;
    }
	
}
