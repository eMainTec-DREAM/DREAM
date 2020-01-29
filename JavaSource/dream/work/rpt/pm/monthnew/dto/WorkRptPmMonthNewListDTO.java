package dream.work.rpt.pm.monthnew.dto;

import common.bean.BaseDTO;

/**
 * �ű����˵����Ȳ DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkRptPmMonthNewListDTO extends BaseDTO
{
	/** ����-�������� */
	private String filterStartDate 		= "";
	/** ����-�������� */
	private String filterEndDate 		= "";
	/** ����-�μ�id */
	private String filterDeptId 		= "";
	/** ����-�μ��� */
	private String filterDeptDesc 		= "";
	/** deptId */
	private String deptId				= "";
	/** ���� �� ���� ��  */
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
