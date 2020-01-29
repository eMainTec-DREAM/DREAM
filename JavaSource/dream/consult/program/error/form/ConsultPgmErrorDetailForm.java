package dream.consult.program.error.form;

import common.struts.BaseForm;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;

/**
 * Error Page - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultPgmErrorDetailForm"
 */
public class ConsultPgmErrorDetailForm extends BaseForm
{
	private ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO = new ConsultPgmErrorCommonDTO();
	private ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO = new ConsultPgmErrorDetailDTO();
    
	public ConsultPgmErrorCommonDTO getConsultPgmErrorCommonDTO() {
		return consultPgmErrorCommonDTO;
	}
	public void setConsultPgmErrorCommonDTO(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO) {
		this.consultPgmErrorCommonDTO = consultPgmErrorCommonDTO;
	}
	public ConsultPgmErrorDetailDTO getConsultPgmErrorDetailDTO() {
		return consultPgmErrorDetailDTO;
	}
	public void setConsultPgmErrorDetailDTO(ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO) {
		this.consultPgmErrorDetailDTO = consultPgmErrorDetailDTO;
	}
}
