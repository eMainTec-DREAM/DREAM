package dream.consult.program.msg.form;

import common.struts.BaseForm;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;

/**
 * 메시지 설정(메일, SMS) - 목록 form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmMsgListForm"
 */
public class ConsultPgmMsgListForm extends BaseForm
{
    //===============================================================
    /** 메시지 설정  공통 */
    private ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO = new ConsultPgmMsgCommonDTO();

	public ConsultPgmMsgCommonDTO getConsultPgmMsgCommonDTO() {
		return consultPgmMsgCommonDTO;
	}

	public void setConsultPgmMsgCommonDTO(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO) {
		this.consultPgmMsgCommonDTO = consultPgmMsgCommonDTO;
	}

}
