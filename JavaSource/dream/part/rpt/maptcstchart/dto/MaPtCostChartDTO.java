package dream.part.rpt.maptcstchart.dto;

import common.bean.BaseDTO;

/**
 * ������м� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtCostChartDTO extends BaseDTO
{
	/** ����-�μ����̵� */
	private String filterDeptDesc 		= "";
	/** ����-�μ��� */
	private String filterDeptId 		= "";
	/** ����-�� */
	private String filterYyyy 			= "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
	/** Ŭ�� �� ��*/
	private String yyyy 				= "";
	/** Ŭ�� �� �μ�id*/
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
