package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqWorkCommonDTO extends BaseDTO
{
	/** 요청일자 From */
	private String filterReqStartDate     = "";
	/** 요청일자 To */
	private String filterReqEndDate       = "";
	/** 진행상태 ID */
	private String filterWoReqStatusId    = "";
	/** 진행상태 명 */
	private String filterWoReqStatusDesc  = "";
	/** 제목 */
	private String filterWoReqDesc        = "";
	/** 요청내용 */
	private String filterRequest          = "";
	/** 설비 ID */
	private String filterEquipId          = "";
	/** 설비 명 */
	private String filterEquipDesc        = "";
	/** 설비위치 ID */
	private String filterEqLocId          = "";
	/** 설비위치 명 */
	private String filterEqLocDesc        = "";
	/** 요청자 ID */
	private String filterReqEmpId         = "";
	/** 요청자 명 */
	private String filterReqEmpDesc       = "";
	/** 요청부서 ID */
	private String filterReqDeptId        = "";
	/** 요청부서 명 */
	private String filterReqDeptDesc      = "";

	private String wkorId				  = "";
	
	private String woReqResId			  = "";
	/** 작업요청 ID */
	private String woReqId				  = "";
	/** 작업요청 NO */
    private String filterWoReqNo          = "";
	/** 우선순위 ID */
	private String filterReqPriorityId    = "";
	/** 우선순위 명 */
	private String filterReqPriorityDesc  = "";
	
	/** 필터-처리공장 ID */
    private String filterPlantId          = "";
    /** 필터-처리공장 DESC */
    private String filterPlantDesc        = "";
    /** 필터-처리부서 ID */
    private String filterRecDeptId        = "";
    /** 필터-처리부서 DESC */
    private String filterRecDeptDesc      = "";
    /** 필터-처리자 ID */
    private String filterRecEmpId		  = "";
    /** 필터-처리자 DESC */
    private String filterRecEmpDesc       = "";
    /** 제외 진행상태 ID */
    private String notWoReqStatusId       = "";
    /** 처리작업그룹 ID */
    private String filterRecWkCtrId	  	  = "";
    /** 처리작업그룹 DESC */
    private String filterRecWkCtrDesc	  = "";
    
    /** select 팝업에서 선택된 작업요청유형 */
    private String selectedWoReqTypeId	  = "";
    
    /** 필터-공장 ID */
    private String filterReqPlant       = "";
    /** 필터-공장 DESC */
    private String filterReqPlantDesc	  = "";
    
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
	public String getFilterReqPlant() {
		return filterReqPlant;
	}
	public void setFilterReqPlant(String filterReqPlant) {
		this.filterReqPlant = filterReqPlant;
	}
	public String getFilterReqPlantDesc() {
		return filterReqPlantDesc;
	}
	public void setFilterReqPlantDesc(String filterReqPlantDesc) {
		this.filterReqPlantDesc = filterReqPlantDesc;
	}
	public String getFilterWoReqNo()
    {
        return filterWoReqNo;
    }
    public void setFilterWoReqNo(String filterWoReqNo)
    {
        this.filterWoReqNo = filterWoReqNo;
    }
    public String getSelectedWoReqTypeId() {
		return selectedWoReqTypeId;
	}
	public void setSelectedWoReqTypeId(String selectedWoReqTypeId) {
		this.selectedWoReqTypeId = selectedWoReqTypeId;
	}
	public String getNotWoReqStatusId()
    {
        return notWoReqStatusId;
    }
    public void setNotWoReqStatusId(String notWoReqStatusId)
    {
        this.notWoReqStatusId = notWoReqStatusId;
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
	public String getFilterReqPriorityId() {
		return filterReqPriorityId;
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
	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}
	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = filterReqStartDate;
	}
	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}
	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = filterReqEndDate;
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
	
	
	
	

}
