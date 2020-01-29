package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetDetailDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;

/**
 * �޹�����- �� Form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDowsetDetailForm"
 */
public class ConsultCompWrkcalDowsetDetailForm extends BaseForm
{
    //========================================================================
    /** �ٹ��ϴ޷� ���� */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    //========================================================================
    /** �޹����� ��� */
    private ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO = new ConsultCompWrkcalDowsetListDTO();
    /** �޹����� �� */
    private ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO = new ConsultCompWrkcalDowsetDetailDTO();


	public ConsultCompWrkcalCommonDTO getConsultCompWrkcalCommonDTO() {
		return consultCompWrkcalCommonDTO;
	}

	public void setConsultCompWrkcalCommonDTO(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) {
		this.consultCompWrkcalCommonDTO = consultCompWrkcalCommonDTO;
	}

	public ConsultCompWrkcalDowsetDetailDTO getConsultCompWrkcalDowsetDetailDTO() {
		return consultCompWrkcalDowsetDetailDTO;
	}

	public void setConsultCompWrkcalDowsetDetailDTO(ConsultCompWrkcalDowsetDetailDTO consultCompWrkcalDowsetDetailDTO) {
		this.consultCompWrkcalDowsetDetailDTO = consultCompWrkcalDowsetDetailDTO;
	}

	public ConsultCompWrkcalDowsetListDTO getConsultCompWrkcalDowsetListDTO() {
		return consultCompWrkcalDowsetListDTO;
	}

	public void setConsultCompWrkcalDowsetListDTO(ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO) {
		this.consultCompWrkcalDowsetListDTO = consultCompWrkcalDowsetListDTO;
	}

}
