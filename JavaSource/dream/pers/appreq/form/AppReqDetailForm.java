package dream.pers.appreq.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * ��û����-�� Form
 * @author  javaworker
 * @version $Id: AppReqDetailForm.java,v 1.1 2013/08/30 09:13:48 javaworker Exp $
 * @since   1.0
 * @struts.form name="appReqDetailForm"
 */
public class AppReqDetailForm extends BaseForm
{
    /** ��û���� ���� */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** ��û����-��DTO */
    private AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();

    /** ������� */
    private Collection appStatusOptions = null;
    /** �������� */
    private Collection wfTypeOptions = null;
    
    /** ���α��� �׸��� Combo */
    private String appTypeCombo = "";
    
    public AppReqDetailDTO getAppReqDetailDTO()
    {
        return appReqDetailDTO;
    }

    public void setAppReqDetailDTO(AppReqDetailDTO appReqDetailDTO)
    {
        this.appReqDetailDTO = appReqDetailDTO;
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
