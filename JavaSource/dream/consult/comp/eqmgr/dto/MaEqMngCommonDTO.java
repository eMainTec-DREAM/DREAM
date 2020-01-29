package dream.consult.comp.eqmgr.dto;

import common.bean.BaseDTO;

/**
 * 설비담당자 변경공통 DTO
 * @author  jung7126
 * @version $Id: MaEqMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqMngCommonDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId					= "";
	/** 필터-설비명 */
	private String filterEquipDesc			= "";
	/** 필터-위치id */
	private String filterEqLocId			= "";
	/** 필터-위치명 */
	private String filterEqLocDesc			= "";
	/** 필터-기능코드id(종류) */
	private String filterEqCtgId			= "";
	/** 필터-기능코드명(종류) */
	private String filterEqCtgDesc			= "";
	/** 필터-내/외자 */
	private String filterPlfTypeId			= "";
	/** 필터-내/외자 명 */
	private String filterPlfTypeDesc		= "";
	/** 필터-관리부서id */
	private String filterDeptId				= "";
	/** 필터-관리부서명 */
	private String filterDeptDesc			= "";
	/** 필터-법정설비여부 */
	private String filterIsLawEq			= "";
	/** 필터-관리자(정)id */
	private String filterMainMngId			= "";
	/** 필터-관리자(정)명 */
	private String filterMainMngName		= "";
	/** 필터-관리자(부)id */
	private String filterSubMngId			= "";
	/** 필터-관리자(부)명 */
	private String filterSubMngName			= "";
	/** 필터-제작사 */
	private String filterMaker				= "";
	/** 필터-모델번호 */
	private String filterModelNo			= "";
	/** 필터-설비번호 */
	private String filterItemNo				= "";
	
	public String getFilterItemNo() {
		return filterItemNo;
	}

	public void setFilterItemNo(String filterItemNo) {
		this.filterItemNo = filterItemNo;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
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

	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}

	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}

	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}

	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}

	public String getFilterPlfTypeId() {
		return filterPlfTypeId;
	}

	public void setFilterPlfTypeId(String filterPlfTypeId) {
		this.filterPlfTypeId = filterPlfTypeId;
	}

	public String getFilterPlfTypeDesc() {
		return filterPlfTypeDesc;
	}

	public void setFilterPlfTypeDesc(String filterPlfTypeDesc) {
		this.filterPlfTypeDesc = filterPlfTypeDesc;
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

	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}

	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
	}

	public String getFilterMainMngId() {
		return filterMainMngId;
	}

	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}

	public String getFilterMainMngName() {
		return filterMainMngName;
	}

	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}

	public String getFilterSubMngId() {
		return filterSubMngId;
	}

	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}

	public String getFilterSubMngName() {
		return filterSubMngName;
	}

	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
	}

	public String getFilterMaker() {
		return filterMaker;
	}

	public void setFilterMaker(String filterMaker) {
		this.filterMaker = filterMaker;
	}

	public String getFilterModelNo() {
		return filterModelNo;
	}

	public void setFilterModelNo(String filterModelNo) {
		this.filterModelNo = filterModelNo;
	}
	
}
