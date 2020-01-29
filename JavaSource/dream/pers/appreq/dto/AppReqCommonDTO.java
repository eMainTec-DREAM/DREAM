package dream.pers.appreq.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  javaworker
 * @version $Id: AppReqCommonDTO.java,v 1.2 2014/03/07 05:35:55 javaworker Exp $
 * @since   1.0
 * 
 */
public class AppReqCommonDTO extends BaseDTO
{
    /** ����ID */
    private String objectId = "";
    /** �������� */
    private String apprType = "";
    /** ������ ID */
    private String apprusrId = "";
    /** ���� ID */
    private String apprlistId = "";
    
    
    /** �����ȣ(Key) */
    private String appFlowNo = "";
    
    /** �����ȣ(����) */
    private String filterAppDocNo = "";
    /** ���� */
    private String title = "";
    /** ������� */
    private String appStatus = "";
    /** �������From */
    private String enterDateFrom = "";
    /** �������To */
    private String enterDateTo = "";
    /** �������� */
    private String wfType = "";
    /** ������ȣ */
    private String objectNo = "";
    /** �����ûȭ������ ������ȸȭ������ üũ */
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
