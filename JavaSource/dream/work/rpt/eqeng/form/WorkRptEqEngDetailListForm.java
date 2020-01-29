package dream.work.rpt.eqeng.form;

import common.struts.BaseForm;
import dream.work.rpt.eqeng.dto.WorkRptEqEngCommonDTO;
import dream.work.rpt.eqeng.dto.WorkRptEqEngDetailListDTO;

/**
 * 에너지사용량(설비별) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptEqEngDetailListForm"
 */
public class WorkRptEqEngDetailListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkRptEqEngCommonDTO workRptEqEngCommonDTO = new WorkRptEqEngCommonDTO();
    
    private WorkRptEqEngDetailListDTO workRptEqEngDetailListDTO = new WorkRptEqEngDetailListDTO();
    
    public WorkRptEqEngCommonDTO getWorkRptEqEngCommonDTO()
    {
        return workRptEqEngCommonDTO;
    }

    public void setWorkRptEqEngCommonDTO(
            WorkRptEqEngCommonDTO workRptEqEngCommonDTO)
    {
        this.workRptEqEngCommonDTO = workRptEqEngCommonDTO;
    }
    
    public WorkRptEqEngDetailListDTO getWorkRptEqEngDetailListDTO()
    {
        return workRptEqEngDetailListDTO;
    }

    public void setWorkRptEqEngDetailListDTO(
            WorkRptEqEngDetailListDTO workRptEqEngDetailListDTO)
    {
        this.workRptEqEngDetailListDTO = workRptEqEngDetailListDTO;
    }
	
}
