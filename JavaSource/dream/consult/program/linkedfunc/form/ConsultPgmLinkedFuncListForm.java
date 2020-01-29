package dream.consult.program.linkedfunc.form;

import common.struts.BaseForm;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;


/**
 * PgmLinkedFunc Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultPgmLinkedFuncListForm"
 * */
public class ConsultPgmLinkedFuncListForm extends BaseForm {
    
    private ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO = new ConsultPgmLinkedFuncCommonDTO();

    public ConsultPgmLinkedFuncCommonDTO getConsultPgmLinkedFuncCommonDTO() {
        return consultPgmLinkedFuncCommonDTO;
    }

    public void setConsultPgmLinkedFuncCommonDTO(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO) {
        this.consultPgmLinkedFuncCommonDTO = consultPgmLinkedFuncCommonDTO;
    }
}