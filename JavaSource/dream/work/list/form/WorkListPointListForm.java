package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * WorkListPoint Page - List Form
 * @author youngjoo38
 * @version $Id: WorkListPointListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workListPointListForm"
 * */
public class WorkListPointListForm extends BaseForm {
    
    private WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = new WorkListPtrlResultCommonDTO();
    private WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = new WorkListPtrlResultMstrDetailDTO();
    private WorkListPointListDTO workListPointListDTO = new WorkListPointListDTO();

    
    
    public WorkListPtrlResultMstrDetailDTO getWorkListPtrlResultMstrDetailDTO()
    {
        return workListPtrlResultMstrDetailDTO;
    }

    public void setWorkListPtrlResultMstrDetailDTO(
            WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO)
    {
        this.workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailDTO;
    }

    public WorkListPtrlResultCommonDTO getWorkListPtrlResultCommonDTO()
    {
        return workListPtrlResultCommonDTO;
    }

    public void setWorkListPtrlResultCommonDTO(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO)
    {
        this.workListPtrlResultCommonDTO = workListPtrlResultCommonDTO;
    }

    public WorkListPointListDTO getWorkListPointListDTO() {
        return workListPointListDTO;
    }

    public void setWorkListPointListDTO(WorkListPointListDTO workListPointListDTO) {
        this.workListPointListDTO = workListPointListDTO;
    }
}