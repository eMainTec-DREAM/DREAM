package dream.work.let.permit.form;

import common.struts.BaseForm;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * �����۾��㰡�� �۾����� - �۾��� �� Form
 * @author  kim2107
 * @version $Id: WorkLetPermitCraftDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workLetPermitCraftDetailForm"
 */
public class WorkLetPermitCraftDetailForm extends BaseForm
{    
    //===============================================================
//	/** �����۾� ���� */ 
//    private WorkLetCommonDTO workLetCommonDTO = new WorkLetCommonDTO();
//    /** �����۾��㰡�� ��� */
//    private WorkLetPermitListDTO workLetPermitListDTO = new WorkLetPermitListDTO();
	/** �����۾��㰡�� �۾����� �� DTO  */
    private WorkLetPermitDetailDTO workLetPermitDetailDTO = new WorkLetPermitDetailDTO();
    /** �����۾��㰡�� �۾��� ���  */
    private WorkLetPermitCraftListDTO workLetPermitCraftListDTO = new WorkLetPermitCraftListDTO();
    /** �����۾��㰡�� �۾��� �� DTO  */
    private WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = new WorkLetPermitCraftDetailDTO();
    
//	public WorkLetCommonDTO getWorkLetCommonDTO() {
//		return workLetCommonDTO;
//	}
//	public void setWorkLetCommonDTO(WorkLetCommonDTO workLetCommonDTO) {
//		this.workLetCommonDTO = workLetCommonDTO;
//	}
//	public WorkLetPermitListDTO getWorkLetPermitListDTO() {
//		return workLetPermitListDTO;
//	}
//	public void setWorkLetPermitListDTO(WorkLetPermitListDTO workLetPermitListDTO) {
//		this.workLetPermitListDTO = workLetPermitListDTO;
//	}
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
