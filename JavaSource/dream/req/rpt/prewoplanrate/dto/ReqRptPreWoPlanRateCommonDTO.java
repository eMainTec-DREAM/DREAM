package dream.req.rpt.prewoplanrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업오더 사전 계획 수립률 목록 - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptPreWoPlanRateCommonDTO extends BaseDTO
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
    private String filterDeptId				 = "";
    /** 부서명 */
    private String filterDeptDesc			 = "";
    
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
    
}
