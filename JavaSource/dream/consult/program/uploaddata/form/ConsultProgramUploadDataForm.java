package dream.consult.program.uploaddata.form;

import common.struts.BaseForm;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * 업로드데이타 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultProgramUploadDataForm"
 */
public class ConsultProgramUploadDataForm extends BaseForm
{    
    //===============================================================
    /** 화면 공통 */
    private ConsultProgramUploadDataDTO consultProgramUploadDataDTO = new ConsultProgramUploadDataDTO();
    
	public ConsultProgramUploadDataDTO getConsultProgramUploadDataDTO() {
		return consultProgramUploadDataDTO;
	}

	public void setConsultProgramUploadDataDTO(ConsultProgramUploadDataDTO consultProgramUploadDataDTO) {
		this.consultProgramUploadDataDTO = consultProgramUploadDataDTO;
	}
}
