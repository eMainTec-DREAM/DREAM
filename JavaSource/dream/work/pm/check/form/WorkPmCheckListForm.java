package dream.work.pm.check.form;

import common.struts.BaseForm;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;


/**
 * WorkPmCheck Page - List Form
 * @author youngjoo38
 * @version $Id: WorkPmCheckListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmCheckListForm"
 * */
public class WorkPmCheckListForm extends BaseForm {
    
    private WorkPmCheckCommonDTO workPmCheckCommonDTO = new WorkPmCheckCommonDTO();

    public WorkPmCheckCommonDTO getWorkPmCheckCommonDTO() {
        return workPmCheckCommonDTO;
    }

    public void setWorkPmCheckCommonDTO(WorkPmCheckCommonDTO workPmCheckCommonDTO) {
        this.workPmCheckCommonDTO = workPmCheckCommonDTO;
    }
}