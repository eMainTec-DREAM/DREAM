package dream.work.let.categ.form;

import common.struts.BaseForm;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 점검항목 detail Page - Detail Form
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailForm.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts.form name="workLetCategPointDetailForm"
 */
public class WorkLetCategPointDetailForm extends BaseForm
{
	private WorkLetCategCommonDTO workLetCategCommonDTO = new WorkLetCategCommonDTO();
	private WorkLetCategDetailDTO workLetCategDetailDTO = new WorkLetCategDetailDTO();
	private WorkLetCategPointListDTO workLetCategPointListDTO = new WorkLetCategPointListDTO();
	private WorkLetCategPointDetailDTO workLetCategPointDetailDTO = new WorkLetCategPointDetailDTO();

	public WorkLetCategCommonDTO getWorkLetCategCommonDTO() {
		return workLetCategCommonDTO;
	}
	public void setWorkLetCategCommonDTO(WorkLetCategCommonDTO workLetCategCommonDTO) {
		this.workLetCategCommonDTO = workLetCategCommonDTO;
	}
	public WorkLetCategDetailDTO getWorkLetCategDetailDTO() {
		return workLetCategDetailDTO;
	}
	public void setWorkLetCategDetailDTO(WorkLetCategDetailDTO workLetCategDetailDTO) {
		this.workLetCategDetailDTO = workLetCategDetailDTO;
	}
	public WorkLetCategPointListDTO getWorkLetCategPointListDTO() {
		return workLetCategPointListDTO;
	}
	public void setWorkLetCategPointListDTO(WorkLetCategPointListDTO workLetCategPointListDTO) {
		this.workLetCategPointListDTO = workLetCategPointListDTO;
	}
	public WorkLetCategPointDetailDTO getWorkLetCategPointDetailDTO() {
		return workLetCategPointDetailDTO;
	}
	public void setWorkLetCategPointDetailDTO(WorkLetCategPointDetailDTO workLetCategPointDetailDTO) {
		this.workLetCategPointDetailDTO = workLetCategPointDetailDTO;
	}
	

}
