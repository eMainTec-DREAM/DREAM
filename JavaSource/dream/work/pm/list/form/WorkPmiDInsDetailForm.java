package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * WorkPmiDIns Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiDInsDetailForm"
 */
public class WorkPmiDInsDetailForm extends BaseForm
{
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
    private WorkPmiDInsDetailDTO workPmiDInsDetailDTO = new WorkPmiDInsDetailDTO();
    
    public MaPmMstrCommonDTO getMaPmMstrCommonDTO()
    {
        return maPmMstrCommonDTO;
    }
    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
        this.maPmMstrCommonDTO = maPmMstrCommonDTO;
    }
    public WorkPmiDInsCommonDTO getWorkPmiDInsCommonDTO() {
        return workPmiDInsCommonDTO;
    }
    public void setWorkPmiDInsCommonDTO(WorkPmiDInsCommonDTO workPmiDInsCommonDTO) {
        this.workPmiDInsCommonDTO = workPmiDInsCommonDTO;
    }
    public WorkPmiDInsDetailDTO getWorkPmiDInsDetailDTO() {
        return workPmiDInsDetailDTO;
    }
    public void setWorkPmiDInsDetailDTO(WorkPmiDInsDetailDTO workPmiDInsDetailDTO) {
        this.workPmiDInsDetailDTO = workPmiDInsDetailDTO;
    }
}
