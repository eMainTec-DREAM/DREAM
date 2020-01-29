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
    /** ȸ���ڵ� */
    private String compNo    = "";
    /** Work flow �ܰ� ID */
    private String wfphaseId = "";
    /** ����flow id */
    private String wflistId = "";
    /** ������� */
    private String stepNum = "";
    /** ����� */
    private String description = "";
    /** ���籸�� */
    private String approvalType = "";
    /** ���籸��desc */
    private String approvalTypeDesc = "";
    /** ������ */
    private String gradeType = "";
    /** ������desc */
    private String gradeTypeDesc = "";
    /** ��뿩�� */
    private String isUse = "";
    /** ��� */
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
