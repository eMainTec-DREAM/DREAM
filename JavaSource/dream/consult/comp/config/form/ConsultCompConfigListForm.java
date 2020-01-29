package dream.consult.comp.config.form;

import common.struts.BaseForm;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;

/**
 * �ý���ȯ�溯�� - ��� form
 * @author  syyang
 * @version $Id: ConsultCompConfigListForm.java,v 1.0 2015/12/01 09:13:09 syyang Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompConfigListForm"
 */
public class ConsultCompConfigListForm extends BaseForm
{    
    //===============================================================
    /** �ý���ȯ�溯�� ���� */
    private ConsultCompConfigCommonDTO consultCompConfigCommonDTO = new ConsultCompConfigCommonDTO();
    
	public ConsultCompConfigCommonDTO getConsultCompConfigCommonDTO() {
		return consultCompConfigCommonDTO;
	}

	public void setConsultCompConfigCommonDTO(ConsultCompConfigCommonDTO consultCompConfigCommonDTO) {
		this.consultCompConfigCommonDTO = consultCompConfigCommonDTO;
	}
	
}
