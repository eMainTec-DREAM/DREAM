package dream.consult.program.guide.form;

import common.struts.BaseForm;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;
import dream.consult.program.guide.dto.ConsultPgmGuideDetailDTO;

/**
 * Guide Page - Detail Form
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="consultPgmGuideDetailForm"
 */
public class ConsultPgmGuideDetailForm extends BaseForm
{
	private ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO = new ConsultPgmGuideCommonDTO();
	private ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO = new ConsultPgmGuideDetailDTO();
    
	public ConsultPgmGuideCommonDTO getConsultPgmGuideCommonDTO() {
		return consultPgmGuideCommonDTO;
	}
	public void setConsultPgmGuideCommonDTO(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO) {
		this.consultPgmGuideCommonDTO = consultPgmGuideCommonDTO;
	}
	public ConsultPgmGuideDetailDTO getConsultPgmGuideDetailDTO() {
		return consultPgmGuideDetailDTO;
	}
	public void setConsultPgmGuideDetailDTO(ConsultPgmGuideDetailDTO consultPgmGuideDetailDTO) {
		this.consultPgmGuideDetailDTO = consultPgmGuideDetailDTO;
	}
}
