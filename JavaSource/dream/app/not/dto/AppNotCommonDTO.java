package dream.app.not.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������� ���� DTO
 * @author  javaworker
 * @version $Id: AppNotCommonDTO.java,v 1.1 2013/08/30 09:10:26 javaworker Exp $
 * @since   1.0
 * 
 */
public class AppNotCommonDTO extends BaseDTO
{
    /** �����ȣ(Key) 1row ��ȸ�� ��� */
    private String appFlowNo ="";
    
    /** �����ȣ(����) */
    private String filterAppDocNo = "";
    /** ���� */
    private String title = "";
    /** ������� */
    private String appStatus = "";
    /** ��������From */
    private String appDateFrom = "";
    /** ��������To */
    private String appDateTo = "";
    /** �������From */
    private String enterDateFrom = "";
    /** �������To */
    private String enterDateTo = "";
    /** �������� */
    private String wfType = "";
    /** ó������(��Ȯ��/Ȯ��) */
    private String wfAction = "P";
    /** ������ȣ */
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
