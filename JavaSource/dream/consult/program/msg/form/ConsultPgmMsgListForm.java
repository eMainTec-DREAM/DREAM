package dream.consult.program.msg.form;

import common.struts.BaseForm;
import dream.consult.program.msg.dto.ConsultPgmMsgCommonDTO;

/**
 * �޽��� ����(����, SMS) - ��� form
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="consultPgmMsgListForm"
 */
public class ConsultPgmMsgListForm extends BaseForm
{
    //===============================================================
    /** �޽��� ����  ���� */
    private ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO = new ConsultPgmMsgCommonDTO();

	public ConsultPgmMsgCommonDTO getConsultPgmMsgCommonDTO() {
		return consultPgmMsgCommonDTO;
	}

	public void setConsultPgmMsgCommonDTO(ConsultPgmMsgCommonDTO consultPgmMsgCommonDTO) {
		this.consultPgmMsgCommonDTO = consultPgmMsgCommonDTO;
	}

}
