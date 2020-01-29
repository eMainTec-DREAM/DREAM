package dream.work.rpt.maeqwochart.dto;

import common.bean.BaseDTO;

/**
 * 설비작업현황 DTO
 * @author  kim21017
 * @version $Id: MaEqWoChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqWoChartDTO extends BaseDTO
{
	/** 수리일자 문자열 */
	private String dateArrStr 			= "";
	/** 필터-부서아이디 */
	private String filterDeptDesc 		= "";
	/** 필터-부서명 */
	private String filterDeptId 		= "";
	/** 필터-수리시작일자 */
	private String filterStartDate 		= "";
	/** 필터-수리끝일자 */
	private String filterEndDate 		= "";
	/** 검색된 부서아이디 */
	private String deptDesc 			= "";
	/** 검색된 부서명 */
	private String deptId 				= "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
	
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
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
