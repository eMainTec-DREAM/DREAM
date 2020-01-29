package dream.app.not.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import common.struts.BaseForm;
import dream.app.not.dto.AppNotCommonDTO;
import dream.app.not.dto.AppNotDTO;

/**
 * 통보문서 - 목록
 * @author  javaworker
 * @version $Id: AppNotListForm.java,v 1.1 2013/08/30 09:13:44 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="appNotListForm"
 */
public class AppNotListForm extends BaseForm
{    
    /** 통보문서 확인 Grid List */
    private List appNotList = ListUtils.lazyList(new ArrayList(), getDtoFactory(AppNotDTO.class));
    
    /** 통보문서 공통 */
    private AppNotCommonDTO appNotCommonDTO = new AppNotCommonDTO();

    /** 결재상태 */
    private Collection appStatusOptions = null;
    /** 결재종류 */
    private Collection wfTypeOptions = null;
    
    public AppNotCommonDTO getAppNotCommonDTO()
    {
        return appNotCommonDTO;
    }
    public void setAppNotCommonDTO(AppNotCommonDTO appNotCommonDTO)
    {
        this.appNotCommonDTO = appNotCommonDTO;
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
    public List getAppNotList()
    {
        return appNotList;
    }
    public void setAppNotList(List appNotList)
    {
        this.appNotList = appNotList;
    }
}