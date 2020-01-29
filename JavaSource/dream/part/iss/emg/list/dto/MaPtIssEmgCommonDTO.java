package dream.part.iss.emg.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtIssEmgCommonDTO extends BaseDTO
{
    /** ���μ� */
    private String issueDept	= "";
    /** ���μ��� */
    private String issueDeptDesc	= "";
    /** ������� From */
    private String issueDateFrom	= "";
    /** ������� To */
    private String issueDateTo		= "";
    /** ǰ��/�԰� */
    private String partDesc			= "";
    /** ������ */
    private String recBy			= "";
    /** �����ڸ� */
    private String recByName		= "";
    /** ������ */
    private String ptemgissStatus	= "";
    
    private String ptemgissStatusDesc	= "";
    /** �۾�������� */
    private String ptemgTaskStatus	= "";
    
    private String ptemgTaskStatusDesc	= "";
    /** ����ȣ */
    private String ptemgisslistId	= "";
    /** ����ȣ Filter */
    private String filterPtemgisslistId	= "";
    /** ����ûId */
    private String ptemgissreqId   = "";
    /**Filter ��û�� ID */
    private String filterReqById        = "";
    /**Filter ��û��*/
    private String filterReqByDesc      = "";
    /**Filter ��û�μ� ID*/
    private String filterReqDeptId      = "";
    /**Filter ��û�μ� */
    private String filterReqDeptDesc        = "";
    /**Filter ��û ������ */
    private String filterReqStartDate       = "";
    /**Filter ��û ��������*/
    private String filterReqEndDate         = "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
    /** ����-�ڽ�Ʈ���� ID */
    private String filterCtctrId            = "";
    /** ����-�ڽ�Ʈ���� DESC */
    private String filterCtctrDesc          = "";
    
    
	public String getFilterCtctrId()
    {
        return filterCtctrId;
    }
    public void setFilterCtctrId(String filterCtctrId)
    {
        this.filterCtctrId = filterCtctrId;
    }
    public String getFilterCtctrDesc()
    {
        return filterCtctrDesc;
    }
    public void setFilterCtctrDesc(String filterCtctrDesc)
    {
        this.filterCtctrDesc = filterCtctrDesc;
    }
    public String getFilterReqById()
    {
        return filterReqById;
    }
    public void setFilterReqById(String filterReqById)
    {
        this.filterReqById = filterReqById;
    }
    public String getFilterReqByDesc()
    {
        return filterReqByDesc;
    }
    public void setFilterReqByDesc(String filterReqByDesc)
    {
        this.filterReqByDesc = filterReqByDesc;
    }
    public String getFilterReqDeptId()
    {
        return filterReqDeptId;
    }
    public void setFilterReqDeptId(String filterReqDeptId)
    {
        this.filterReqDeptId = filterReqDeptId;
    }
    public String getFilterReqDeptDesc()
    {
        return filterReqDeptDesc;
    }
    public void setFilterReqDeptDesc(String filterReqDeptDesc)
    {
        this.filterReqDeptDesc = filterReqDeptDesc;
    }
    public String getFilterReqStartDate()
    {
        return filterReqStartDate;
    }
    public void setFilterReqStartDate(String filterReqStartDate)
    {
        this.filterReqStartDate = filterReqStartDate;
    }
    public String getFilterReqEndDate()
    {
        return filterReqEndDate;
    }
    public void setFilterReqEndDate(String filterReqEndDate)
    {
        this.filterReqEndDate = filterReqEndDate;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getPtemgissreqId()
    {
        return ptemgissreqId;
    }
    public void setPtemgissreqId(String ptemgissreqId)
    {
        this.ptemgissreqId = ptemgissreqId;
    }
    public String getPtemgissStatusDesc() {
		return ptemgissStatusDesc;
	}
	public void setPtemgissStatusDesc(String ptemgissStatusDesc) {
		this.ptemgissStatusDesc = ptemgissStatusDesc;
	}
	public String getPtemgTaskStatusDesc() {
		return ptemgTaskStatusDesc;
	}
	public void setPtemgTaskStatusDesc(String ptemgTaskStatusDesc) {
		this.ptemgTaskStatusDesc = ptemgTaskStatusDesc;
	}
	public String getFilterPtemgisslistId() {
		return filterPtemgisslistId;
	}
	public void setFilterPtemgisslistId(String filterPtemgisslistId) {
		this.filterPtemgisslistId = filterPtemgisslistId;
	}
	public String getIssueDept() {
		return issueDept;
	}
	public void setIssueDept(String issueDept) {
		this.issueDept = issueDept;
	}
	public String getIssueDeptDesc() {
		return issueDeptDesc;
	}
	public void setIssueDeptDesc(String issueDeptDesc) {
		this.issueDeptDesc = issueDeptDesc;
	}
	public String getIssueDateFrom() {
		return issueDateFrom;
	}
	public void setIssueDateFrom(String issueDateFrom) {
		this.issueDateFrom = issueDateFrom;
	}
	public String getIssueDateTo() {
		return issueDateTo;
	}
	public void setIssueDateTo(String issueDateTo) {
		this.issueDateTo = issueDateTo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getRecBy() {
		return recBy;
	}
	public void setRecBy(String recBy) {
		this.recBy = recBy;
	}
	public String getRecByName() {
		return recByName;
	}
	public void setRecByName(String recByName) {
		this.recByName = recByName;
	}
	public String getPtemgissStatus() {
		return ptemgissStatus;
	}
	public void setPtemgissStatus(String ptemgissStatus) {
		this.ptemgissStatus = ptemgissStatus;
	}
	public String getPtemgTaskStatus() {
		return ptemgTaskStatus;
	}
	public void setPtemgTaskStatus(String ptemgTaskStatus) {
		this.ptemgTaskStatus = ptemgTaskStatus;
	}
	public String getPtemgisslistId() {
		return ptemgisslistId;
	}
	public void setPtemgisslistId(String ptemgisslistId) {
		this.ptemgisslistId = ptemgisslistId;
		super.setAuditKey(ptemgisslistId);
	}
    
}
