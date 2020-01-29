package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
/**
 * 평가기준 - List Form
 * @author kim21017
 * @version $Id: AssBasePointValListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBasePointValListForm"
 * */

public class AssBasePointValListForm extends BaseForm{
	
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBasePointListDTO assBasePointListDTO = new AssBasePointListDTO();
	private AssBasePointValListDTO assBasePointValListDTO = new AssBasePointValListDTO();
	
	public AssBasePointListDTO getAssBasePointListDTO() {
		return assBasePointListDTO;
	}
	public void setAssBasePointListDTO(AssBasePointListDTO assBasePointListDTO) {
		this.assBasePointListDTO = assBasePointListDTO;
	}
	public AssBaseCommonDTO getAssBaseCommonDTO() {
		return assBaseCommonDTO;
	}
	public void setAssBaseCommonDTO(AssBaseCommonDTO assBaseCommonDTO) {
		this.assBaseCommonDTO = assBaseCommonDTO;
	}
	public AssBasePointValListDTO getAssBasePointValListDTO() {
		return assBasePointValListDTO;
	}
	public void setAssBasePointValListDTO(AssBasePointValListDTO assBasePointValListDTO) {
		this.assBasePointValListDTO = assBasePointValListDTO;
	}
	
}
