package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;

/**
 * WorkPmiCInsPoint Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiCInsPointDetailForm"
 */
public class WorkPmiCInsPointDetailForm extends BaseForm
{
    private WorkPmiCInsCommonDTO workPmiCInsCommonDTO = new WorkPmiCInsCommonDTO();
    
    private WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO = new WorkPmiCInsPointDetailDTO();
    
    /** Ã·ºÎ¹®¼­ DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public WorkPmiCInsCommonDTO getWorkPmiCInsCommonDTO()
    {
        return workPmiCInsCommonDTO;
    }
    public void setWorkPmiCInsCommonDTO(WorkPmiCInsCommonDTO workPmiCInsCommonDTO)
    {
        this.workPmiCInsCommonDTO = workPmiCInsCommonDTO;
    }
    public WorkPmiCInsPointDetailDTO getWorkPmiCInsPointDetailDTO() {
        return workPmiCInsPointDetailDTO;
    }
    public void setWorkPmiCInsPointDetailDTO(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO) {
        this.workPmiCInsPointDetailDTO = workPmiCInsPointDetailDTO;
    }
}
