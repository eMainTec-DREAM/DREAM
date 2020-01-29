package dream.consult.comp.config.form;

import common.struts.BaseForm;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;

/**
 * �ý��� ȯ�溯��- �� Form
 * @author  syyang
 * @version $Id: ConsultCompConfigDetailForm.java,v 1.0 2015/12/02 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompConfigDetailForm"
 */
public class ConsultCompConfigDetailForm extends BaseForm
{
    //========================================================================
    /** �ý���ȯ�溯�� ���� */ 
    private ConsultCompConfigCommonDTO consultCompConfigCommonDTO = new ConsultCompConfigCommonDTO();
    //========================================================================
    /** �ý���ȯ�溯�� �� */
    private ConsultCompConfigDetailDTO consultCompConfigDetailDTO = new ConsultCompConfigDetailDTO();
    
	public ConsultCompConfigCommonDTO getConsultCompConfigCommonDTO() {
		return consultCompConfigCommonDTO;
	}

	public void setConsultCompConfigCommonDTO(ConsultCompConfigCommonDTO consultCompConfigCommonDTO) {
		this.consultCompConfigCommonDTO = consultCompConfigCommonDTO;
	}

	public ConsultCompConfigDetailDTO getConsultCompConfigDetailDTO() {
		return consultCompConfigDetailDTO;
	}

	public void setConsultCompConfigDetailDTO(ConsultCompConfigDetailDTO consultCompConfigDetailDTO) {
		this.consultCompConfigDetailDTO = consultCompConfigDetailDTO;
	}
}
