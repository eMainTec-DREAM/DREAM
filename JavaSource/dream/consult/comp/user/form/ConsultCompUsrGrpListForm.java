package dream.consult.comp.user.form;

import common.struts.BaseForm;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * ���ѱ׷� - ��� form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompUsrGrpListForm"
 */
public class ConsultCompUsrGrpListForm extends BaseForm
{    
    //===============================================================
    /** ���ѱ׷� ���� */
    private ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO = new ConsultCompUsrGrpCommonDTO();

    public ConsultCompUsrGrpCommonDTO getConsultCompUsrGrpCommonDTO()
    {
        return consultCompUsrGrpCommonDTO;
    }

    public void setConsultCompUsrGrpCommonDTO(
            ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO)
    {
        this.consultCompUsrGrpCommonDTO = consultCompUsrGrpCommonDTO;
    }
    
}
