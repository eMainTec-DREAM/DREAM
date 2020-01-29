package dream.mgr.workflow.dto;

import common.bean.BaseDTO;
/**
 * Wokrflow Page - ���� DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrWorkflowCommonDTO extends BaseDTO
{
	/**ȸ���ڵ�*/
	private String compNo 			= "";
	/**����flow Id*/
    private String wflistId           = "";
    /**Filter ����������� Id*/
    private String filterWorkflowTypeId   = "";
	/**Filter ����������и�*/
	private String filterWorkflowTypeDesc 	= "";
	/**Filter ����flow��*/
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
