package dream.work.let.permit.form;

import common.struts.BaseForm;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * �����۾��㰡�� �۾����� - �����׸� ��� Form
 * @author syyang
 * @version $Id: WorkLetPermitPointListForm.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @struts.form name="workLetPermitPointListForm"
 * */

public class WorkLetPermitPointListForm extends BaseForm
{
	/** �����۾��㰡�� �۾����� �� DTO  */
    private WorkLetPermitDetailDTO workLetPermitDetailDTO = new WorkLetPermitDetailDTO();
    /** �����۾��㰡�� �۾����� �����׸� ��� */
	private WorkLetPermitPointListDTO workLetPermitPointListDTO = new WorkLetPermitPointListDTO();
	/** �����۾��㰡�� �۾����� �����׸� �� DTO */
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
