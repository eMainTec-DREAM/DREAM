package dream.mgr.workflow.dto;

import common.bean.BaseDTO;

/**
 * Wokrflow Phase Page - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrWorkflowPhaseDetailDTO extends BaseDTO
{
    /** 회사코드 */
    private String compNo    = "";
    /** Work flow 단계 ID */
    private String wfphaseId = "";
    /** 결재flow id */
    private String wflistId = "";
    /** 진행순서 */
    private String stepNum = "";
    /** 결재명 */
    private String description = "";
    /** 결재구분 */
    private String approvalType = "";
    /** 결재구분desc */
    private String approvalTypeDesc = "";
    /** 결재자 */
    private String gradeType = "";
    /** 결재자desc */
    private String gradeTypeDesc = "";
    /** 사용여부 */
    private String isUse = "";
    /** 비고 */
    private String remark = "";
    
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getWfphaseId()
    {
        return wfphaseId;
    }
    public void setWfphaseId(String wfphaseId)
    {
        this.wfphaseId = wfphaseId;
        super.setAuditKey(wfphaseId);
    }
    public String getWflistId()
    {
        return wflistId;
    }
    public void setWflistId(String wflistId)
    {
        this.wflistId = wflistId;
    }
    public String getStepNum()
    {
        return stepNum;
    }
    public void setStepNum(String stepNum)
    {
        this.stepNum = stepNum;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getApprovalType()
    {
        return approvalType;
    }
    public void setApprovalType(String approvalType)
    {
        this.approvalType = approvalType;
    }
    public String getApprovalTypeDesc()
    {
        return approvalTypeDesc;
    }
    public void setApprovalTypeDesc(String approvalTypeDesc)
    {
        this.approvalTypeDesc = approvalTypeDesc;
    }
    public String getGradeType()
    {
        return gradeType;
    }
    public void setGradeType(String gradeType)
    {
        this.gradeType = gradeType;
    }
    public String getGradeTypeDesc()
    {
        return gradeTypeDesc;
    }
    public void setGradeTypeDesc(String gradeTypeDesc)
    {
        this.gradeTypeDesc = gradeTypeDesc;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
}
