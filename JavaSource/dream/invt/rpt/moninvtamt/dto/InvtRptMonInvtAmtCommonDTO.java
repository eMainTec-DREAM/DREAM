package dream.invt.rpt.moninvtamt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * InvtRptMonInvtAmtList Page - 공통 DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class InvtRptMonInvtAmtCommonDTO extends BaseDTO
{
    /**Filter 년 */ 
    private String filterYyyy		    = "";
    /**Filter 공장명 ID */ 
    private String filterPlantId        = "";
    /**Filter 공장명 DESC */ 
    private String filterPlantDesc      = "";
    /**Filter 사용부서 ID */ 
    private String filterDeptId        = "";
    /**Filter 사용부서 DESC */ 
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
