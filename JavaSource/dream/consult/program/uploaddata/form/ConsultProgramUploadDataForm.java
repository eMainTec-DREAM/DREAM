package dream.consult.program.uploaddata.form;

import common.struts.BaseForm;
import dream.consult.program.uploaddata.dto.ConsultProgramUploadDataDTO;

/**
 * ���ε嵥��Ÿ - ��� form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultProgramUploadDataForm"
 */
public class ConsultProgramUploadDataForm extends BaseForm
{    
    //===============================================================
    /** ȭ�� ���� */
    private ConsultProgramUploadDataDTO consultProgramUploadDataDTO = new ConsultProgramUploadDataDTO();
    
	public ConsultProgramUploadDataDTO getConsultProgramUploadDataDTO() {
		return consultProgramUploadDataDTO;
	}

	public void setConsultProgramUploadDataDTO(ConsultProgramUploadDataDTO consultProgramUploadDataDTO) {
		this.consultProgramUploadDataDTO = consultProgramUploadDataDTO;
	}
}
