package dream.conn.rpt.month.log.dto;

import common.bean.BaseDTO;

/**
 * ����������Ȳ DTO
 * @author  sy.yang
 * @version $Id: ConnRptMonthLogListDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class ConnRptMonthLogListDTO extends BaseDTO
{
	/** �� ���ڿ� */
	private String dateArrStr 			= "";
	/** ����-�˻��������� */
	private String filterStartDate 		= "";
	/** ����-�˻������� */
	private String filterEndDate 		= "";
	/** ����-�μ�id */
	private String filterDeptId 		= "";
	/** ����-�μ��� */
	private String filterDeptDesc 		= "";
	/** ����-���id */
	private String filterEmpId 		= "";
	/** ����-����� */
	private String filterEmpDesc 		= "";
	/** deptId */
	private String deptId				= "";
	/** empId */
	private String empId				= "";
	
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
