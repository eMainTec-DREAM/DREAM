package dream.work.let.categ.form;

import common.struts.BaseForm;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcDetailDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;
/**
 * 안전작업유형 - 보완사항 List Page - List Form
 * @author euna0207
 * @version $Id: WorkLetCategEtcListForm.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * @struts.form name="workLetCategEtcListForm"
 * */

public class WorkLetCategEtcListForm extends BaseForm{
	
	private WorkLetCategCommonDTO workLetCategCommonDTO = new WorkLetCategCommonDTO();
	private WorkLetCategDetailDTO workLetCategDetailDTO = new WorkLetCategDetailDTO();
	private WorkLetCategEtcListDTO workLetCategEtcListDTO = new WorkLetCategEtcListDTO();
	private WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO = new WorkLetCategEtcDetailDTO();
	
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
	public WorkLetCategEtcListDTO getWorkLetCategEtcListDTO() {
		return workLetCategEtcListDTO;
	}
	public void setWorkLetCategEtcListDTO(WorkLetCategEtcListDTO workLetCategEtcListDTO) {
		this.workLetCategEtcListDTO = workLetCategEtcListDTO;
	}
	public WorkLetCategEtcDetailDTO getWorkLetCategEtcDetailDTO() {
		return workLetCategEtcDetailDTO;
	}
	public void setWorkLetCategEtcDetailDTO(WorkLetCategEtcDetailDTO workLetCategEtcDetailDTO) {
		this.workLetCategEtcDetailDTO = workLetCategEtcDetailDTO;
	}
}
