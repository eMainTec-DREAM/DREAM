package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointDetailDTO;
import dream.ass.base.dto.AssBasePointListDTO;

/**
 * 평가항목 - Detail Form
 * @author kim21017
 * @version $Id: AssBasePointDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBasePointDetailForm"
 */
public class AssBasePointDetailForm extends BaseForm
{
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBasePointListDTO assBasePointListDTO = new AssBasePointListDTO();
	private AssBasePointDetailDTO assBasePointDetailDTO = new AssBasePointDetailDTO();
	
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
	public AssBasePointDetailDTO getAssBasePointDetailDTO() {
		return assBasePointDetailDTO;
	}
	public void setAssBasePointDetailDTO(AssBasePointDetailDTO assBasePointDetailDTO) {
		this.assBasePointDetailDTO = assBasePointDetailDTO;
	}
}
