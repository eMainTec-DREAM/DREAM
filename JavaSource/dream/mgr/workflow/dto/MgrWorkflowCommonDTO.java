package dream.mgr.workflow.dto;

import common.bean.BaseDTO;
/**
 * Wokrflow Page - 공통 DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrWorkflowCommonDTO extends BaseDTO
{
	/**회사코드*/
	private String compNo 			= "";
	/**결재flow Id*/
    private String wflistId           = "";
    /**Filter 결재업무구분 Id*/
    private String filterWorkflowTypeId   = "";
	/**Filter 결재업무구분명*/
	private String filterWorkflowTypeDesc 	= "";
	/**Filter 승인flow명*/
    private String filterDescription    = "";
    
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
    public String getFilterWorkflowTypeId()
    {
        return filterWorkflowTypeId;
    }
    public void setFilterWorkflowTypeId(String filterWorkflowTypeId)
    {
        this.filterWorkflowTypeId = filterWorkflowTypeId;
    }
    public String getFilterWorkflowTypeDesc()
    {
        return filterWorkflowTypeDesc;
    }
    public void setFilterWorkflowTypeDesc(String filterWorkflowTypeDesc)
    {
        this.filterWorkflowTypeDesc = filterWorkflowTypeDesc;
    }
    public String getFilterDescription()
    {
        return filterDescription;
    }
    public void setFilterDescription(String filterDescription)
    {
        this.filterDescription = filterDescription;
    }
	
}
