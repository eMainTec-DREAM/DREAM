package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoReqCommonDTO extends BaseDTO
{
	/** ��û���� From */
	private String filterReqStartDate     = "";
	/** ��û���� To */
	private String filterReqEndDate       = "";
	/** ������� ID */
	private String filterWoReqStatusId    = "";
	/** ������� �� */
	private String filterWoReqStatusDesc  = "";
	/** ���� */
	private String filterWoReqDesc        = "";
	/** ��û���� */
	private String filterRequest          = "";
	/** ���� ID */
	private String filterEquipId          = "";
	/** ���� �� */
	private String filterEquipDesc        = "";
	/** ������ġ ID */
	private String filterEqLocId          = "";
	/** ������ġ �� */
	private String filterEqLocDesc        = "";
	/** ��û�� ID */
	private String filterReqEmpId         = "";
	/** ��û�� �� */
	private String filterReqEmpDesc       = "";
	/** ��û�μ� ID */
	private String filterReqDeptId        = "";
	/** ��û�μ� �� */
	private String filterReqDeptDesc      = "";
	/** ó���� ID */
	private String filterRecEmpId         = "";
	/** ó���� �� */
	private String filterRecEmpDesc       = "";
	/** ó���μ� ID */
	private String filterRecDeptId        = "";
	/** ó���μ� �� */
	private String filterRecDeptDesc      = "";
	/** ��û �켱���� ID */
	private String filterReqPriorityId    = "";
	/** ��û �켱���� Desc */
	private String filterReqPriorityDesc  = "";
	
	private String wkorId				= "";
	
	private String wkorDesc              = "";
	
	/** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc         	= "";
    /** ����-�۾���û�߻����� ID */
    private String filterReqChannelId		= "";
    /** ����-�۾���û�߻����� DESC */
    private String filterReqChannelDesc		= "";
    
    /** ���� ��� ID */
    private String invtlistId				= "";
    /** �۾���û ID */
    private String woReqId				    = "";
    /** ó������ ID */
    private String woReqResId               = "";
    
    /** ��û��ȣ */
    private String filterWoReqNo            = "";
    /** ��û���� ID */
    private String filterWoReqType          = "";
    /** ��û���� Desc*/
    private String filterWoReqTypeDesc      = "";
    /** �������� ID */
    private String filterMoCd               = "";
    /** �������� Desc */
    private String filterMoCdDesc           = "";
    /** �������󼳸� */
    private String filterMoDesc             = "";
    /** W/O # */
    private String filterWoNo               = "";
    /** ó���۾��׷� ID */
    private String filterRecWkCtrId	  	  = "";
    /** ó���۾��׷� DESC */
    private String filterRecWkCtrDesc	  = "";
    
    public String getFilterRecWkCtrId() {
		return filterRecWkCtrId;
	}
	public void setFilterRecWkCtrId(String filterRecWkCtrId) {
		this.filterRecWkCtrId = filterRecWkCtrId;
	}
	public String getFilterRecWkCtrDesc() {
		return filterRecWkCtrDesc;
	}
	public void setFilterRecWkCtrDesc(String filterRecWkCtrDesc) {
		this.filterRecWkCtrDesc = filterRecWkCtrDesc;
	}
	public String getFilterReqChannelId() {
		return filterReqChannelId;
	}
	public void setFilterReqChannelId(String filterReqChannelId) {
		this.filterReqChannelId = filterReqChannelId;
	}
	public String getFilterReqChannelDesc() {
		return filterReqChannelDesc;
	}
	public void setFilterReqChannelDesc(String filterReqChannelDesc) {
		this.filterReqChannelDesc = filterReqChannelDesc;
	}
	public String getFilterWoNo()
    {
        return filterWoNo;
    }
    public void setFilterWoNo(String filterWoNo)
    {
        this.filterWoNo = filterWoNo;
    }
    public String getFilterWoReqType()
    {
        return filterWoReqType;
    }
    public void setFilterWoReqType(String filterWoReqType)
    {
        this.filterWoReqType = filterWoReqType;
    }
    public String getFilterWoReqTypeDesc()
    {
        return filterWoReqTypeDesc;
    }
    public void setFilterWoReqTypeDesc(String filterWoReqTypeDesc)
    {
        this.filterWoReqTypeDesc = filterWoReqTypeDesc;
    }
    public String getFilterMoCdDesc()
    {
        return filterMoCdDesc;
    }
    public void setFilterMoCdDesc(String filterMoCdDesc)
    {
        this.filterMoCdDesc = filterMoCdDesc;
    }
    public String getFilterMoCd()
    {
        return filterMoCd;
    }
    public void setFilterMoCd(String filterMoCd)
    {
        this.filterMoCd = filterMoCd;
    }
    public String getFilterMoDesc()
    {
        return filterMoDesc;
    }
    public void setFilterMoDesc(String filterMoDesc)
    {
        this.filterMoDesc = filterMoDesc;
    }
    public String getFilterWoReqNo()
    {
        return filterWoReqNo;
    }
    public void setFilterWoReqNo(String filterWoReqNo)
    {
        this.filterWoReqNo = filterWoReqNo;
    }
    public String getWkorDesc()
    {
        return wkorDesc;
    }
    public void setWkorDesc(String wkorDesc)
    {
        this.wkorDesc = wkorDesc;
    }
    public String getInvtlistId() {
		return invtlistId;
	}
	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}
	
	public String getFilterReqPriorityId() {
		return filterReqPriorityId;
	}
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public void setFilterReqPriorityId(String filterReqPriorityId) {
		this.filterReqPriorityId = filterReqPriorityId;
	}
	public String getFilterReqPriorityDesc() {
		return filterReqPriorityDesc;
	}
	public void setFilterReqPriorityDesc(String filterReqPriorityDesc) {
		this.filterReqPriorityDesc = filterReqPriorityDesc;
	}
	public String getFilterRecEmpId() {
		return filterRecEmpId;
	}
	public void setFilterRecEmpId(String filterRecEmpId) {
		this.filterRecEmpId = filterRecEmpId;
	}
	public String getFilterRecEmpDesc() {
		return filterRecEmpDesc;
	}
	public void setFilterRecEmpDesc(String filterRecEmpDesc) {
		this.filterRecEmpDesc = filterRecEmpDesc;
	}
	public String getFilterRecDeptId() {
		return filterRecDeptId;
	}
	public void setFilterRecDeptId(String filterRecDeptId) {
		this.filterRecDeptId = filterRecDeptId;
	}
	public String getFilterRecDeptDesc() {
		return filterRecDeptDesc;
	}
	public void setFilterRecDeptDesc(String filterRecDeptDesc) {
		this.filterRecDeptDesc = filterRecDeptDesc;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
		super.setAuditKey(woReqId);
	}
	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}
	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = CommonUtil.convertDate(filterReqStartDate);
	}
	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}
	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = CommonUtil.convertDate(filterReqEndDate);
	}
	public String getFilterWoReqStatusId() {
		return filterWoReqStatusId;
	}
	public void setFilterWoReqStatusId(String filterWoReqStatusId) {
		this.filterWoReqStatusId = filterWoReqStatusId;
	}
	public String getFilterWoReqStatusDesc() {
		return filterWoReqStatusDesc;
	}
	public void setFilterWoReqStatusDesc(String filterWoReqStatusDesc) {
		this.filterWoReqStatusDesc = filterWoReqStatusDesc;
	}
	public String getFilterWoReqDesc() {
		return filterWoReqDesc;
	}
	public void setFilterWoReqDesc(String filterWoReqDesc) {
		this.filterWoReqDesc = filterWoReqDesc;
	}
	public String getFilterRequest() {
		return filterRequest;
	}
	public void setFilterRequest(String filterRequest) {
		this.filterRequest = filterRequest;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterReqEmpId() {
		return filterReqEmpId;
	}
	public void setFilterReqEmpId(String filterReqEmpId) {
		this.filterReqEmpId = filterReqEmpId;
	}
	public String getFilterReqEmpDesc() {
		return filterReqEmpDesc;
	}
	public void setFilterReqEmpDesc(String filterReqEmpDesc) {
		this.filterReqEmpDesc = filterReqEmpDesc;
	}
	public String getFilterReqDeptId() {
		return filterReqDeptId;
	}
	public void setFilterReqDeptId(String filterReqDeptId) {
		this.filterReqDeptId = filterReqDeptId;
	}
	public String getFilterReqDeptDesc() {
		return filterReqDeptDesc;
	}
	public void setFilterReqDeptDesc(String filterReqDeptDesc) {
		this.filterReqDeptDesc = filterReqDeptDesc;
	}
	
}
