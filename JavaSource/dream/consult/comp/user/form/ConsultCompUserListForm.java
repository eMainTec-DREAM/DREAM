package dream.consult.comp.user.form;

import common.struts.BaseForm;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;


/**
 * CompUser Page - List Form
 * @author youngjoo38
 * @version $Id: ConsultCompUserListForm.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="consultCompUserListForm"
 * */
public class ConsultCompUserListForm extends BaseForm {
    
    private ConsultCompUserCommonDTO consultCompUserCommonDTO = new ConsultCompUserCommonDTO();

    public ConsultCompUserCommonDTO getConsultCompUserCommonDTO() {
        return consultCompUserCommonDTO;
    }

    public void setConsultCompUserCommonDTO(ConsultCompUserCommonDTO consultCompUserCommonDTO) {
        this.consultCompUserCommonDTO = consultCompUserCommonDTO;
    }
}