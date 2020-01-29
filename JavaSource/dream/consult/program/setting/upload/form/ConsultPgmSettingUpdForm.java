package dream.consult.program.setting.upload.form;

import common.struts.BaseForm;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdDTO;
/**
 * Excel Data Upload - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="consultPgmSettingUpdForm"
 * */

public class ConsultPgmSettingUpdForm extends BaseForm{
	
	private ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO = new ConsultPgmSettingUpdDTO();

	public ConsultPgmSettingUpdDTO getConsultPgmSettingUpdDTO() {
		return consultPgmSettingUpdDTO;
	}

	public void setConsultPgmSettingUpdDTO(ConsultPgmSettingUpdDTO consultPgmSettingUpdDTO) {
		this.consultPgmSettingUpdDTO = consultPgmSettingUpdDTO;
	}
	
}
