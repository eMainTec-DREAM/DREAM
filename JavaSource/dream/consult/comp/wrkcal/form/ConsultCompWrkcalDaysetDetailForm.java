package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetDetailDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDaysetListDTO;

/**
 * �޹��� ����- �� Form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDaysetDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDaysetDetailForm"
 */
public class ConsultCompWrkcalDaysetDetailForm extends BaseForm
{
    //========================================================================
    /** �ٹ��ϴ޷� ���� */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    //========================================================================
    /** �޹����� ��� */
    private ConsultCompWrkcalDaysetListDTO consultCompWrkcalDaysetListDTO = new ConsultCompWrkcalDaysetListDTO();
    /** �޹����� �� */
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
