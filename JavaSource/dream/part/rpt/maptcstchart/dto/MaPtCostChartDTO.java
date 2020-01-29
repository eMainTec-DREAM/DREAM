package dream.part.rpt.maptcstchart.dto;

import common.bean.BaseDTO;

/**
 * 자재비용분석 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtCostChartDTO extends BaseDTO
{
	/** 필터-부서아이디 */
	private String filterDeptDesc 		= "";
	/** 필터-부서명 */
	private String filterDeptId 		= "";
	/** 필터-년 */
	private String filterYyyy 			= "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
	/** 클릭 시 년*/
	private String yyyy 				= "";
	/** 클릭 시 부서id*/
	private String deptId 				= "";
	
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterYyyy() {
		return filterYyyy;
	}
	public void setFilterYyyy(String filterYyyy) {
		this.filterYyyy = filterYyyy;
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
