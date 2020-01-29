package dream.invt.rpt.moninvtamt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * InvtRptMonInvtAmtList Page - ���� DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class InvtRptMonInvtAmtCommonDTO extends BaseDTO
{
    /**Filter �� */ 
    private String filterYyyy		    = "";
    /**Filter ����� ID */ 
    private String filterPlantId        = "";
    /**Filter ����� DESC */ 
    private String filterPlantDesc      = "";
    /**Filter ���μ� ID */ 
    private String filterDeptId        = "";
    /**Filter ���μ� DESC */ 
    private String filterDeptDesc      = "";
    /**Filter invtListId */ 
    private String filterInvtListId      = "";
    
    
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
	public String getFilterInvtListId() {
		return filterInvtListId;
	}
	public void setFilterInvtListId(String filterInvtListId) {
		this.filterInvtListId = filterInvtListId;
	}
    


  
}
