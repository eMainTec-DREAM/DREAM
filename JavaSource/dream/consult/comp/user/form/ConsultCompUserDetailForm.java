package dream.consult.comp.user.form;

import common.struts.BaseForm;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUserDetailDTO;

/**
 * Comp User Page - Detail Form
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailForm.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="consultCompUserDetailForm"
 */
public class ConsultCompUserDetailForm extends BaseForm
{
    private ConsultCompUserCommonDTO consultCompUserCommonDTO = new ConsultCompUserCommonDTO();
    private ConsultCompUserDetailDTO consultCompUserDetailDTO = new ConsultCompUserDetailDTO();
    
    public ConsultCompUserCommonDTO getConsultCompUserCommonDTO() {
        return consultCompUserCommonDTO;
    }
    public void setConsultCompUserCommonDTO(ConsultCompUserCommonDTO consultCompUserCommonDTO) {
        this.consultCompUserCommonDTO = consultCompUserCommonDTO;
    }
    public ConsultCompUserDetailDTO getConsultCompUserDetailDTO() {
        return consultCompUserDetailDTO;
    }
    public void setConsultCompUserDetailDTO(ConsultCompUserDetailDTO consultCompUserDetailDTO) {
        this.consultCompUserDetailDTO = consultCompUserDetailDTO;
    }
}
