package dream.work.rpt.pm.month.rate.dto;

import common.bean.BaseDTO;

/**
 * �������˽����� DTO
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListDTO.java,v 1.0 2015/12/02 09:13:08 sy.yang Exp $
 * @since   1.0
 * 
 */
public class WorkRptPmMonthRateListDTO extends BaseDTO
{
	/** ���˽������� ���ڿ� */
	private String dateArrStr 			= "";
	/** ����-���˽���������� */
	private String filterStartDate 		= "";
	/** ����-���˽��ೡ���� */
	private String filterEndDate 		= "";
	/** ����-�μ�id */
	private String filterDeptId 		= "";
	/** ����-�μ��� */
	private String filterDeptDesc 		= "";
	/** ����-�μ�id */
	private String filterPlantId 		= "";
	/** ����-�μ��� */
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
