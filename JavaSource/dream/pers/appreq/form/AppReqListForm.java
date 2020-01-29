package dream.pers.appreq.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;

/**
 * ��û���� - ���
 * @author  javaworker
 * @version $Id: AppReqListForm.java,v 1.1 2013/08/30 09:13:48 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="appReqListForm"
 */
public class AppReqListForm extends BaseForm
{    
    /** ��û���� ���� */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

    /** ������� */
    private Collection appStatusOptions = null;
    /** �������� */
    private Collection wfTypeOptions = null;
    
    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }
    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }
    public Collection getAppStatusOptions()
    {
        return appStatusOptions;
    }
    public void setAppStatusOptions(Collection appStatusOptions)
    {
        this.appStatusOptions = appStatusOptions;
    }
    public Collection getWfTypeOptions()
    {
        return wfTypeOptions;
    }
    public void setWfTypeOptions(Collection wfTypeOptions)
    {
        this.wfTypeOptions = wfTypeOptions;
    }
}