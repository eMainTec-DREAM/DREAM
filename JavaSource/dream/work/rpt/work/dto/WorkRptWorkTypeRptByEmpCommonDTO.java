package dream.work.rpt.work.dto;

import common.bean.BaseDTO;

/**
 * 담당자별작업현황 DTO 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptWorkTypeRptByEmpCommonDTO extends BaseDTO
{
	/** 월  */
	private String filterFromYyyyMm 	= "";
	private String filterToYyyyMm		= "";
	
	/** 공장 */
	private String filterPlant			= "";
	private String filterPlantDesc		= "";
	
	/** 작업부서 */
	private String filterWrkDeptId			= "";
	private String filterWrkDeptDesc		= "";
	
	/** 작업그룹 */
	private String filterWkCtrId		= "";
	private String filterWkCtrDesc		= "";
	
	/** 작업종류 */
	private String filterWoType			= "";
	private String filterWoTypeDesc		= "";
	
	/** 담당자 */
	private String filterEmpId			= "";
	private String filterEmpDesc		= "";
	
	
	public String getFilterFromYyyyMm() {
		return filterFromYyyyMm;
	}
	public void setFilterFromYyyyMm(String filterFromYyyyMm) {
		this.filterFromYyyyMm = filterFromYyyyMm;
	}
	public String getFilterToYyyyMm() {
		return filterToYyyyMm;
	}
	public void setFilterToYyyyMm(String filterToYyyyMm) {
		this.filterToYyyyMm = filterToYyyyMm;
	}
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterWrkDeptId() {
		return filterWrkDeptId;
	}
	public void setFilterWrkDeptId(String filterWrkDeptId) {
		this.filterWrkDeptId = filterWrkDeptId;
	}
	public String getFilterWrkDeptDesc() {
		return filterWrkDeptDesc;
	}
	public void setFilterWrkDeptDesc(String filterWrkDeptDesc) {
		this.filterWrkDeptDesc = filterWrkDeptDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterWoType() {
		return filterWoType;
	}
	public void setFilterWoType(String filterWoType) {
		this.filterWoType = filterWoType;
	}
	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}
	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	
}