package dream.consult.program.guide.form;

import common.struts.BaseForm;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
/**
 * Guide Page - List Form
 * @author kim21017
 * @version $Id: ConsultPgmGuideListForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="consultPgmGuideListForm"
 * */

public class ConsultPgmGuideListForm extends BaseForm{
	
	private ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO = new ConsultPgmGuideCommonDTO();

	public ConsultPgmGuideCommonDTO getConsultPgmGuideCommonDTO() {
		return consultPgmGuideCommonDTO;
	}

	public void setConsultPgmGuideCommonDTO(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO) {
		this.consultPgmGuideCommonDTO = consultPgmGuideCommonDTO;
	}
	
}
