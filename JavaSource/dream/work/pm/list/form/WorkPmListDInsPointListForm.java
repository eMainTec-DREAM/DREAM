package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;


/**
 * WorkPmListDInsPoint Page - List Form
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmListDInsPointListForm"
 * */
public class WorkPmListDInsPointListForm extends BaseForm {
    
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    private WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO = new WorkPmListDInsPointDetailDTO();
    
    public WorkPmListDInsPointDetailDTO getWorkPmListDInsPointDetailDTO() {
		return workPmListDInsPointDetailDTO;
	}

	public void setWorkPmListDInsPointDetailDTO(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO) {
		this.workPmListDInsPointDetailDTO = workPmListDInsPointDetailDTO;
	}

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO()
    {
        return maPmMstrCommonDTO;
    }

    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
        this.maPmMstrCommonDTO = maPmMstrCommonDTO;
    }

}