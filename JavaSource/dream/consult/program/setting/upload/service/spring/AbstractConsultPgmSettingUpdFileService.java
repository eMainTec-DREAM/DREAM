package dream.consult.program.setting.upload.service.spring;

import dream.consult.program.setting.upload.service.ConsultPgmSettingUpdFileService;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractConsultPgmSettingUpdFileService implements ConsultPgmSettingUpdFileService
{    
	protected ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService;
	
    public AbstractConsultPgmSettingUpdFileService(ConsultPgmSettingUpdFileService consultPgmSettingUpdFileService)
    {
        this.consultPgmSettingUpdFileService = consultPgmSettingUpdFileService;
    }
	
	
}
