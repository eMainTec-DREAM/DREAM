package dream.work.let.permit.form;

import common.struts.BaseForm;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서 작업유형 - 작업자 목록
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListForm.java,v 1.0 2015/12/01 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="workLetPermitCraftListForm"
 */
public class WorkLetPermitCraftListForm extends BaseForm
{    
    //===============================================================
	/** 안전작업허가서 작업유형 상세 DTO  */
    private WorkLetPermitDetailDTO workLetPermitDetailDTO = new WorkLetPermitDetailDTO();
    /** 안전작업허가서 작업유형 작업자 목록  */
    private WorkLetPermitCraftListDTO workLetPermitCraftListDTO = new WorkLetPermitCraftListDTO();
    /** 안전작업허가서 작업유형 작업자 상세 DTO  */
    private WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = new WorkLetPermitCraftDetailDTO();
    
    
	public WorkLetPermitCraftListDTO getWorkLetPermitCraftListDTO() {
		return workLetPermitCraftListDTO;
	}
	public WorkLetPermitDetailDTO getWorkLetPermitDetailDTO() {
		return workLetPermitDetailDTO;
	}
	public void setWorkLetPermitDetailDTO(WorkLetPermitDetailDTO workLetPermitDetailDTO) {
		this.workLetPermitDetailDTO = workLetPermitDetailDTO;
	}
	public void setWorkLetPermitCraftListDTO(WorkLetPermitCraftListDTO workLetPermitCraftListDTO) {
		this.workLetPermitCraftListDTO = workLetPermitCraftListDTO;
	}
	public WorkLetPermitCraftDetailDTO getWorkLetPermitCraftDetailDTO() {
		return workLetPermitCraftDetailDTO;
	}
	public void setWorkLetPermitCraftDetailDTO(WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO) {
		this.workLetPermitCraftDetailDTO = workLetPermitCraftDetailDTO;
	}

}
