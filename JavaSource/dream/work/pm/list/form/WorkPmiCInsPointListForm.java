package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;


/**
 * WorkPmiCInsPoint Page - List Form
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiCInsPointListForm"
 * */
public class WorkPmiCInsPointListForm extends BaseForm {
    
    private WorkPmiCInsCommonDTO workPmiCInsCommonDTO = new WorkPmiCInsCommonDTO();
    
    public WorkPmiCInsCommonDTO getWorkPmiCInsCommonDTO()
    {
        return workPmiCInsCommonDTO;
    }

    public void setWorkPmiCInsCommonDTO(WorkPmiCInsCommonDTO workPmiCInsCommonDTO)
    {
        this.workPmiCInsCommonDTO = workPmiCInsCommonDTO;
    }
}