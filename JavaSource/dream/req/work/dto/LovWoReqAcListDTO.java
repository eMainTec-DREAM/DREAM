package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * 작업요청 팝업 DTO
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 */
public class LovWoReqAcListDTO extends BaseDTO
{
    /** 코드 */
    private String woReqNo 		= "";
    /** 명 */
    private String woReqDesc 	= "";
    /** 상태 */
    private String woReqStatus 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String woReqType	= "";
    
    /** 작업요청명 - 필터 */
	private String filterWoReqDesc			= "";
	/** 작업요청번호 - 필터 */
	private String filterWoReqNo			= "";
	/** 작업요청 시작일 - 필터 */
	private String filterReqStartDate		= "";
	/** 작업요청 종료일 - 필터 */
	private String filterReqEndDate			= "";
	/** 설비id - 필터 */
	private String filterEquipId			= "";
	/** 설비명 - 필터 */
	private String filterEquipDesc			= "";
	/** 위치id - 필터 */
	private String filterEqLocId			= "";
	/** 위치명 - 필터 */
	private String filterEqLocDesc			= "";
	/** 요청부서id - 필터 */
	private String filterReqDeptId			= "";
	/** 요청부서명 - 필터 */
	private String filterReqDeptDesc		= "";
	/** 요청자id - 필터 */
	private String filterReqEmpId			= "";
	/** 요청자명 - 필터 */
	private String filterReqEmpDesc			= "";
	/** 필터-요청구분 */
	private String filterWoReqTypeId		= "";
	/** 필터-요청구분 명 */
	private String filterWoReqTypeDesc		= "";
	/** 필터 - 진행상태 */
	private String filterWoReqStatus		= "";
	/** 필터 - 진행상태 명 */
	private String filterWoReqStatusDesc	= "";

	/** Multy Select Y */
    private String multiSelect    			= "";

    
	public String getWoReqNo() {
		return woReqNo;
	}

	public void setWoReqNo(String woReqNo) {
		this.woReqNo = woReqNo;
	}

	public String getWoReqDesc() {
		return woReqDesc;
	}

	public void setWoReqDesc(String woReqDesc) {
		this.woReqDesc = woReqDesc;
	}

	public String getWoReqStatus() {
		return woReqStatus;
	}

	public void setWoReqStatus(String woReqStatus) {
		this.woReqStatus = woReqStatus;
	}

	public String getExtCode1() {
		return extCode1;
	}

	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}

	public String getExtCode2() {
		return extCode2;
	}

	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}

	public String getWoReqType() {
		return woReqType;
	}

	public void setWoReqType(String woReqType) {
		this.woReqType = woReqType;
	}

	public String getFilterWoReqDesc() {
		return filterWoReqDesc;
	}

	public void setFilterWoReqDesc(String filterWoReqDesc) {
		this.filterWoReqDesc = filterWoReqDesc;
	}

	public String getFilterWoReqNo() {
		return filterWoReqNo;
	}

	public void setFilterWoReqNo(String filterWoReqNo) {
		this.filterWoReqNo = filterWoReqNo;
	}

	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}

	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = filterReqStartDate;
	}

	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}

	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = filterReqEndDate;
	}

	public String getFilterEquipId() {
		return filterEquipId;
	}

	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}

	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}

	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}

	public String getFilterEqLocId() {
		return filterEqLocId;
	}

	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}

	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}

	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
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

	public String getFilterReqEmpId() {
		return filterReqEmpId;
	}

	public void setFilterReqEmpId(String filterReqEmpId) {
		this.filterReqEmpId = filterReqEmpId;
	}

	public String getFilterReqEmpDesc() {
		return filterReqEmpDesc;
	}

	public void setFilterReqEmpDesc(String filterReqEmpDesc) {
		this.filterReqEmpDesc = filterReqEmpDesc;
	}

	public String getFilterWoReqTypeId() {
		return filterWoReqTypeId;
	}

	public void setFilterWoReqTypeId(String filterWoReqTypeId) {
		this.filterWoReqTypeId = filterWoReqTypeId;
	}

	public String getFilterWoReqTypeDesc() {
		return filterWoReqTypeDesc;
	}

	public void setFilterWoReqTypeDesc(String filterWoReqTypeDesc) {
		this.filterWoReqTypeDesc = filterWoReqTypeDesc;
	}

	public String getFilterWoReqStatus() {
		return filterWoReqStatus;
	}

	public void setFilterWoReqStatus(String filterWoReqStatus) {
		this.filterWoReqStatus = filterWoReqStatus;
	}

	public String getFilterWoReqStatusDesc() {
		return filterWoReqStatusDesc;
	}

	public void setFilterWoReqStatusDesc(String filterWoReqStatusDesc) {
		this.filterWoReqStatusDesc = filterWoReqStatusDesc;
	}

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
    
}
