package dream.consult.program.error.form;

import common.struts.BaseForm;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
/**
 * Error Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultPgmErrorListForm"
 * */

public class ConsultPgmErrorListForm extends BaseForm{
	
	private ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO = new ConsultPgmErrorCommonDTO();

	public ConsultPgmErrorCommonDTO getConsultPgmErrorCommonDTO() {
		return consultPgmErrorCommonDTO;
	}

	public void setConsultPgmErrorCommonDTO(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO) {
		this.consultPgmErrorCommonDTO = consultPgmErrorCommonDTO;
	}
	
}
