package dream.work.rpt.pm.month.rate.dto;

import common.bean.BaseDTO;

/**
 * 월별점검실행율 DTO
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListDTO.java,v 1.0 2015/12/02 09:13:08 sy.yang Exp $
 * @since   1.0
 * 
 */
public class WorkRptPmMonthRateListDTO extends BaseDTO
{
	/** 점검실행일자 문자열 */
	private String dateArrStr 			= "";
	/** 필터-점검실행시작일자 */
	private String filterStartDate 		= "";
	/** 필터-점검실행끝일자 */
	private String filterEndDate 		= "";
	/** 필터-부서id */
	private String filterDeptId 		= "";
	/** 필터-부서명 */
	private String filterDeptDesc 		= "";
	/** 필터-부서id */
	private String filterPlantId 		= "";
	/** 필터-부서명 */
	private String filterPlantDesc 		= "";
	
	public String getDateArrStr() {
		return dateArrStr;
	}
	public void setDateArrStr(String dateArrStr) {
		this.dateArrStr = dateArrStr;
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
	
	
	
	
}
