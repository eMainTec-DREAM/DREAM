package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;

/**
 * WorkPmiDInsPoint Page - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointDetailForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmiDInsPointDetailForm"
 */
public class WorkPmiDInsPointDetailForm extends BaseForm
{
    private WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
    
    private WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO = new WorkPmiDInsPointDetailDTO();

    /** Ã·ºÎ¹®¼­ DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    public WorkPmiDInsCommonDTO getWorkPmiDInsCommonDTO()
    {
        return workPmiDInsCommonDTO;
    }
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public void setWorkPmiDInsCommonDTO(WorkPmiDInsCommonDTO workPmiDInsCommonDTO)
    {
        this.workPmiDInsCommonDTO = workPmiDInsCommonDTO;
    }
    public WorkPmiDInsPointDetailDTO getWorkPmiDInsPointDetailDTO() {
        return workPmiDInsPointDetailDTO;
    }
    public void setWorkPmiDInsPointDetailDTO(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO) {
        this.workPmiDInsPointDetailDTO = workPmiDInsPointDetailDTO;
    }
}
