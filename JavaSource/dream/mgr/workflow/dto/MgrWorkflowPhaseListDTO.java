package dream.mgr.workflow.dto;

import common.bean.BaseDTO;

/**
 * Wokrflow Phase Page - List DTO
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public class MgrWorkflowPhaseListDTO
        extends BaseDTO
{
    /** ȸ���ڵ� */
    private String compNo    = "";
    /** Work flow �ܰ� ID */
    private String wfphaseId = "";
    /** ����flow ID */
    private String wflistId = "";
    
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
    
    
}
