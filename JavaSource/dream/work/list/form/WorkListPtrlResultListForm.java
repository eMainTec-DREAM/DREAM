package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 순회점검 작업결과 목록
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListForm.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 *
 * @struts.form name="workListPtrlResultListForm"
 */
public class WorkListPtrlResultListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = new WorkListPtrlResultCommonDTO();

    public WorkListPtrlResultCommonDTO getWorkListPtrlResultCommonDTO()
    {
        return workListPtrlResultCommonDTO;
    }

    public void setWorkListPtrlResultCommonDTO(
            WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO)
    {
        this.workListPtrlResultCommonDTO = workListPtrlResultCommonDTO;
    }

    
}
