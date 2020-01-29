package dream.app.not.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검 공통 DTO
 * @author  javaworker
 * @version $Id: AppNotCommonDTO.java,v 1.1 2013/08/30 09:10:26 javaworker Exp $
 * @since   1.0
 * 
 */
public class AppNotCommonDTO extends BaseDTO
{
    /** 결재번호(Key) 1row 조회시 사용 */
    private String appFlowNo ="";
    
    /** 결재번호(필터) */
    private String filterAppDocNo = "";
    /** 제목 */
    private String title = "";
    /** 결재상태 */
    private String appStatus = "";
    /** 결재일자From */
    private String appDateFrom = "";
    /** 결재일자To */
    private String appDateTo = "";
    /** 기안일자From */
    private String enterDateFrom = "";
    /** 기안일자To */
    private String enterDateTo = "";
    /** 결재종류 */
    private String wfType = "";
    /** 처리구분(미확인/확인) */
    private String wfAction = "P";
    /** 문서번호 */
    private String objectNo = "";
    
    public String getFilterAppDocNo()
    {
        return filterAppDocNo;
    }
    public void setFilterAppDocNo(String filterAppDocNo)
    {
        this.filterAppDocNo = filterAppDocNo;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getAppStatus()
    {
        return appStatus;
    }
    public void setAppStatus(String appStatus)
    {
        this.appStatus = appStatus;
    }
    public String getAppDateFrom()
    {
        return appDateFrom;
    }
    public void setAppDateFrom(String appDateFrom)
    {
        this.appDateFrom = CommonUtil.dateToData(appDateFrom);
    }
    public String getAppDateTo()
    {
        return appDateTo;
    }
    public void setAppDateTo(String appDateTo)
    {
        this.appDateTo = CommonUtil.dateToData(appDateTo);
    }
    public String getEnterDateFrom()
    {
        return enterDateFrom;
    }
    public void setEnterDateFrom(String enterDateFrom)
    {
        this.enterDateFrom = CommonUtil.dateToData(enterDateFrom);
    }
    public String getEnterDateTo()
    {
        return enterDateTo;
    }
    public void setEnterDateTo(String enterDateTo)
    {
        this.enterDateTo = CommonUtil.dateToData(enterDateTo);
    }
    public String getWfType()
    {
        return wfType;
    }
    public void setWfType(String wfType)
    {
        this.wfType = wfType;
    }
    public String getObjectNo()
    {
        return objectNo;
    }
    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
    }
    public String getWfAction()
    {
        return wfAction;
    }
    public void setWfAction(String wfAction)
    {
        this.wfAction = wfAction;
    }
    public String getAppFlowNo()
    {
        return appFlowNo;
    }
    public void setAppFlowNo(String appFlowNo)
    {
        this.appFlowNo = appFlowNo;
    }
}
