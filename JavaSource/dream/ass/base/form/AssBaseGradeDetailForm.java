package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseGradeDetailDTO;
import dream.ass.base.dto.AssBaseGradeListDTO;

/**
 * 등급기준 - Detail Form
 * @author kim21017
 * @version $Id: AssBaseGradeDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBaseGradeDetailForm"
 */
public class AssBaseGradeDetailForm extends BaseForm
{
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBaseGradeListDTO assBaseGradeListDTO = new AssBaseGradeListDTO();
	private AssBaseGradeDetailDTO assBaseGradeDetailDTO = new AssBaseGradeDetailDTO();
	
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
	public AssBaseGradeDetailDTO getAssBaseGradeDetailDTO() {
		return assBaseGradeDetailDTO;
	}
	public void setAssBaseGradeDetailDTO(AssBaseGradeDetailDTO assBaseGradeDetailDTO) {
		this.assBaseGradeDetailDTO = assBaseGradeDetailDTO;
	}
}
