package dream.work.rpt.pmi.point.value.form;

import common.struts.BaseForm;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;

/**
 * 정기점검항목결과 - 목록 Form
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueListForm.java, v1.0 2019/07/10 10:37:09 nhkim8548 Exp $
 * @since   1.0
 *
 * @struts.form name="workRptPmiPointValueListForm"
 */
public class WorkRptPmiPointValueListForm extends BaseForm
{    
    //===============================================================
    /** 정기점검항목결과  */
    private WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO = new WorkRptPmiPointValueCommonDTO();
    //===============================================================

    public WorkRptPmiPointValueCommonDTO getWorkRptPmiPointValueCommonDTO()
    {
        return workRptPmiPointValueCommonDTO;
    }

    public void setWorkRptPmiPointValueCommonDTO(
            WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO)
    {
        this.workRptPmiPointValueCommonDTO = workRptPmiPointValueCommonDTO;
    }
}
