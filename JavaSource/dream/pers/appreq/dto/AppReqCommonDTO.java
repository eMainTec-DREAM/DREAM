package dream.pers.appreq.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  javaworker
 * @version $Id: AppReqCommonDTO.java,v 1.2 2014/03/07 05:35:55 javaworker Exp $
 * @since   1.0
 * 
 */
public class AppReqCommonDTO extends BaseDTO
{
    /** 문서ID */
    private String objectId = "";
    /** 결제구분 */
    private String apprType = "";
    /** 결재자 ID */
    private String apprusrId = "";
    /** 결재 ID */
    private String apprlistId = "";
    
    
    /** 결재번호(Key) */
    private String appFlowNo = "";
    
    /** 결재번호(필터) */
    private String filterAppDocNo = "";
    /** 제목 */
    private String title = "";
    /** 결재상태 */
    private String appStatus = "";
    /** 기안일자From */
    private String enterDateFrom = "";
    /** 기안일자To */
    private String enterDateTo = "";
    /** 결재종류 */
    private String wfType = "";
    /** 문서번호 */
    private String objectNo = "";
    /** 결재요청화면인지 결재조회화면인지 체크 */
    private String isAppHist = "";
    
    public String getIsAppHist() {
		return isAppHist;
	}
	public void setIsAppHist(String isAppHist) {
		this.isAppHist = isAppHist;
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
    public String getObjectId()
    {
        return objectId;
    }
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }
    public String getApprType()
    {
        return apprType;
    }
    public void setApprType(String apprType)
    {
        this.apprType = apprType;
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
    
    
    public String getAppFlowNo()
    {
        return appFlowNo;
    }
    public void setAppFlowNo(String appFlowNo)
    {
        this.appFlowNo = appFlowNo;
    }
}
