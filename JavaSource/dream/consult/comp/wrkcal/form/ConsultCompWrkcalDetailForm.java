package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;

/**
 * 회사설정- 상세 Form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDetailForm"
 */
public class ConsultCompWrkcalDetailForm extends BaseForm
{
    //========================================================================
    /** 회사설정 공통 */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    //========================================================================
    /** 회사설정 상세 */
    private ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO = new ConsultCompWrkcalDetailDTO();


	public ConsultCompWrkcalCommonDTO getConsultCompWrkcalCommonDTO() {
		return consultCompWrkcalCommonDTO;
	}

	public void setConsultCompWrkcalCommonDTO(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) {
		this.consultCompWrkcalCommonDTO = consultCompWrkcalCommonDTO;
	}

	public ConsultCompWrkcalDetailDTO getConsultCompWrkcalDetailDTO() {
		return consultCompWrkcalDetailDTO;
	}

	public void setConsultCompWrkcalDetailDTO(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO) {
		this.consultCompWrkcalDetailDTO = consultCompWrkcalDetailDTO;
	}

}
