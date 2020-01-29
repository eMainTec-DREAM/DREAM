package dream.consult.program.uploaddata.form;

import common.struts.BaseForm;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataColDTO;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ÄÃ·³ - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="consultProgramUploadDataColForm"
 * */
public class ConsultProgramUploadDataColForm extends BaseForm {
    
    private ConsultProgramUploadDataDTO consultProgramUploadDataDTO = new ConsultProgramUploadDataDTO();
    private ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO = new ConsultProgramUploadDataColDTO();

    
    public ConsultProgramUploadDataDTO getConsultProgramUploadDataDTO()
    {
        return consultProgramUploadDataDTO;
    }

    public void setConsultProgramUploadDataDTO(ConsultProgramUploadDataDTO consultProgramUploadDataDTO)
    {
        this.consultProgramUploadDataDTO = consultProgramUploadDataDTO;
    }

    public ConsultProgramUploadDataColDTO getConsultProgramUploadDataColDTO() {
        return consultProgramUploadDataColDTO;
    }

    public void setConsultProgramUploadDataColDTO(ConsultProgramUploadDataColDTO consultProgramUploadDataColDTO) {
        this.consultProgramUploadDataColDTO = consultProgramUploadDataColDTO;
    }
}