package dream.pers.appreq.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� ��� DTO
 * @author  javaworker
 * @version $Id: AppReqDetailDTO.java,v 1.1 2013/08/30 09:14:12 javaworker Exp $ 
 * @since   1.0
 */
public class AppReqDetailDTO extends BaseDTO
{
    /** ����ID */
    private String objectId = "";
    /** �������� */
    private String apprType = "";
    /** ����ID */
    private String apprlistId = "";
    /** ��û���� */
    private String reqDate = "";
    /** ��û�� */
    private String reqBy = "";
    /** ��û�ڸ� */
    private String reqByName = "";
    /** �����������[W:�ۼ��� R:�����û P:�������� C:����Ϸ� D:����ݷ�] */
    private String apprStatus = "";
    /** ���к�������� */
    private String parentStatus = "";
    /** JSON Parameters For Extra Info. */
    private String jsonParam    = "";
    
    
    /** �����ȣ */
    private String appDocNo = "";
    /** ������� */
    private String appStatus = "";
    /** ���� */
    private String title = "";
    /** �������� */
    private String wfType = "";
    /** ������ȣ */
    private String objectNo = "";
    /** �󼼳��� */
    private String remark = "";
    /** ����󼼹�ȣ */
    private String appFlowNo = "";
    
    
    public String getJsonParam()
    {
        return jsonParam;
    }
    public void setJsonParam(String jsonParam)
    {
        this.jsonParam = jsonParam;
    }
    public String getReqByName()
    {
        return reqByName;
    }
    public void setReqByName(String reqByName)
    {
        this.reqByName = reqByName;
    }
    public String getReqDate()
    {
        return reqDate;
    }
    public void setReqDate(String reqDate)
    {
        this.reqDate = CommonUtil.convertDate(reqDate);
    }
    public String getReqBy()
    {
        return reqBy;
    }
    public void setReqBy(String reqBy)
    {
        this.reqBy = reqBy;
    }
    public String getApprStatus()
    {
        return apprStatus;
    }
    public void setApprStatus(String apprStatus)
    {
        this.apprStatus = apprStatus;
    }
    public String getApprlistId()
    {
        return apprlistId;
    }
    public void setApprlistId(String apprlistId)
    {
        this.apprlistId = apprlistId;
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
    public String getParentStatus()
    {
        return parentStatus;
    }
    public void setParentStatus(String parentStatus)
    {
        this.parentStatus = parentStatus;
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