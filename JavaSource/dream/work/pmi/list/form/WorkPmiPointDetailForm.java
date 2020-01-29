package dream.work.pmi.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * �����۾� ����
 * @author  kim2107
 * @version $Id: WorkPmiPointDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmiPointDetailForm"
 */
public class WorkPmiPointDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
	/** �۾���� �˻��׸� ��� DTO  */
    private WorkPmiPointListDTO workPmiPointListDTO = new WorkPmiPointListDTO();
	/** �۾���� �˻��׸� �� DTO  */
    private WorkPmiPointDetailDTO workPmiPointDetailDTO = new WorkPmiPointDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public WorkPmiPointListDTO getWorkPmiPointListDTO() {
		return workPmiPointListDTO;
	}
	public void setWorkPmiPointListDTO(WorkPmiPointListDTO workPmiPointListDTO) {
		this.workPmiPointListDTO = workPmiPointListDTO;
	}
	public WorkPmiPointDetailDTO getWorkPmiPointDetailDTO() {
		return workPmiPointDetailDTO;
	}
	public void setWorkPmiPointDetailDTO(WorkPmiPointDetailDTO workPmiPointDetailDTO) {
		this.workPmiPointDetailDTO = workPmiPointDetailDTO;
	}
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}
	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}
}
