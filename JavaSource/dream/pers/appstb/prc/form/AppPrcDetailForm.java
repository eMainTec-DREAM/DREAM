package dream.pers.appstb.prc.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * ���繮��-�� Form
 * @author  javaworker
 * @version $Id: AppPrcDetailForm.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
 * @since   1.0
 * @struts.form name="appPrcDetailForm"
 */
public class AppPrcDetailForm extends BaseForm
{
    /** ���繮�� ���� */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** ���繮��-��DTO */
    private AppPrcDetailDTO appPrcDetailDTO = new AppPrcDetailDTO();

    /** ������� */
    private Collection appStatusOptions = null;
    /** �������� */
    private Collection wfTypeOptions = null;
    
    /** ���α��� �׸��� Combo */
    private String appTypeCombo = "";
    
    public AppPrcDetailDTO getAppPrcDetailDTO()
    {
        return appPrcDetailDTO;
    }

    public void setAppPrcDetailDTO(AppPrcDetailDTO appPrcDetailDTO)
    {
        this.appPrcDetailDTO = appPrcDetailDTO;
    }

    public Collection getWfTypeOptions()
    {
        return wfTypeOptions;
    }

    public void setWfTypeOptions(Collection wfTypeOptions)
    {
        this.wfTypeOptions = wfTypeOptions;
    }

    public Collection getAppStatusOptions()
    {
        return appStatusOptions;
    }

    public void setAppStatusOptions(Collection appStatusOptions)
    {
        this.appStatusOptions = appStatusOptions;
    }

    public String getAppTypeCombo()
    {
        return appTypeCombo;
    }

    public void setAppTypeCombo(String appTypeCombo)
    {
        this.appTypeCombo = appTypeCombo;
    }

    public AppReqCommonDTO getAppReqCommonDTO()
    {
        return appReqCommonDTO;
    }

    public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO)
    {
        this.appReqCommonDTO = appReqCommonDTO;
    }
    
}
