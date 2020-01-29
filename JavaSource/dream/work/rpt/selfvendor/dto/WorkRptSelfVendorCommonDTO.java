package dream.work.rpt.selfvendor.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �系, ���� �۾� ��Ȳ Report - ���� DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptSelfVendorCommonDTO extends BaseDTO
{
    /** �������� */ 
    private String filterStartDate           = "";
    /** �������� */ 
    private String filterEndDate             = "";
    /** ���� ID */ 
    private String filterPlantId             = "";
    /** ����� */ 
    private String filterPlantDesc           = "";
    /** �μ� ID */ 
    private String filterDeptId              = "";
    /** �μ��� */
    private String filterDeptDesc			 = "";
    
    
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
    
}
