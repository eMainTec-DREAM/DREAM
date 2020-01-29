package dream.asset.rpt.monthnew.dto;

import common.bean.BaseDTO;

/**
 * 신규설비등록현황 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class AssetRptMonthNewListDTO extends BaseDTO
{
	/** 필터-시작일자 */
	private String filterStartDate 		= "";
	/** 필터-종료일자 */
	private String filterEndDate 		= "";
	/** 필터-부서id */
	private String filterDeptId 		= "";
	/** 필터-부서명 */
	private String filterDeptDesc 		= "";
	/** deptId */
	private String deptId				= "";
	/** 일자 간 개월 수  */
	private String months				= "";
	/** 필터-공장id */
	private String filterPlantId 		= "";
	/** 필터-공장명 */
	private String filterPlantDesc 		= "";
	
	public String getFilterDeptId() {
		return filterDeptId;
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
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
}
