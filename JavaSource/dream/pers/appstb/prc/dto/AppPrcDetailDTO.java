package dream.pers.appstb.prc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 결재 상신 DTO
 * @author  javaworker
 * @version $Id: AppPrcDetailDTO.java,v 1.1 2013/08/30 09:11:12 javaworker Exp $ 
 * @since   1.0
 */
public class AppPrcDetailDTO extends BaseDTO
{
    /** 결재자명 */
    private String apprByName   = "";
    /** 결재자 */
    private String apprBy       = "";
    /** 결재순서 */
    private String apprSeq      = "";
    /** 직위 */
    private String grade        = "";
    /** 부서 */
    private String deptName     = "";
    /** KEY ID */
    private String apprusrId     = "";
    /** Header Key ID */
    private String apprlistId    = "";
    
    /** 결재번호 */
    private String appDocNo = "";
    /** 결재상태 */
    private String appStatus = "";
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
    /** 결재상세번호 */
    private String appFlowNo = "";
    
    //=====================================
    // 결재 처리시 사용
    /** 처리상태(승인/반려) */
    private String wfStatus = "";
    /** 처리구분(완료/대상/대기) */
    private String wfAction = "";
    /** 결재작업자구분 */
    private String apprUsrTypeDesc = "";
    private String apprUsrTypeId = "";
    
    
    
    

    
	public String getApprUsrTypeDesc() {
		return apprUsrTypeDesc;
	}
	public void setApprUsrTypeDesc(String apprUsrTypeDesc) {
		this.apprUsrTypeDesc = apprUsrTypeDesc;
	}
	public String getApprUsrTypeId() {
		return apprUsrTypeId;
	}
	public void setApprUsrTypeId(String apprUsrTypeId) {
		this.apprUsrTypeId = apprUsrTypeId;
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
    }
    public String getApprByName()
    {
        return apprByName;
    }
    public void setApprByName(String apprByName)
    {
        this.apprByName = apprByName;
    }
    public String getApprBy()
    {
        return apprBy;
    }
    public void setApprBy(String apprBy)
    {
        this.apprBy = apprBy;
    }
    public String getApprSeq()
    {
        return apprSeq;
    }
    public void setApprSeq(String apprSeq)
    {
        this.apprSeq = CommonUtil.convertMoney(apprSeq);
    }
    public String getGrade()
    {
        return grade;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }
    public String getDeptName()
    {
        return deptName;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
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
    public String getAppDesc()
    {
        return appDesc;
    }
    public void setAppDesc(String appDesc)
    {
        this.appDesc = appDesc;
    }
    public String getAppFlowNo()
    {
        return appFlowNo;
    }
    public void setAppFlowNo(String appFlowNo)
    {
        this.appFlowNo = appFlowNo;
    }
    public String getWfStatus()
    {
        return wfStatus;
    }
    public void setWfStatus(String wfStatus)
    {
        this.wfStatus = wfStatus;
    }
    public String getWfAction()
    {
        return wfAction;
    }
    public void setWfAction(String wfAction)
    {
        this.wfAction = wfAction;
    }
}