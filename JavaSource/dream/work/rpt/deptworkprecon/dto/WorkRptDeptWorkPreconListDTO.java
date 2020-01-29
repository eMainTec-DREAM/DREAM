package dream.work.rpt.deptworkprecon.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.BaseDTO;

/**
 * 부서별 작업진행현황 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkRptDeptWorkPreconListDTO extends BaseDTO
{
	/** 필터-시작일자 */
	private String filterStartDate 		= "";
	/** 필터-종료일자 */
	private String filterEndDate 		= "";
	/** 필터-부서id */
	private String filterDeptId 		= "";
	/** 필터-부서명 */
	private String filterDeptDesc 		= "";
	/** 필터-공장id */
	private String filterPlantId 		= "";
	/** 필터-공장명 */
	private String filterPlantDesc 		= "";
	/** deptId */
	private String deptId				= "";
	/** 일자 간 개월 수  */
	private String months				= "";
	/** 일자 간 개월 수  */
	private String woTypes				= "";
	/** 일자 간 개월 수  */
	private String woTypeCnt			= "";
	
	private List woTypeList = new ArrayList<Map>();
	
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public List getWoTypeList() {
		return woTypeList;
	}
	public void setWoTypeList(List woTypeList) {
		this.woTypeList = woTypeList;
	}
	public String getWoTypeCnt() {
		return woTypeCnt;
	}
	public void setWoTypeCnt(String woTypeCnt) {
		this.woTypeCnt = woTypeCnt;
	}
	public String getWoTypes() {
		return woTypes;
	}
	public void setWoTypes(String woTypes) {
		this.woTypes = woTypes;
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
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
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
}
