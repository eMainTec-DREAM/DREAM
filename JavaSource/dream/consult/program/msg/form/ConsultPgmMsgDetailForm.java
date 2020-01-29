package dream.consult.program.msg.form;

import common.struts.BaseForm;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;

/**
 * 메시지 설정(메일, SMS)- 상세 Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmMsgDetailForm"
 */
public class ConsultPgmMsgDetailForm extends BaseForm
{
    //========================================================================
    /** 메시지 설정(메일, SMS) 공통 */
    private ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO = new ConsultPgmMsgCommonDTO();
    //========================================================================
    /** 메시지 설정(메일, SMS) 상세 */
    private ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO = new ConsultPgmMsgDetailDTO();


	public ConsultPgmMsgCommonDTO getConsultPgmMsgCommonDTO() {
		return consultPgmMsgCommonDTO;
	}

	public void setConsultPgmMsgCommonDTO(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO) {
		this.consultPgmMsgCommonDTO = consultPgmMsgCommonDTO;
	}

	public ConsultPgmMsgDetailDTO getConsultPgmMsgDetailDTO() {
		return consultPgmMsgDetailDTO;
	}

	public void setConsultPgmMsgDetailDTO(ConsultPgmMsgDetailDTO consultPgmMsgDetailDTO) {
		this.consultPgmMsgDetailDTO = consultPgmMsgDetailDTO;
	}

}
