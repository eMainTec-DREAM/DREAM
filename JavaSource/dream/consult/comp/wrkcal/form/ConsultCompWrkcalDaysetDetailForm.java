package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;

/**
 * 휴무일 설정- 상세 Form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDaysetDetailForm"
 */
public class ConsultCompWrkcalDaysetDetailForm extends BaseForm
{
    //========================================================================
    /** 근무일달력 공통 */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    //========================================================================
    /** 휴무요일 목록 */
    private ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO = new ConsultCompWrkcalDaysetListDTO();
    /** 휴무요일 상세 */
    private ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO = new ConsultCompWrkcalDaysetDetailDTO();


	public ConsultCompWrkcalCommonDTO getConsultCompWrkcalCommonDTO() {
		return consultCompWrkcalCommonDTO;
	}

	public void setConsultCompWrkcalCommonDTO(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) {
		this.consultCompWrkcalCommonDTO = consultCompWrkcalCommonDTO;
	}

	public ConsultCompWrkcalDaysetDetailDTO getConsultCompWrkcalDaysetDetailDTO() {
		return consultCompWrkcalDaysetDetailDTO;
	}

	public void setConsultCompWrkcalDaysetDetailDTO(ConsultCompWrkcalDaysetDetailDTO consultCompWrkcalDaysetDetailDTO) {
		this.consultCompWrkcalDaysetDetailDTO = consultCompWrkcalDaysetDetailDTO;
	}

	public ConsultCompWrkcalDaysetListDTO getConsultCompWrkcalDaysetListDTO() {
		return consultCompWrkcalDaysetListDTO;
	}

	public void setConsultCompWrkcalDaysetListDTO(ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO) {
		this.consultCompWrkcalDaysetListDTO = consultCompWrkcalDaysetListDTO;
	}

}
