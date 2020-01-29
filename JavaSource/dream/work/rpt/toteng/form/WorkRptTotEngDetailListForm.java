package dream.work.rpt.toteng.form;

import common.struts.BaseForm;
import dream.work.rpt.toteng.dto.WorkRptTotEngCommonDTO;
import dream.work.rpt.toteng.dto.WorkRptTotEngDetailListDTO;

/**
 * 에너지사용량(집계) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptTotEngDetailListForm"
 */
public class WorkRptTotEngDetailListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptTotEngCommonDTO workRptTotEngCommonDTO = new WorkRptTotEngCommonDTO();
    
    private WorkRptTotEngDetailListDTO workRptTotEngDetailListDTO = new WorkRptTotEngDetailListDTO();
    
    public WorkRptTotEngCommonDTO getWorkRptTotEngCommonDTO()
    {
        return workRptTotEngCommonDTO;
    }

    public void setWorkRptTotEngCommonDTO(
            WorkRptTotEngCommonDTO workRptTotEngCommonDTO)
    {
        this.workRptTotEngCommonDTO = workRptTotEngCommonDTO;
    }
    
    public WorkRptTotEngDetailListDTO getWorkRptTotEngDetailListDTO()
    {
        return workRptTotEngDetailListDTO;
    }

    public void setWorkRptTotEngDetailListDTO(
            WorkRptTotEngDetailListDTO workRptTotEngDetailListDTO)
    {
        this.workRptTotEngDetailListDTO = workRptTotEngDetailListDTO;
    }
	
}
