package dream.consult.program.setting.upload.form;

import common.file.FileForm;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;

/**
 * 엑셀업로드- 상세 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="consultPgmSettingUpdFileForm"
 */
public class ConsultPgmSettingUpdFileForm extends FileForm
{
    //========================================================================
    /** 메뉴 상세 */
    private ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO = new ConsultPgmSettingUpdFileDTO();
    /** 파일명 */
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
