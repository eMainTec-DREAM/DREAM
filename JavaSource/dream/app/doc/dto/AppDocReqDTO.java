package dream.app.doc.dto;

import common.bean.BaseDTO;

/**
 * 결재요청 DTO
 * @author  javaworker
 * @version $Id: AppDocReqDTO.java,v 1.2 2014/07/02 04:14:08 pochul2423 Exp $ 
 * @since   1.0
 */
public class AppDocReqDTO extends BaseDTO
{
    /** 결재번호 */
    private String appDocNo = "";
    /** 결재상태 */
    private String appStatus = "";
    /** 기안일자 */
    private String enterDate = "";
    /** 기안자명 */
    private String enterByName = "";
    /** 제목 */
    private String title = "";
    /** 문서종류 */
    private String wfType = "";
    /** 문서번호 */
    private String objectNo = "";
    /** 상세내용 */
    private String remark = "";
    /** 결재의견 */
    private String appDesc = "";
    // 문서 통보처리만 할때 사용
    /** 통보대상자 */
    private String[] appNotUser = null;
    
    public String getAppDesc()
    {
        return appDesc;
    }
    public void setAppDesc(String appDesc)
    {
        this.appDesc = appDesc;
    }
    public String getAppDocNo()
    {
        return appDocNo;
    }
    public void setAppDocNo(String appDocNo)
    {
        this.appDocNo = appDocNo;
    }
    public String getAppStatus()
    {
        return appStatus;
    }
    public void setAppStatus(String appStatus)
    {
        this.appStatus = appStatus;
    }
    public String getEnterDate()
    {
        return enterDate;
    }
    public void setEnterDate(String enterDate)
    {
        this.enterDate = enterDate;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getWfType()
    {
        return wfType;
    }
    public void setWfType(String wfType)
    {
        this.wfType = wfType;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getEnterByName()
    {
        return enterByName;
    }
    public void setEnterByName(String enterByName)
    {
        this.enterByName = enterByName;
    }
    public String getObjectNo()
    {
        return objectNo;
    }
    public void setObjectNo(String objectNo)
    {
        this.objectNo = objectNo;
    }
    public String[] getAppNotUser()
    {
        return appNotUser;
    }
    public void setAppNotUser(String[] appNotUser)
    {
        this.appNotUser = appNotUser;
    }
}