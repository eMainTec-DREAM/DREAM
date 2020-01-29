package dream.mgr.workflow.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Wokrflow Page - Detail DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrWorkflowDetailDTO extends BaseDTO
{
    /**회사코드*/
    private String compNo           = "";
    /**결재flow Id*/
    private String wflistId           = "";
    /**결재업무구분 Id*/
    private String workflowTypeId     = "";
    /**결재업무구분명*/
    private String workflowTypeDesc     = "";
    /**승인flow명*/
    private String description    = "";
    /**사용여부 */
    private String isUse    = "";
    /**등록일*/
    private String regDate    = "";
    /**비고*/
    private String remark    = "";
    
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getWflistId()
    {
        return wflistId;
    }
    public void setWflistId(String wflistId)
    {
        this.wflistId = wflistId;
        super.setAuditKey(wflistId);
    }
    public String getWorkflowTypeId()
    {
        return workflowTypeId;
    }
    public void setWorkflowTypeId(String workflowTypeId)
    {
        this.workflowTypeId = workflowTypeId;
    }
    public String getWorkflowTypeDesc()
    {
        return workflowTypeDesc;
    }
    public void setWorkflowTypeDesc(String workflowTypeDesc)
    {
        this.workflowTypeDesc = workflowTypeDesc;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRegDate()
    {
        return regDate;
    }
    public void setRegDate(String regDate)
    {
        this.regDate = CommonUtil.convertDate(regDate);
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
