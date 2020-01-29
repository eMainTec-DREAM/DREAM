package dream.part.adj.dto;

import common.bean.BaseDTO;

/**
 * 무상입고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtFcRecCommonDTO extends BaseDTO
{
	/** ID */
	private String fcRecListId         = "";
	
	/** 필터-부서Id */
	private String filterDeptId        = "";
	/** 필터-부서명 */
	private String filterDeptDesc      = "";
	/** 필터-입고일자 From */
	private String filterRecStartDate  = "";
	/** 필터-입고일자 To */
	private String filterRecEndDate    = "";
	/** 필터-품명/규격명 */
	private String filterPartNameSize  = "";
	/** 상태 - 작업현황에서 팝업 노출 시 사용 */
	private String fcRecStatus        = "";
	/** 상태명 - 작업현황에서 팝업 노출 시 사용 */
	private String fcRecStatusDesc    = "";
	private String filterPartId    = "";
	private String filterPartDesc    = "";
	
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getFcRecListId() {
		return fcRecListId;
	}
	public void setFcRecListId(String fcRecListId) {
		this.fcRecListId = fcRecListId;
		super.setAuditKey(fcRecListId);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterRecStartDate() {
		return filterRecStartDate;
	}
	public void setFilterRecStartDate(String filterRecStartDate) {
		this.filterRecStartDate = filterRecStartDate;
	}
	public String getFilterRecEndDate() {
		return filterRecEndDate;
	}
	public void setFilterRecEndDate(String filterRecEndDate) {
		this.filterRecEndDate = filterRecEndDate;
	}
	public String getFilterPartNameSize() {
		return filterPartNameSize;
	}
	public void setFilterPartNameSize(String filterPartNameSize) {
		this.filterPartNameSize = filterPartNameSize;
	}
	public String getFcRecStatus() {
		return fcRecStatus;
	}
	public void setFcRecStatus(String fcRecStatus) {
		this.fcRecStatus = fcRecStatus;
	}
	public String getFcRecStatusDesc() {
		return fcRecStatusDesc;
	}
	public void setFcRecStatusDesc(String fcRecStatusDesc) {
		this.fcRecStatusDesc = fcRecStatusDesc;
	}
	
}
