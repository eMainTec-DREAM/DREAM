package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;


/**
 * WorkPmiDInsPoint Page - List Form
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiDInsPointListForm"
 * */
public class WorkPmiDInsPointListForm extends BaseForm {
    
    private WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
    
    public WorkPmiDInsCommonDTO getWorkPmiDInsCommonDTO()
    {
        return workPmiDInsCommonDTO;
    }

    public void setWorkPmiDInsCommonDTO(WorkPmiDInsCommonDTO workPmiDInsCommonDTO)
    {
        this.workPmiDInsCommonDTO = workPmiDInsCommonDTO;
    }
}