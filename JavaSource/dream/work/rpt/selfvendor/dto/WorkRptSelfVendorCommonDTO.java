package dream.work.rpt.selfvendor.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사내, 외주 작업 현황 Report - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptSelfVendorCommonDTO extends BaseDTO
{
    /** 시작일자 */ 
    private String filterStartDate           = "";
    /** 종료일자 */ 
    private String filterEndDate             = "";
    /** 공장 ID */ 
    private String filterPlantId             = "";
    /** 공장명 */ 
    private String filterPlantDesc           = "";
    /** 부서 ID */ 
    private String filterDeptId              = "";
    /** 부서명 */
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
