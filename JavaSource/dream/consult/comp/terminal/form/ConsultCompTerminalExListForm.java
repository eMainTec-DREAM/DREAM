package dream.consult.comp.terminal.form;

import common.struts.BaseForm;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;

/**
 * �����͹̳� - ��� form
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompTerminalExListForm"
 */
public class ConsultCompTerminalExListForm extends BaseForm
{
    //===============================================================
    /** �����͹̳�  ���� */
    private ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO = new ConsultCompTerminalExCommonDTO();

	public ConsultCompTerminalExCommonDTO getConsultCompTerminalExCommonDTO() {
		return consultCompTerminalExCommonDTO;
	}

	public void setConsultCompTerminalExCommonDTO(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO) {
		this.consultCompTerminalExCommonDTO = consultCompTerminalExCommonDTO;
	}

}
