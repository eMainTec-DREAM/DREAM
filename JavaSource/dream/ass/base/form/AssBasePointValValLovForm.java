package dream.ass.base.form;

import dream.ass.base.dto.AssBasePointValValLovDTO;
import dream.comm.form.MaFinderAcForm;
/**
 * 평가항목 평가기준 LOV - List Form
 * @author kim21017
 * @version $Id: AssBasePointValValLovForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBasePointValValLovForm"
 * */

public class AssBasePointValValLovForm extends MaFinderAcForm{
	
	private AssBasePointValValLovDTO assBasePointValValLovDTO = new AssBasePointValValLovDTO();

	public AssBasePointValValLovDTO getAssBasePointValValLovDTO() {
		return assBasePointValValLovDTO;
	}

	public void setAssBasePointValValLovDTO(AssBasePointValValLovDTO assBasePointValValLovDTO) {
		this.assBasePointValValLovDTO = assBasePointValValLovDTO;
	}
	
}
