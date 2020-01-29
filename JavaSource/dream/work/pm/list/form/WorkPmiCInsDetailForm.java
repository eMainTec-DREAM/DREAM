package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;

/**
 * WorkPmiCIns Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiCInsDetailForm"
 */
public class WorkPmiCInsDetailForm extends BaseForm
{
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private WorkPmiCInsCommonDTO workPmiCInsCommonDTO = new WorkPmiCInsCommonDTO();
    private WorkPmiCInsDetailDTO workPmiCInsDetailDTO = new WorkPmiCInsDetailDTO();
    
    public MaPmMstrCommonDTO getMaPmMstrCommonDTO()
    {
        return maPmMstrCommonDTO;
    }
    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
        this.maPmMstrCommonDTO = maPmMstrCommonDTO;
    }
    public WorkPmiCInsCommonDTO getWorkPmiCInsCommonDTO() {
        return workPmiCInsCommonDTO;
    }
    public void setWorkPmiCInsCommonDTO(WorkPmiCInsCommonDTO workPmiCInsCommonDTO) {
        this.workPmiCInsCommonDTO = workPmiCInsCommonDTO;
    }
    public WorkPmiCInsDetailDTO getWorkPmiCInsDetailDTO() {
        return workPmiCInsDetailDTO;
    }
    public void setWorkPmiCInsDetailDTO(WorkPmiCInsDetailDTO workPmiCInsDetailDTO) {
        this.workPmiCInsDetailDTO = workPmiCInsDetailDTO;
    }
}
