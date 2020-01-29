package dream.consult.comp.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.comp.list.dto.ConsultCompListLovDTO;

/**
 * 회사코드 팝업 Form
 * @author  hyosung
 * @version $Id: ConsultCompListLovForm.java,v 1.0 2016/01/18 09:12:46 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompListLovForm"
 */
public class ConsultCompListLovForm extends MaFinderAcForm
{
    /**  DTO */
    private ConsultCompListLovDTO consultCompListLovDTO = new ConsultCompListLovDTO();

	public ConsultCompListLovDTO getConsultCompListLovDTO() {
		return consultCompListLovDTO;
	}

	public void setConsultCompListLovDTO(ConsultCompListLovDTO consultCompListLovDTO) {
		this.consultCompListLovDTO = consultCompListLovDTO;
	}
}
