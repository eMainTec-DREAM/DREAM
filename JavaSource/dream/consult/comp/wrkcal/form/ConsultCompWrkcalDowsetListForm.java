package dream.consult.comp.wrkcal.form;

import common.struts.BaseForm;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDowsetListDTO;

/**
 * ȸ�缳�� - ��� form
 * @author  kim21017
 * @version $Id: ConsultCompWrkcalDowsetListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompWrkcalDowsetListForm"
 */
public class ConsultCompWrkcalDowsetListForm extends BaseForm
{
    //===============================================================
    /** ȸ�缳�� ���� */
    private ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO = new ConsultCompWrkcalCommonDTO();
    /** �޹����� ��� */
    private ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO = new ConsultCompWrkcalDowsetListDTO();

	public ConsultCompWrkcalCommonDTO getConsultCompWrkcalCommonDTO() {
		return consultCompWrkcalCommonDTO;
	}

	public void setConsultCompWrkcalCommonDTO(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO) {
		this.consultCompWrkcalCommonDTO = consultCompWrkcalCommonDTO;
	}

	public ConsultCompWrkcalDowsetListDTO getConsultCompWrkcalDowsetListDTO() {
		return consultCompWrkcalDowsetListDTO;
	}

	public void setConsultCompWrkcalDowsetListDTO(ConsultCompWrkcalDowsetListDTO consultCompWrkcalDowsetListDTO) {
		this.consultCompWrkcalDowsetListDTO = consultCompWrkcalDowsetListDTO;
	}

}
