package dream.consult.comp.wrkcal.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalLovDTO;

/**
 * 근무달력 팝업 Form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalLovForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalLovForm"
 */
public class ConsultCompWrkcalLovForm extends MaFinderAcForm
{
    /**  DTO */
    private ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO = new ConsultCompWrkcalLovDTO();

	public ConsultCompWrkcalLovDTO getConsultCompWrkcalLovDTO() {
		return consultCompWrkcalLovDTO;
	}

	public void setConsultCompWrkcalLovDTO(ConsultCompWrkcalLovDTO consultCompWrkcalLovDTO) {
		this.consultCompWrkcalLovDTO = consultCompWrkcalLovDTO;
	}
}
