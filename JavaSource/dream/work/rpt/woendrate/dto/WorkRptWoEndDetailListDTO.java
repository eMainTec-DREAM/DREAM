package dream.work.rpt.woendrate.dto;

import common.bean.BaseDTO;

/**
 * 작업오더 일마감 처리율 목록 - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class WorkRptWoEndDetailListDTO extends BaseDTO
{
	/** 시작일자 */ 
    private String startDate           = "";
    /** 종료일자 */ 
    private String endDate             = "";
    /** 공장 ID */ 
    private String plantId             = "";
    /** 공장명 */ 
    private String plantDesc           = "";
    /** 부서 ID */
    private String deptId				 = "";
    /** 부서명 */
    private String deptDesc			 = "";
    
    private String month			 = "";
    
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
    
}
