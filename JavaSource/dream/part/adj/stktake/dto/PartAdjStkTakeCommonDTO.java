package dream.part.adj.stktake.dto;

import common.bean.BaseDTO;

/**
 * 부품실사 공통 DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeHdrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class PartAdjStkTakeCommonDTO extends BaseDTO
{
	/** 자재요청 id */
	private String ptPrListId 				= "";
	/** 필터 신청부서id  */
	private String filterDeptId				= "";
	/** 필터 신청부서명 */
	private String filterDeptDesc			= "";
	/** 필터 시작 요청일자 */
	private String filterStartPlanDate		= "";
	/** 필터 끝 요청일자 */
	private String filterEndPlanDate			= "";
	/** 필터 제목 */
	private String filterPtPrListDesc		= "";
	/** 필터 자재id  */
	private String filterPartId				= "";
	/** 필터 자재No */
	private String filterPartNo				= "";
	/** 필터 자재명 */
	private String filterPartDesc			= "";
	/** 필터 작성상태id  */
	private String filterPtPrListStatusId	= "";
	/** 필터 작성상태명 */
	private String filterPtPrListStatusDesc	= "";
	/** 필터 품명/규격 */
	private String filterPtDescSize			= "";
	/** 필터 요청번호 */
	private String filterPtPrListNo			= "";
	/** 필터 신청자id  */
	private String filterUserId				= "";
	/** 필터 신청자명 */
	private String filterUserDesc			= "";
	
	/** 필터 창고명 */
	private String filterWname				= "";
	/** 필터 창고 */
	private String filterWcodeId			= "";
	
	/** 필터-공장 ID */
	private String filterPlantId         	= "";
	/** 필터-공장 DESC */
	private String filterPlantDesc        	= "";
	
	private String ptStkTakeListId			= "";
	private String ptStkTakeStatus			= "";

    
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
	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}
	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}
	public String getPtStkTakeListId() {
		return ptStkTakeListId;
	}
	public void setPtStkTakeListId(String ptStkTakeListId) {
		this.ptStkTakeListId = ptStkTakeListId;
		super.setAuditKey(ptStkTakeListId);
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getPtPrListId() {
		return ptPrListId;
	}
	public void setPtPrListId(String ptPrListId) {
		this.ptPrListId = ptPrListId;
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

	public String getFilterStartPlanDate() {
		return filterStartPlanDate;
	}
	public void setFilterStartPlanDate(String filterStartPlanDate) {
		this.filterStartPlanDate = filterStartPlanDate;
	}
	public String getFilterEndPlanDate() {
		return filterEndPlanDate;
	}
	public void setFilterEndPlanDate(String filterEndPlanDate) {
		this.filterEndPlanDate = filterEndPlanDate;
	}
	public String getFilterPtPrListDesc() {
		return filterPtPrListDesc;
	}
	public void setFilterPtPrListDesc(String filterPtPrListDesc) {
		this.filterPtPrListDesc = filterPtPrListDesc;
	}
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterPtPrListStatusId() {
		return filterPtPrListStatusId;
	}
	public void setFilterPtPrListStatusId(String filterPtPrListStatusId) {
		this.filterPtPrListStatusId = filterPtPrListStatusId;
	}
	public String getFilterPtPrListStatusDesc() {
		return filterPtPrListStatusDesc;
	}
	public void setFilterPtPrListStatusDesc(String filterPtPrListStatusDesc) {
		this.filterPtPrListStatusDesc = filterPtPrListStatusDesc;
	}
	public String getFilterPtDescSize() {
		return filterPtDescSize;
	}
	public void setFilterPtDescSize(String filterPtDescSize) {
		this.filterPtDescSize = filterPtDescSize;
	}
	public String getFilterPtPrListNo() {
		return filterPtPrListNo;
	}
	public void setFilterPtPrListNo(String filterPtPrListNo) {
		this.filterPtPrListNo = filterPtPrListNo;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	
}
