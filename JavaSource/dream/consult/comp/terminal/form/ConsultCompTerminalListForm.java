package dream.consult.comp.terminal.form;

import common.struts.BaseForm;
import dream.consult.comp.terminal.dto.ConsultCompTerminalCommonDTO;

/**
 * �����͹̳� - ��� form
 * @author  kim21017
 * @version $Id: ConsultCompTerminalListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompTerminalListForm"
 */
public class ConsultCompTerminalListForm extends BaseForm
{
    //===============================================================
    /** �����͹̳�  ���� */
    private ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO = new ConsultCompTerminalCommonDTO();

	public ConsultCompTerminalCommonDTO getConsultCompTerminalCommonDTO() {
		return consultCompTerminalCommonDTO;
	}

	public void setConsultCompTerminalCommonDTO(ConsultCompTerminalCommonDTO consultCompTerminalCommonDTO) {
		this.consultCompTerminalCommonDTO = consultCompTerminalCommonDTO;
	}

}
