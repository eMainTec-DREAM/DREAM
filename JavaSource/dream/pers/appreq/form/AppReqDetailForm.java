package dream.pers.appreq.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 요청문서-상세 Form
 * @author  javaworker
 * @version $Id: AppReqDetailForm.java,v 1.1 2013/08/30 09:13:48 javaworker Exp $
 * @since   1.0
 * @struts.form name="appReqDetailForm"
 */
public class AppReqDetailForm extends BaseForm
{
    /** 요청문서 공통 */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();
    /** 요청문서-상세DTO */
    private AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();

    /** 결재상태 */
    private Collection appStatusOptions = null;
    /** 결재종류 */
    private Collection wfTypeOptions = null;
    
    /** 승인구분 그리드 Combo */
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
