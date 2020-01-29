package dream.consult.program.msg.form;

import common.struts.BaseForm;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;
import dream.consult.program.msg.dto.ConsultPgmMsgDetailDTO;

/**
 * �޽��� ����(����, SMS)- �� Form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmMsgDetailForm"
 */
public class ConsultPgmMsgDetailForm extends BaseForm
{
    //========================================================================
    /** �޽��� ����(����, SMS) ���� */
    private ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO = new ConsultPgmMsgCommonDTO();
    //========================================================================
    /** �޽��� ����(����, SMS) �� */
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
