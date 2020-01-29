package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * WorkListCinsPlanMstr Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workListCinsPlanMstrDetailForm"
 */
public class WorkListCinsPlanMstrDetailForm extends BaseForm
{
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = new WorkListCinsPlanMstrCommonDTO();
    private WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO = new WorkListCinsPlanMstrDetailDTO();
    
    public MaPmMstrCommonDTO getMaPmMstrCommonDTO()
    {
        return maPmMstrCommonDTO;
    }
    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
        this.maPmMstrCommonDTO = maPmMstrCommonDTO;
    }
    public WorkListCinsPlanMstrCommonDTO getWorkListCinsPlanMstrCommonDTO() {
        return workListCinsPlanMstrCommonDTO;
    }
    public void setWorkListCinsPlanMstrCommonDTO(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO) {
        this.workListCinsPlanMstrCommonDTO = workListCinsPlanMstrCommonDTO;
    }
    public WorkListCinsPlanMstrDetailDTO getWorkListCinsPlanMstrDetailDTO() {
        return workListCinsPlanMstrDetailDTO;
    }
    public void setWorkListCinsPlanMstrDetailDTO(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO) {
        this.workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailDTO;
    }
}
