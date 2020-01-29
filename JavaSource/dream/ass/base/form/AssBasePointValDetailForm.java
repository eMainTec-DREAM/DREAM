package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;

/**
 * 평가기준 - Detail Form
 * @author kim21017
 * @version $Id: AssBasePointValDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBasePointValDetailForm"
 */
public class AssBasePointValDetailForm extends BaseForm
{
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBasePointListDTO assBasePointListDTO = new AssBasePointListDTO();
	private AssBasePointValListDTO assBasePointValListDTO = new AssBasePointValListDTO();
	private AssBasePointValDetailDTO assBasePointValDetailDTO = new AssBasePointValDetailDTO();
	
	public AssBaseCommonDTO getAssBaseCommonDTO() {
		return assBaseCommonDTO;
	}
	public void setAssBaseCommonDTO(AssBaseCommonDTO assBaseCommonDTO) {
		this.assBaseCommonDTO = assBaseCommonDTO;
	}
	public AssBasePointListDTO getAssBasePointListDTO() {
		return assBasePointListDTO;
	}
	public void setAssBasePointListDTO(AssBasePointListDTO assBasePointListDTO) {
		this.assBasePointListDTO = assBasePointListDTO;
	}
	public AssBasePointValListDTO getAssBasePointValListDTO() {
		return assBasePointValListDTO;
	}
	public void setAssBasePointValListDTO(AssBasePointValListDTO assBasePointValListDTO) {
		this.assBasePointValListDTO = assBasePointValListDTO;
	}
	public AssBasePointValDetailDTO getAssBasePointValDetailDTO() {
		return assBasePointValDetailDTO;
	}
	public void setAssBasePointValDetailDTO(AssBasePointValDetailDTO assBasePointValDetailDTO) {
		this.assBasePointValDetailDTO = assBasePointValDetailDTO;
	}
	
}
