package dream.consult.program.uploaddata.form;

import common.struts.BaseForm;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataScriptDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ÄÃ·³ - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultProgramUploadDataScriptForm"
 * */
public class ConsultProgramUploadDataScriptForm extends BaseForm {
    
    private ConsultProgramUploadDataDTO consultProgramUploadDataDTO = new ConsultProgramUploadDataDTO();
    private ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO = new ConsultProgramUploadDataScriptDTO();

    
    public ConsultProgramUploadDataDTO getConsultProgramUploadDataDTO()
    {
        return consultProgramUploadDataDTO;
    }

    public void setConsultProgramUploadDataDTO(ConsultProgramUploadDataDTO consultProgramUploadDataDTO)
    {
        this.consultProgramUploadDataDTO = consultProgramUploadDataDTO;
    }

    public ConsultProgramUploadDataScriptDTO getConsultProgramUploadDataScriptDTO() {
        return consultProgramUploadDataScriptDTO;
    }

    public void setConsultProgramUploadDataScriptDTO(ConsultProgramUploadDataScriptDTO consultProgramUploadDataScriptDTO) {
        this.consultProgramUploadDataScriptDTO = consultProgramUploadDataScriptDTO;
    }
}