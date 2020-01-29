package dream.consult.comp.terminal.form;

import common.struts.BaseForm;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExCommonDTO;
import dream.consult.comp.terminal.dto.ConsultCompTerminalExDetailDTO;

/**
 * 접근터미널- 상세 Form
 * @author  kim21017
 * @version $Id: ConsultCompTerminalExDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="consultCompTerminalExDetailForm"
 */
public class ConsultCompTerminalExDetailForm extends BaseForm
{
    //========================================================================
    /** 접근터미널 공통 */
    private ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO = new ConsultCompTerminalExCommonDTO();
    //========================================================================
    /** 접근터미널 상세 */
    private ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO = new ConsultCompTerminalExDetailDTO();


	public ConsultCompTerminalExCommonDTO getConsultCompTerminalExCommonDTO() {
		return consultCompTerminalExCommonDTO;
	}

	public void setConsultCompTerminalExCommonDTO(ConsultCompTerminalExCommonDTO consultCompTerminalExCommonDTO) {
		this.consultCompTerminalExCommonDTO = consultCompTerminalExCommonDTO;
	}

	public ConsultCompTerminalExDetailDTO getConsultCompTerminalExDetailDTO() {
		return consultCompTerminalExDetailDTO;
	}

	public void setConsultCompTerminalExDetailDTO(ConsultCompTerminalExDetailDTO consultCompTerminalExDetailDTO) {
		this.consultCompTerminalExDetailDTO = consultCompTerminalExDetailDTO;
	}

}
