package dream.work.rpt.eqeng.form;

import common.struts.BaseForm;
import dream.work.rpt.eqeng.dto.WorkRptEqEngCommonDTO;
import dream.work.rpt.eqeng.dto.WorkRptEqEngDetailListDTO;

/**
 * ��������뷮(����)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptEqEngListForm"
 */
public class WorkRptEqEngListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
