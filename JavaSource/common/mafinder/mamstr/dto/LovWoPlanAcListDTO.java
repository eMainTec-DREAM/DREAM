package common.mafinder.mamstr.dto;

import common.bean.BaseDTO;

/**
 * 작업 팝업 DTO
 * @author  kim21017
 * @version $Id: LovWoPlanAcListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovWoPlanAcListDTO extends BaseDTO
{
    /** 코드 */
    private String woNo 		= "";
    /** 명 */
    private String woDesc 		= "";
    /** 상태 */
    private String woStatus     = "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String pmType 	    = "";
    
    private String woType       = "";
    
    /** 작업명 - 필터 */
	private String filterWkOrDesc		= "";
	/** 작업번호 - 필터 */
	private String filterWoNo			= "";
	/** 작업일자 시작일 - 필터 */
	private String filterStartDate		= "";
	/** 작업일자 종료일 - 필터 */
	private String filterEndDate		= "";
	/** 부서id - 필터 */
	private String filterDeptId			= "";
	/** 부서명 - 필터 */
	private String filterDeptDesc		= "";
	/** 설비id - 필터 */
	private String filterEquipId		= "";
	/** 설비명 - 필터 */
	private String filterEquipDesc		= "";
	/** 담당자id - 필터 */
	private String filterEmpId			= "";
	/** 담당자명 - 필터 */
	private String filterEmpDesc		= "";
	/** 위치id - 필터 */
	private String filterEqLocId		= "";
	/** 위치명 - 필터 */
	private String filterEqLocDesc		= "";
	/** 종류id - 필터 */
	private String filterEqCtgId		= "";
	/** 종류명 - 필터 */
	private String filterEqCtgDesc		= "";
	/** 필터-내/외자 */
	private String filterPlfTypeId		= "";
	/** 필터-내/외자 명 */
	private String filterPlfTypeDesc	= "";
	/** 필터-작업종류 */
	private String filterWoTypeId		= "";
	/** 필터-작업종류 명 */
	private String filterWoTypeDesc		= "";
	/** 필터-작업형태 */
	private String filterPmTypeId		= "";
	/** 필터-작업형태 명 */
	private String filterPmTypeDesc		= "";
	/** 필터-법정설비여부 */
	private String filterIsLawEq		= "";
	/** 필터-관리자(정)id */
	private String filterMainMngId		= "";
	/** 필터-관리자(정)명 */
	private String filterMainMngName	= "";
	/** 필터-관리자(부)id */
	private String filterSubMngId		= "";
	/** 필터-관리자(부)명 */
	private String filterSubMngName		= "";
	/** 필터 - 상태코드 */
	private String filterWoStatus		= "";
	/** 필터 - 상태코드명 */
	private String filterWoStatusDesc	= "";
	
	/** 자가/외주구분 코드 */
	private String selfVendorType       = "";
	/** 자가/외주구분명 */
	private String selfVendorTypeDesc  	= "";
	/** 거래처ID */
	private String vendorId            	= "";
	/** 거래처 */
	private String vendorDesc          	= "";
    /** Multy Select Y */
    private String multiSelect    		= "";
	
    
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getSelfVendorType() {
		return selfVendorType;
	}
	public void setSelfVendorType(String selfVendorType) {
		this.selfVendorType = selfVendorType;
	}
	public String getSelfVendorTypeDesc() {
		return selfVendorTypeDesc;
	}
	public void setSelfVendorTypeDesc(String selfVendorTypeDesc) {
		this.selfVendorTypeDesc = selfVendorTypeDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getFilterWkOrDesc() {
		return filterWkOrDesc;
	}
	public void setFilterWkOrDesc(String filterWkOrDesc) {
		this.filterWkOrDesc = filterWkOrDesc;
	}
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
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
	public String getFilterWoTypeId() {
		return filterWoTypeId;
	}
	public void setFilterWoTypeId(String filterWoTypeId) {
		this.filterWoTypeId = filterWoTypeId;
	}
	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}
	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
	}
	public String getFilterPmTypeId() {
		return filterPmTypeId;
	}
	public void setFilterPmTypeId(String filterPmTypeId) {
		this.filterPmTypeId = filterPmTypeId;
	}
	public String getFilterPmTypeDesc() {
		return filterPmTypeDesc;
	}
	public void setFilterPmTypeDesc(String filterPmTypeDesc) {
		this.filterPmTypeDesc = filterPmTypeDesc;
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
	public String getFilterWoStatus() {
		return filterWoStatus;
	}
	public void setFilterWoStatus(String filterWoStatus) {
		this.filterWoStatus = filterWoStatus;
	}
	public String getFilterWoStatusDesc() {
		return filterWoStatusDesc;
	}
	public void setFilterWoStatusDesc(String filterWoStatusDesc) {
		this.filterWoStatusDesc = filterWoStatusDesc;
	}
	public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getWoStatus()
    {
        return woStatus;
    }
    public void setWoStatus(String woStatus)
    {
        this.woStatus = woStatus;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getWoDesc()
    {
        return woDesc;
    }
    public void setWoDesc(String woDesc)
    {
        this.woDesc = woDesc;
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
    
}
