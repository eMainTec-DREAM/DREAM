package dream.work.let.permit.form;

import common.struts.BaseForm;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;

/**
 * 안전작업 - 안전작업허가서 작업유형 목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workLetPermitListForm"
 */
public class WorkLetPermitListForm extends BaseForm
{    
    /** 안전작업 공통 */ 
    private WorkLetCommonDTO workLetCommonDTO = new WorkLetCommonDTO();
    /** 안전작업 상세 */
    private WorkLetDetailDTO workLetDetailDTO = new WorkLetDetailDTO();
    /** 안전작업허가서 작업유형 목록 */
    private WorkLetPermitListDTO workLetPermitListDTO = new WorkLetPermitListDTO();
	/** 안전작업허가서 작업유형 상세 DTO  */
    private WorkLetPermitDetailDTO workLetPermitDetailDTO = new WorkLetPermitDetailDTO();
    
    
	public WorkLetCommonDTO getWorkLetCommonDTO() {
		return workLetCommonDTO;
	}
	public void setWorkLetCommonDTO(WorkLetCommonDTO workLetCommonDTO) {
		this.workLetCommonDTO = workLetCommonDTO;
	}
	public WorkLetDetailDTO getWorkLetDetailDTO() {
		return workLetDetailDTO;
	}
	public void setWorkLetDetailDTO(WorkLetDetailDTO workLetDetailDTO) {
		this.workLetDetailDTO = workLetDetailDTO;
	}
	public WorkLetPermitListDTO getWorkLetPermitListDTO() {
		return workLetPermitListDTO;
	}
	public void setWorkLetPermitListDTO(WorkLetPermitListDTO workLetPermitListDTO) {
		this.workLetPermitListDTO = workLetPermitListDTO;
	}
	public WorkLetPermitDetailDTO getWorkLetPermitDetailDTO() {
		return workLetPermitDetailDTO;
	}
	public void setWorkLetPermitDetailDTO(WorkLetPermitDetailDTO workLetPermitDetailDTO) {
		this.workLetPermitDetailDTO = workLetPermitDetailDTO;
	}
    
}
