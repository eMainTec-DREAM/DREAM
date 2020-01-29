package dream.consult.program.linkedfunc.form;

import common.struts.BaseForm;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncCommonDTO;
import dream.consult.program.linkedfunc.dto.ConsultPgmLinkedFuncDetailDTO;

/**
 * PgmLinkedFunc Page - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultPgmLinkedFuncDetailForm"
 */
public class ConsultPgmLinkedFuncDetailForm extends BaseForm
{
    private ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO = new ConsultPgmLinkedFuncCommonDTO();
    private ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO = new ConsultPgmLinkedFuncDetailDTO();
    
    public ConsultPgmLinkedFuncCommonDTO getConsultPgmLinkedFuncCommonDTO() {
        return consultPgmLinkedFuncCommonDTO;
    }
    public void setConsultPgmLinkedFuncCommonDTO(ConsultPgmLinkedFuncCommonDTO consultPgmLinkedFuncCommonDTO) {
        this.consultPgmLinkedFuncCommonDTO = consultPgmLinkedFuncCommonDTO;
    }
    public ConsultPgmLinkedFuncDetailDTO getConsultPgmLinkedFuncDetailDTO() {
        return consultPgmLinkedFuncDetailDTO;
    }
    public void setConsultPgmLinkedFuncDetailDTO(ConsultPgmLinkedFuncDetailDTO consultPgmLinkedFuncDetailDTO) {
        this.consultPgmLinkedFuncDetailDTO = consultPgmLinkedFuncDetailDTO;
    }
}
