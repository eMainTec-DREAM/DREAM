package dream.work.rpt.mawodaily.dto;

import common.bean.BaseDTO;

/**
 * 일별작업실행율 DTO
 * @author  kim21017
 * @version $Id: MaWoDailyChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoDailyChartDTO extends BaseDTO
{
	/** 수리일자 문자열 */
	private String dateArrStr 			= "";
	/** 필터-수리시작일자 */
	private String filterStartDate 		= "";
	/** 필터-수리끝일자 */
	private String filterEndDate 		= "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
    /** 필터-부서 ID */
    private String filterDeptId            = "";
    /** 필터-부서 DESC */
    private String filterDeptDesc          = "";
    
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
