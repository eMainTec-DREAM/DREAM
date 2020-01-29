package dream.consult.comp.terminal.form;

import common.struts.BaseForm;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;

/**
 * 접근터미널 - 목록 form
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompTerminalExListForm"
 */
public class ConsultCompTerminalExListForm extends BaseForm
{
    //===============================================================
    /** 접근터미널  공통 */
    private ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO = new ConsultCompTerminalExCommonDTO();

	public ConsultCompTerminalExCommonDTO getConsultCompTerminalExCommonDTO() {
		return consultCompTerminalExCommonDTO;
	}

	public void setConsultCompTerminalExCommonDTO(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO) {
		this.consultCompTerminalExCommonDTO = consultCompTerminalExCommonDTO;
	}

}
