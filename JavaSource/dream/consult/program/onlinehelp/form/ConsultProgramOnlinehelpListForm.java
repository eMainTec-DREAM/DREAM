package dream.consult.program.onlinehelp.form;

import common.struts.BaseForm;
import dream.consult.program.onlinehelp.dto.ConsultProgramOnlinehelpCommonDTO;

/**
 * ���� - ��� form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultProgramOnlinehelpListForm"
 */
public class ConsultProgramOnlinehelpListForm extends BaseForm
{    
    //===============================================================
    /** ȭ�� ���� */
    private ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO = new ConsultProgramOnlinehelpCommonDTO();
    
	public ConsultProgramOnlinehelpCommonDTO getConsultProgramOnlinehelpCommonDTO() {
		return consultProgramOnlinehelpCommonDTO;
	}

	public void setConsultProgramOnlinehelpCommonDTO(ConsultProgramOnlinehelpCommonDTO consultProgramOnlinehelpCommonDTO) {
		this.consultProgramOnlinehelpCommonDTO = consultProgramOnlinehelpCommonDTO;
	}
}
