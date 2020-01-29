package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;

/**
 * 투자 목록 팝업 DTO
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public class LovInvtListDTO extends BaseDTO
{
	/** 투자목록 ID */
	private String invtlistId			= "";
	
	/** 투자시기 */
	private String filterStartDate		= "";
	private String filterEndDate		= "";
	
	/** 투자명 */
	private String filterInvtDesc		= "";
	
	/** 진행단계 */
	private String filterInvtStatus		= "";
	/** 진행단계명 */
	private String filterInvtStatusDesc	= "";
	
	/** 투자부서 ID */
	private String filterDeptId			= "";
	/** 투자부서명 */
	private String filterDeptDesc		= "";
	
	/** 담당자 ID */
	private String filterEmpId			= "";
	/** 담당자명 */
	private String filterEmpDesc		= "";
	
	/** 투자구분 */
	private String filterInvtCateg		= "";
	/** 투자구분명 */
	private String filterInvtCategDesc	= "";
	
	/** 분류 */
	private String filterInvtType		= "";
	/** 분류명 */
	private String filterInvtTypeDesc	= "";
	
	/** 설비위치 ID */
	private String filterEqlocId		= "";
	/** 설비위치명 */
	private String filterEqlocDesc		= "";
	
	/** 설비종류 ID */
	private String filterEqctgId		= "";
	/** 설비종류명 */
	private String filterEqctgDesc		= "";
	
	/** 투자계획 */
	private String filterInvtlistNo		= "";
	
	/** 설비 ID */
	private String filterEquipId		= "";
	/** 설비명 */
	private String filterEquipDesc		= "";
	
	/** 공장 ID */
	private String filterPlantId		= "";
	/** 공장명 */
	private String filterPlantDesc		= "";
	
	
	public String getInvtlistId() {
		return invtlistId;
	}
	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
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
	public String getFilterInvtDesc() {
		return filterInvtDesc;
	}
	public void setFilterInvtDesc(String filterInvtDesc) {
		this.filterInvtDesc = filterInvtDesc;
	}
	public String getFilterInvtStatus() {
		return filterInvtStatus;
	}
	public void setFilterInvtStatus(String filterInvtStatus) {
		this.filterInvtStatus = filterInvtStatus;
	}
	public String getFilterInvtStatusDesc() {
		return filterInvtStatusDesc;
	}
	public void setFilterInvtStatusDesc(String filterInvtStatusDesc) {
		this.filterInvtStatusDesc = filterInvtStatusDesc;
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
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}
	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}
	public String getFilterInvtCateg() {
		return filterInvtCateg;
	}
	public void setFilterInvtCateg(String filterInvtCateg) {
		this.filterInvtCateg = filterInvtCateg;
	}
	public String getFilterInvtCategDesc() {
		return filterInvtCategDesc;
	}
	public void setFilterInvtCategDesc(String filterInvtCategDesc) {
		this.filterInvtCategDesc = filterInvtCategDesc;
	}
	public String getFilterInvtType() {
		return filterInvtType;
	}
	public void setFilterInvtType(String filterInvtType) {
		this.filterInvtType = filterInvtType;
	}
	public String getFilterInvtTypeDesc() {
		return filterInvtTypeDesc;
	}
	public void setFilterInvtTypeDesc(String filterInvtTypeDesc) {
		this.filterInvtTypeDesc = filterInvtTypeDesc;
	}
	public String getFilterEqlocId() {
		return filterEqlocId;
	}
	public void setFilterEqlocId(String filterEqlocId) {
		this.filterEqlocId = filterEqlocId;
	}
	public String getFilterEqlocDesc() {
		return filterEqlocDesc;
	}
	public void setFilterEqlocDesc(String filterEqlocDesc) {
		this.filterEqlocDesc = filterEqlocDesc;
	}
	public String getFilterEqctgId() {
		return filterEqctgId;
	}
	public void setFilterEqctgId(String filterEqctgId) {
		this.filterEqctgId = filterEqctgId;
	}
	public String getFilterEqctgDesc() {
		return filterEqctgDesc;
	}
	public void setFilterEqctgDesc(String filterEqctgDesc) {
		this.filterEqctgDesc = filterEqctgDesc;
	}
	public String getFilterInvtlistNo() {
		return filterInvtlistNo;
	}
	public void setFilterInvtlistNo(String filterInvtlistNo) {
		this.filterInvtlistNo = filterInvtlistNo;
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
