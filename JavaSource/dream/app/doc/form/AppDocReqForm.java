package dream.app.doc.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.app.doc.dto.AppDocReqDTO;
//import dream.app.line.dto.FlowDtlDTO;

/**
 * �����û Form
 * @author  javaworker
 * @version $Id: AppDocReqForm.java,v 1.1 2013/08/30 09:14:54 javaworker Exp $
 * @since   1.0
 * @struts.form name="appDocReqForm"
 */
public class AppDocReqForm extends BaseForm
{
    /** ������DTO */
    private AppDocReqDTO appDocReqDTO = new AppDocReqDTO();

    /** ������� */
    private Collection appStatusOptions = null;
    /** �������� */
    private Collection wfTypeOptions = null;
    
    /** ���α��� �׸��� Combo */
    private String appTypeCombo = "";
    
    /** ������ grid list */
//    private List flowDtlDTOList = ListUtils.lazyList(new ArrayList(), getDtoFactory(FlowDtlDTO.class));
    
    public AppDocReqDTO getAppDocReqDTO()
    {
        return appDocReqDTO;
    }

    public void setAppDocReqDTO(AppDocReqDTO appDocReqDTO)
    {
        this.appDocReqDTO = appDocReqDTO;
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

//    public List getFlowDtlDTOList()
//    {
//        return flowDtlDTOList;
//    }
//
//    public void setFlowDtlDTOList(List flowDtlDTOList)
//    {
//        this.flowDtlDTOList = flowDtlDTOList;
//    }
}
