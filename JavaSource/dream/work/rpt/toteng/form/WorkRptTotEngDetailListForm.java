package dream.work.rpt.toteng.form;

import common.struts.BaseForm;
import dream.work.rpt.toteng.dto.WorkRptTotEngCommonDTO;
import dream.work.rpt.toteng.dto.WorkRptTotEngDetailListDTO;

/**
 * ��������뷮(����) ��
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptTotEngDetailListForm"
 */
public class WorkRptTotEngDetailListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
