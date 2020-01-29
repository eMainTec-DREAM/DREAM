package dream.work.pm.check.form;

import common.struts.BaseForm;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckDetailDTO;

/**
 * WorkPmCheck Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmCheckDetailForm"
 */
public class WorkPmCheckDetailForm extends BaseForm
{
    private WorkPmCheckCommonDTO workPmCheckCommonDTO = new WorkPmCheckCommonDTO();
    private WorkPmCheckDetailDTO workPmCheckDetailDTO = new WorkPmCheckDetailDTO();
    
    public WorkPmCheckCommonDTO getWorkPmCheckCommonDTO() {
        return workPmCheckCommonDTO;
    }
    public void setWorkPmCheckCommonDTO(WorkPmCheckCommonDTO workPmCheckCommonDTO) {
        this.workPmCheckCommonDTO = workPmCheckCommonDTO;
    }
    public WorkPmCheckDetailDTO getWorkPmCheckDetailDTO() {
        return workPmCheckDetailDTO;
    }
    public void setWorkPmCheckDetailDTO(WorkPmCheckDetailDTO workPmCheckDetailDTO) {
        this.workPmCheckDetailDTO = workPmCheckDetailDTO;
    }
}
