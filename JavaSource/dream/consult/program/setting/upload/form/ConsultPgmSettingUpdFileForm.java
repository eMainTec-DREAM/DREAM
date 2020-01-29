package dream.consult.program.setting.upload.form;

import common.file.FileForm;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;

/**
 * �������ε�- �� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultPgmSettingUpdFileForm"
 */
public class ConsultPgmSettingUpdFileForm extends FileForm
{
    //========================================================================
    /** �޴� �� */
    private ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = new ConsultPgmSettingUpdFileDTO();
    /** ���ϸ� */
    private String[] fileNames = null;
    
    
    public String[] getFileNames()
    {
        return fileNames;
    }

    public void setFileNames(String[] fileNames)
    {
        this.fileNames = fileNames;
    }
    
	public ConsultPgmSettingUpdFileDTO getConsultPgmSettingUpdFileDTO() {
		return consultPgmSettingUpdFileDTO;
	}

	public void setConsultPgmSettingUpdFileDTO(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO) {
		this.consultPgmSettingUpdFileDTO = consultPgmSettingUpdFileDTO;
	}

}
