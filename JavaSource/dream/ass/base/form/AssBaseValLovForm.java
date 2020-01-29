package dream.ass.base.form;

import dream.ass.base.dto.AssBaseValLovDTO;
import dream.comm.form.MaFinderAcForm;
/**
 * 설비등급 평가기준 LOV - List Form
 * @author kim21017
 * @version $Id: AssBaseValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBaseValLovForm"
 * */

public class AssBaseValLovForm extends MaFinderAcForm{
	
	private AssBaseValLovDTO assBaseValLovDTO = new AssBaseValLovDTO();

	public AssBaseValLovDTO getAssBaseValLovDTO() {
		return assBaseValLovDTO;
	}

	public void setAssBaseValLovDTO(AssBaseValLovDTO assBaseValLovDTO) {
		this.assBaseValLovDTO = assBaseValLovDTO;
	}

	
}
