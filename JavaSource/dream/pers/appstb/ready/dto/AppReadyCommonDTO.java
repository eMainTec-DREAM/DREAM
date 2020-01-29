package dream.pers.appstb.ready.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  javaworker
 * @version $Id: AppReadyCommonDTO.java,v 1.1 2013/08/30 09:11:12 javaworker Exp $
 * @since   1.0
 * 
 */
public class AppReadyCommonDTO extends BaseDTO
{
    /** Key Id */
    private String apprusrId    = "";
    /** Header Key ID  */
    private String apprlistId   = "";
    /** 요청이랒 시작  */
    private String startDate    = "";
    /** 요청일자 끝 */
    private String endDate      = "";
    /** 결재자 */
    private String apprBy       = "";
    /** 결재일자 */
    private String apprDate     = "";
    /** 결재의견 */
    private String remark       = "";
    
    
    /** 결재번호(Key) */
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
    /** 결재처리상태 */
    private String wfStatus = "";
    
    private String wfStatusDesc = "";
    /** 문서번호 */
    private String objectNo = "";
    
    
    public String getWfStatusDesc()
    {
        return wfStatusDesc;
    }
    public void setWfStatusDesc(String wfStatusDesc)
    {
        this.wfStatusDesc = wfStatusDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getApprBy()
    {
        return apprBy;
    }
    public void setApprBy(String apprBy)
    {
        this.apprBy = apprBy;
    }
    public String getApprDate()
    {
        return apprDate;
    }
    public void setApprDate(String apprDate)
    {
        this.apprDate = apprDate;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    public String getApprlistId()
    {
        return apprlistId;
    }
    public void setApprlistId(String apprlistId)
    {
        this.apprlistId = apprlistId;
    }
    public String getApprusrId()
    {
        return apprusrId;
    }
    public void setApprusrId(String apprusrId)
    {
        this.apprusrId = apprusrId;
        super.setAuditKey(apprusrId);
    }
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
    public String getWfStatus()
    {
        return wfStatus;
    }
    public void setWfStatus(String wfStatus)
    {
        this.wfStatus = wfStatus;
    }
    public String getObjectNo()
    {
        return objectNo;
    }
    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
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
