package dream.work.cal.unitedmonth.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 월간작업일정 공통 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WorkCalUnitedMonthCommonDTO extends BaseDTO
{
	/** wo ID */
	private String wkOrId 				= "";
	/** pminslist ID */
	private String pminslistId 				= "";
	/** 필터 년 */
	private String filterYyyymm	 			= "";
	/** 필터 부서id */
	private String filterDeptId 			= "";
	/** 필터 부서명 */
	private String filterDeptDesc 			= "";
	/** 필터 설비id */
	private String filterEquipId 			= "";
	/** 필터 설비명 */
	private String filterEquipDesc 			= "";
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
	/** 필터-위치id */
	private String filterEqLocId			= "";
	/** 필터-위치명 */
	private String filterEqLocDesc			= "";
	/** 필터-종류id */
	private String filterEqCtgId			= "";
	/** 필터-종류 */
	private String filterEqCtgDesc			= "";
	/** 필터-내/외자 */
	private String filterPlfTypeId			= "";
	/** 필터-내/외자 명 */
	private String filterPlfTypeDesc		= "";
	/** 필터-작업종류 */
	private String filterWoTypeId			= "";
	/** 필터-작업종류 명 */
	private String filterWoTypeDesc			= "";
	/** 필터-작업형태 */
	private String filterPmTypeId			= "";
	/** 필터-작업형태 명 */
	private String filterPmTypeDesc			= "";
	/** 필터-작업그룹id */
	private String filterWkCtrId			= "";
	/** 필터-작업그룹 명 */
	private String filterWkCtrDesc			= "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	   = "";
	/** 필터 - 시프트 */
	private String filterShiftId			= "";
	/** 필터 - 시프트 */
	private String filterShiftDesc			= "";
	/** 필터 - 담당자 */
	private String filterEmpId				= "";
	/** 필터 - 담당자 */
	private String filterEmpDesc			= "";
	/** 년월일 */
	private String yyyymmdd 				= "";
	/** schedType */
	private String schedType 				= "";
	/** 부서id(검색된 부서id 저장)*/
	private String deptId 					= "";
	/** 부서명(검색된 부서명 저장) */
	private String deptDesc 				= "";
	/** 법정설비여부 */
	private String isLawEq					= "";
	/** 관리자(정)id */
	private String mainMngId				= "";
	/** 관리자(정)명 */
	private String mainMngName				= "";
	/** 관리자(부)id */
	private String subMngId					= "";
	/** 관리자(부)명 */
	private String subMngName				= "";
	/** 위치id */
	private String eqLocId					= "";
	/** 위치명 */
	private String eqLocDesc				= "";
	/** 종류id */
	private String eqCtgId					= "";
	/** 종류 */
	private String eqCtgDesc				= "";
	/** 설비id */
	private String equipId					= "";
	/** 설비 */
	private String equipDesc				= "";
	/** 내/외자 */
	private String plfTypeId				= "";
	/** 내/외자 명 */
	private String plfTypeDesc				= "";
	/** 작업종류 */
	private String woTypeId					= "";
	/** 작업종류 명 */
	private String woTypeDesc				= "";
	/** 작업형태 */
	private String pmTypeId					= "";
	/** 작업형태 명 */
	private String pmTypeDesc				= "";
	/** 작업그룹id */
	private String wkCtrId					= "";
	/** 작업그룹 명 */
	private String wkCtrDesc				= "";
	/** 선택된 wkorId */
	private String selectedWkorId			= "";
	/** 생성된 wkorId */
	private String createdWkorId			= "";
	/** 설비유형Id */
	private String eqCtgTypeId 	   			= "";
	/** 설비유형 */
	private String eqCtgTypeDesc 	   		= "";
	/** 시프트Id */
	private String shiftId 	   				= "";
	/** 시프트 */
	private String shiftDesc	 	   		= "";
	/** 담당자Id */
	private String empId 	   				= "";
	/** 담당자 */
	private String empDesc		 	   		= "";
	/** filter - 공장Id */
    private String filterPlantId            = "";
    /** filter - 공장명 */
    private String filterPlantDesc          = "";
    /** 공장Id */
    private String plantId                  = "";
    /** 공장명 */
    private String plantDesc                = "";
    /** 선택된 pminslistId */
    private String selectedPmInsId				= "";
    /** 생성된 pminslistId */
    private String createdPmInsId				= "";
	
	public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getFilterShiftId() {
		return filterShiftId;
	}
	public void setFilterShiftId(String filterShiftId) {
		this.filterShiftId = filterShiftId;
	}
	public String getFilterShiftDesc() {
		return filterShiftDesc;
	}
	public void setFilterShiftDesc(String filterShiftDesc) {
		this.filterShiftDesc = filterShiftDesc;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftDesc() {
		return shiftDesc;
	}
	public void setShiftDesc(String shiftDesc) {
		this.shiftDesc = shiftDesc;
	}
	public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}
	public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getCreatedWkorId() {
		return createdWkorId;
	}
	public void setCreatedWkorId(String createdWkorId) {
		this.createdWkorId = createdWkorId;
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
	public String getSelectedWkorId() {
		return selectedWkorId;
	}
	public void setSelectedWkorId(String selectedWkorId) {
		this.selectedWkorId = selectedWkorId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
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
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getFilterYyyymm() {
		return filterYyyymm;
	}
	public void setFilterYyyymm(String filterYyyymm) {
		this.filterYyyymm = CommonUtil.convertDate(filterYyyymm);
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
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}
	public String getSchedType() {
		return schedType;
	}
	public void setSchedType(String schedType) {
		this.schedType = schedType;
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
	public String getIsLawEq() {
		return isLawEq;
	}
	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}
	public String getMainMngId() {
		return mainMngId;
	}
	public void setMainMngId(String mainMngId) {
		this.mainMngId = mainMngId;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
	}
	public String getSubMngId() {
		return subMngId;
	}
	public void setSubMngId(String subMngId) {
		this.subMngId = subMngId;
	}
	public String getSubMngName() {
		return subMngName;
	}
	public void setSubMngName(String subMngName) {
		this.subMngName = subMngName;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getPlfTypeId() {
		return plfTypeId;
	}
	public void setPlfTypeId(String plfTypeId) {
		this.plfTypeId = plfTypeId;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
	}
	public String getPminslistId() {
		return pminslistId;
	}
	public void setPminslistId(String pminslistId) {
		this.pminslistId = pminslistId;
	}
	public String getSelectedPmInsId() {
		return selectedPmInsId;
	}
	public void setSelectedPmInsId(String selectedPmInsId) {
		this.selectedPmInsId = selectedPmInsId;
	}
	public String getCreatedPmInsId() {
		return createdPmInsId;
	}
	public void setCreatedPmInsId(String createdPmInsId) {
		this.createdPmInsId = createdPmInsId;
	}
}
