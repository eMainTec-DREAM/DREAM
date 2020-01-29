package dream.work.rpt.pm.monthnew.dto;

import common.bean.BaseDTO;

/**
 * 신규점검등록현황 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkRptPmMonthNewListDTO extends BaseDTO
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
	
	public String getFilterDeptId() {
		return filterDeptId;
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
