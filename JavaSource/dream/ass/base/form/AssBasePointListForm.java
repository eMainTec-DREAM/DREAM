package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
/**
 * 평가항목 - List Form
 * @author kim21017
 * @version $Id: AssBasePointListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBasePointListForm"
 * */

public class AssBasePointListForm extends BaseForm{
	
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBasePointListDTO assBasePointListDTO = new AssBasePointListDTO();
	
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
	
}
