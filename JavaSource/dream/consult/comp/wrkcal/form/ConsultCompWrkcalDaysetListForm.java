package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;

/**
 * 휴무일 설정 - 목록 form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDaysetListForm"
 */
public class ConsultCompWrkcalDaysetListForm extends BaseForm
{
    //===============================================================
    /** 회사설정 공통 */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    /** 휴무요일 목록 */
    private ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO = new ConsultCompWrkcalDaysetListDTO();

	public ConsultCompWrkcalCommonDTO getConsultCompWrkcalCommonDTO() {
		return consultCompWrkcalCommonDTO;
	}

	public void setConsultCompWrkcalCommonDTO(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) {
		this.consultCompWrkcalCommonDTO = consultCompWrkcalCommonDTO;
	}

	public ConsultCompWrkcalDaysetListDTO getConsultCompWrkcalDaysetListDTO() {
		return consultCompWrkcalDaysetListDTO;
	}

	public void setConsultCompWrkcalDaysetListDTO(ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO) {
		this.consultCompWrkcalDaysetListDTO = consultCompWrkcalDaysetListDTO;
	}

}
