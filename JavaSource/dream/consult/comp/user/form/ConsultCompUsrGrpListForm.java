package dream.consult.comp.user.form;

import common.struts.BaseForm;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="consultCompUsrGrpListForm"
 */
public class ConsultCompUsrGrpListForm extends BaseForm
{    
    //===============================================================
    /** 권한그룹 공통 */
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
