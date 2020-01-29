package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;
/**
 * 등급기준 - List Form
 * @author kim21017
 * @version $Id: AssBaseGradeListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBaseGradeListForm"
 * */

public class AssBaseGradeListForm extends BaseForm{
	
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBaseGradeListDTO assBaseGradeListDTO = new AssBaseGradeListDTO();
	
	public AssBaseCommonDTO getAssBaseCommonDTO() {
		return assBaseCommonDTO;
	}
	public void setAssBaseCommonDTO(AssBaseCommonDTO assBaseCommonDTO) {
		this.assBaseCommonDTO = assBaseCommonDTO;
	}
	public AssBaseGradeListDTO getAssBaseGradeListDTO() {
		return assBaseGradeListDTO;
	}
	public void setAssBaseGradeListDTO(AssBaseGradeListDTO assBaseGradeListDTO) {
		this.assBaseGradeListDTO = assBaseGradeListDTO;
	}
	
}
