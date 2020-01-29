package dream.pers.appstb.prc.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcCommonDTO;
import dream.pers.appstb.prc.dto.AppPrcDetailDTO;

/**
 * 결재문서-상세 Form
 * @author  javaworker
 * @version $Id: AppPrcDetailForm.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
 * @since   1.0
 * @struts.form name="appPrcDetailForm"
 */
public class AppPrcDetailForm extends BaseForm
{
    /** 결재문서 공통 */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** 결재문서-상세DTO */
    private AppPrcDetailDTO appPrcDetailDTO = new AppPrcDetailDTO();

    /** 결재상태 */
    private Collection appStatusOptions = null;
    /** 결재종류 */
    private Collection wfTypeOptions = null;
    
    /** 승인구분 그리드 Combo */
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
