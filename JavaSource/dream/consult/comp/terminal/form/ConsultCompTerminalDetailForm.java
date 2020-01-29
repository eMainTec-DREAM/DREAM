package dream.consult.comp.terminal.form;

import common.struts.BaseForm;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalDetailDTO;

/**
 * �����͹̳�- �� Form
 * @author  kim21017
 * @version $Id: ConsultCompTerminalDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompTerminalDetailForm"
 */
public class ConsultCompTerminalDetailForm extends BaseForm
{
    //========================================================================
    /** �����͹̳� ���� */
    private ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO = new ConsultCompTerminalCommonDTO();
    //========================================================================
    /** �����͹̳� �� */
    private ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO = new ConsultCompTerminalDetailDTO();


	public ConsultCompTerminalCommonDTO getConsultCompTerminalCommonDTO() {
		return consultCompTerminalCommonDTO;
	}

	public void setConsultCompTerminalCommonDTO(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO) {
		this.consultCompTerminalCommonDTO = consultCompTerminalCommonDTO;
	}

	public ConsultCompTerminalDetailDTO getConsultCompTerminalDetailDTO() {
		return consultCompTerminalDetailDTO;
	}

	public void setConsultCompTerminalDetailDTO(ConsultCompTerminalDetailDTO consultCompTerminalDetailDTO) {
		this.consultCompTerminalDetailDTO = consultCompTerminalDetailDTO;
	}

}
