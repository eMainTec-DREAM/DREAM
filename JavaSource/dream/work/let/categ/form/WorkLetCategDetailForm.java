package dream.work.let.categ.form;

import common.struts.BaseForm;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 Detail Page - Detail Form
 * @author euna0207
 * @version $Id: WorkLetCategDetailForm.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts.form name="workLetCategDetailForm"
 */
public class WorkLetCategDetailForm extends BaseForm
{
	private WorkLetCategCommonDTO workLetCategCommonDTO = new WorkLetCategCommonDTO();
	private WorkLetCategDetailDTO workLetCategDetailDTO = new WorkLetCategDetailDTO();
	private WorkLetCategPointListDTO workLetCategPointListDTO = new WorkLetCategPointListDTO();
	private WorkLetCategEtcListDTO workLetCategEtcListDTO = new WorkLetCategEtcListDTO();
	
	public WorkLetCategCommonDTO getWorkLetCategCommonDTO() {
		return workLetCategCommonDTO;
	}
	public WorkLetCategPointListDTO getWorkLetCategPointListDTO() {
		return workLetCategPointListDTO;
	}
	public void setWorkLetCategPointListDTO(WorkLetCategPointListDTO workLetCategPointListDTO) {
		this.workLetCategPointListDTO = workLetCategPointListDTO;
	}
	public WorkLetCategEtcListDTO getWorkLetCategEtcListDTO() {
		return workLetCategEtcListDTO;
	}
	public void setWorkLetCategEtcListDTO(WorkLetCategEtcListDTO workLetCategEtcListDTO) {
		this.workLetCategEtcListDTO = workLetCategEtcListDTO;
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
}
