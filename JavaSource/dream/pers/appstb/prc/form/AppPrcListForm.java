package dream.pers.appstb.prc.form;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * ���繮�� - ���
 * @author  javaworker
 * @version $Id: AppPrcListForm.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="appPrcListForm"
 */
public class AppPrcListForm extends BaseForm
{    
    /** ���繮�� ���� */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }
}