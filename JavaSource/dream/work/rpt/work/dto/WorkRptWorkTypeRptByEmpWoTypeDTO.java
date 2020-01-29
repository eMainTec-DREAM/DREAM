package dream.work.rpt.work.dto;

import common.bean.BaseDTO;

/**
 * ����ں��۾���Ȳ - ����� �۾���������Ȳ �� ��� dto 
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptWorkTypeRptByEmpWoTypeDTO extends BaseDTO
{
	/** Ÿ��Ʋ */
	private String title				= "";
	/** ����� */
	private String empId				= "";
	private String empDesc				= "";
	/** �μ� */
	private String deptId				= "";
	private String deptDesc				= "";
	/** �۾��׷� */
	private String wkCtrId				= "";
	private String wkCtrDesc			= "";
	/** �۾����� */
	private String pmType				= "";
	private String pmTypeDesc			= "";
	/** filter - ��¥ */
	private String filterStartDate		= ""; 
	private String filterEndDate		= ""; 
	/** filter - ���� */
	private String filterPlant			= ""; 
	private String filterPlantDesc		= ""; 
	/** filter - �۾����� */
	private String filterWoType			= ""; 
	private String filterWoTypeDesc		= "";
	
	public String getPmType() {
		return pmType;
	}
	public void setPmType(String pmType) {
		this.pmType = pmType;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
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
    
}