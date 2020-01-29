package dream.work.let.permit.form;

import common.struts.BaseForm;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 목록 Form
 * @author syyang
 * @version $Id: WorkLetPermitPointListForm.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @struts.form name="workLetPermitPointListForm"
 * */

public class WorkLetPermitPointListForm extends BaseForm
{
	/** 안전작업허가서 작업유형 상세 DTO  */
    private WorkLetPermitDetailDTO workLetPermitDetailDTO = new WorkLetPermitDetailDTO();
    /** 안전작업허가서 작업유형 점검항목 목록 */
	private WorkLetPermitPointListDTO workLetPermitPointListDTO = new WorkLetPermitPointListDTO();
	/** 안전작업허가서 작업유형 점검항목 상세 DTO */
	private WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO = new WorkLetPermitPointDetailDTO();
	

	public WorkLetPermitDetailDTO getWorkLetPermitDetailDTO() {
		return workLetPermitDetailDTO;
	}
	public void setWorkLetPermitDetailDTO(WorkLetPermitDetailDTO workLetPermitDetailDTO) {
		this.workLetPermitDetailDTO = workLetPermitDetailDTO;
	}
	public WorkLetPermitPointListDTO getWorkLetPermitPointListDTO() {
		return workLetPermitPointListDTO;
	}
	public void setWorkLetPermitPointListDTO(WorkLetPermitPointListDTO workLetPermitPointListDTO) {
		this.workLetPermitPointListDTO = workLetPermitPointListDTO;
	}
	public WorkLetPermitPointDetailDTO getWorkLetPermitPointDetailDTO() {
		return workLetPermitPointDetailDTO;
	}
	public void setWorkLetPermitPointDetailDTO(WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO) {
		this.workLetPermitPointDetailDTO = workLetPermitPointDetailDTO;
	}

}
