package dream.work.let.dto;

import common.bean.BaseDTO;

/**
 * 안전작업 공통 DTO
 * @author  syyang
 * @version $Id: WorkLetListCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class WorkLetCommonDTO extends BaseDTO
{
	/** 안전작업허가서 ID */
	private String woLetId				= "";

	/** 안전작업명 - 필터 */
	private String filterWoLetDesc		= "";
	/** 안전작업번호 - 필터 */
	private String filterWoLetNo		= "";
	/** 신청부서id - 필터 */
	private String filterReqDeptId		= "";
	/** 신청부서명 - 필터 */
	private String filterReqDeptDesc	= "";
	/** 신청자id - 필터 */
	private String filterReqById		= "";
	/** 신청자명 - 필터 */
	private String filterReqByDesc		= "";
	/** 허가부서id - 필터 */
	private String filterRecDeptId		= "";
	/** 허가부서명 - 필터 */
	private String filterRecDeptDesc	= "";
	/** 허가자id - 필터 */
	private String filterRecById		= "";
	/** 허가자명 - 필터 */
	private String filterRecByDesc		= "";
	/** 설비/기기 - 필터 */
	private String filterItemDesc		= "";
	/** 작업장소 - 필터 */
	private String filterPlace			= "";
	/** 공장Id */
    private String filterPlant        	= "";
    /** 공장명 */
    private String filterPlantDesc      = "";
	/** 작업번호 ID - 필터 */
	private String filterWkorId			= "";
	/** 작업번호 - 필터 */
	private String filterWoNo			= "";
	/** 작업기간 From 시작일 - 필터 */
	private String filterFromStartDate	= "";
	/** 작업기간 From 종료일 - 필터 */
	private String filterFromEndDate	= "";
    /** 작업기간 To - 시작일 */
    private String filterToStartDate	= "";
    /** 작업기간 To - 종료일 */
    private String filterToEndDate		= "";
    
    
	public String getWoLetId() {
		return woLetId;
	}
	public void setWoLetId(String woLetId) {
		this.woLetId = woLetId;
		super.setAuditKey(woLetId);
	}
	public String getFilterWoLetDesc() {
		return filterWoLetDesc;
	}
	public void setFilterWoLetDesc(String filterWoLetDesc) {
		this.filterWoLetDesc = filterWoLetDesc;
	}
	public String getFilterWoLetNo() {
		return filterWoLetNo;
	}
	public void setFilterWoLetNo(String filterWoLetNo) {
		this.filterWoLetNo = filterWoLetNo;
	}
	public String getFilterReqDeptId() {
		return filterReqDeptId;
	}
	public void setFilterReqDeptId(String filterReqDeptId) {
		this.filterReqDeptId = filterReqDeptId;
	}
	public String getFilterReqDeptDesc() {
		return filterReqDeptDesc;
	}
	public void setFilterReqDeptDesc(String filterReqDeptDesc) {
		this.filterReqDeptDesc = filterReqDeptDesc;
	}
	public String getFilterReqById() {
		return filterReqById;
	}
	public void setFilterReqById(String filterReqById) {
		this.filterReqById = filterReqById;
	}
	public String getFilterReqByDesc() {
		return filterReqByDesc;
	}
	public void setFilterReqByDesc(String filterReqByDesc) {
		this.filterReqByDesc = filterReqByDesc;
	}
	public String getFilterRecDeptId() {
		return filterRecDeptId;
	}
	public void setFilterRecDeptId(String filterRecDeptId) {
		this.filterRecDeptId = filterRecDeptId;
	}
	public String getFilterRecDeptDesc() {
		return filterRecDeptDesc;
	}
	public void setFilterRecDeptDesc(String filterRecDeptDesc) {
		this.filterRecDeptDesc = filterRecDeptDesc;
	}
	public String getFilterRecById() {
		return filterRecById;
	}
	public void setFilterRecById(String filterRecById) {
		this.filterRecById = filterRecById;
	}
	public String getFilterRecByDesc() {
		return filterRecByDesc;
	}
	public void setFilterRecByDesc(String filterRecByDesc) {
		this.filterRecByDesc = filterRecByDesc;
	}
	public String getFilterItemDesc() {
		return filterItemDesc;
	}
	public void setFilterItemDesc(String filterItemDesc) {
		this.filterItemDesc = filterItemDesc;
	}
	public String getFilterPlace() {
		return filterPlace;
	}
	public void setFilterPlace(String filterPlace) {
		this.filterPlace = filterPlace;
	}
	public String getFilterPlant() {
		return filterPlant;
	}
	public void setFilterPlant(String filterPlant) {
		this.filterPlant = filterPlant;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getFilterWkorId() {
		return filterWkorId;
	}
	public void setFilterWkorId(String filterWkorId) {
		this.filterWkorId = filterWkorId;
	}
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getFilterFromStartDate() {
		return filterFromStartDate;
	}
	public void setFilterFromStartDate(String filterFromStartDate) {
		this.filterFromStartDate = filterFromStartDate;
	}
	public String getFilterFromEndDate() {
		return filterFromEndDate;
	}
	public void setFilterFromEndDate(String filterFromEndDate) {
		this.filterFromEndDate = filterFromEndDate;
	}
	public String getFilterToStartDate() {
		return filterToStartDate;
	}
	public void setFilterToStartDate(String filterToStartDate) {
		this.filterToStartDate = filterToStartDate;
	}
	public String getFilterToEndDate() {
		return filterToEndDate;
	}
	public void setFilterToEndDate(String filterToEndDate) {
		this.filterToEndDate = filterToEndDate;
	}
    
}
