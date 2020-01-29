package dream.work.cal.pmperiod.dto;

import common.bean.BaseDTO;

/**
 * 예방작업일정(기간) 공통 DTO
 * @author  kim21017
 * @version $Id: MaWoSchedCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaWoSchedCommonDTO extends BaseDTO
{
	/** 스케쥴 ID */
	private String pmSchedId 				= "";
	/** pmequip ID */
	private String pmEquipId 				= "";
	/** 상태 ID */
	private String pmSchedStatus			= "";
	/** 필터 시작일자 */
	private String filterStartDate 			= "";
	/** 필터 종료일자 */
	private String filterEndDate 			= "";
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
	/** 필터-작업그룹 */
	private String filterWkCtrId			= "";
	/** 필터-작업그룹 명 */
	private String filterWkCtrDesc			= "";
	/** 필터-pmNo */
	private String filterPmNo				= "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	    = "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	    = "";
	/** Filter-담당자Id */
    private String filterEmpId              = "";
    /** Filter-담당자 */
    private String filterEmpDesc            = "";
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
	/** pm번호 */
	private String pmNo						= "";
	/** 작업형태 명 */
	private String pmTypeDesc				= "";
	/** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    
    /** 필터 - 설비번호 */
    private String filterEquipNo			= "";
    /** 필터 - 상위설비 ID */
    private String filterPequipId			= "";
    /** 필터 - 상위설비 DESC */
    private String filterPequipDesc			= "";
    /** 필터 - 설비사용부서 ID */
    private String filterEqUsaDeptId		= "";
    /** 필터 - 설비사용부서 DESC */
    private String filterEqUsaDeptDesc		= "";
    /** 필터 - 상위설비사용부서 ID */
    private String filterPEqUsaDeptId		= "";
    /** 필터 - 상위설비사용부서 DESC */
    private String filterPEqUsaDeptDesc		= "";
    /** 필터 - Room No. */
    private String filterRoomNumber         = "";
    /** 필터 - 교정사유 ID */
    private String filterCalTypeId          = "";
    /** 필터 - 교정사유 DESC */
    private String filterCalTypeDesc        = "";
    
    private String wkOrId                   = "";
    
	public String getPmSchedStatus()
    {
        return pmSchedStatus;
    }
    public void setPmSchedStatus(String pmSchedStatus)
    {
        this.pmSchedStatus = pmSchedStatus;
    }
    public String getPmEquipId()
    {
        return pmEquipId;
    }
    public void setPmEquipId(String pmEquipId)
    {
        this.pmEquipId = pmEquipId;
    }
    public String getWkOrId()
    {
        return wkOrId;
    }
    public void setWkOrId(String wkOrId)
    {
        this.wkOrId = wkOrId;
    }
    public String getFilterRoomNumber()
    {
        return filterRoomNumber;
    }
    public void setFilterRoomNumber(String filterRoomNumber)
    {
        this.filterRoomNumber = filterRoomNumber;
    }
    public String getFilterCalTypeId()
    {
        return filterCalTypeId;
    }
    public void setFilterCalTypeId(String filterCalTypeId)
    {
        this.filterCalTypeId = filterCalTypeId;
    }
    public String getFilterCalTypeDesc()
    {
        return filterCalTypeDesc;
    }
    public void setFilterCalTypeDesc(String filterCalTypeDesc)
    {
        this.filterCalTypeDesc = filterCalTypeDesc;
    }
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterPequipId() {
		return filterPequipId;
	}
	public void setFilterPequipId(String filterPequipId) {
		this.filterPequipId = filterPequipId;
	}
	public String getFilterPequipDesc() {
		return filterPequipDesc;
	}
	public void setFilterPequipDesc(String filterPequipDesc) {
		this.filterPequipDesc = filterPequipDesc;
	}
	public String getFilterEqUsaDeptId() {
		return filterEqUsaDeptId;
	}
	public void setFilterEqUsaDeptId(String filterEqUsaDeptId) {
		this.filterEqUsaDeptId = filterEqUsaDeptId;
	}
	public String getFilterEqUsaDeptDesc() {
		return filterEqUsaDeptDesc;
	}
	public void setFilterEqUsaDeptDesc(String filterEqUsaDeptDesc) {
		this.filterEqUsaDeptDesc = filterEqUsaDeptDesc;
	}
	public String getFilterPEqUsaDeptId() {
		return filterPEqUsaDeptId;
	}
	public void setFilterPEqUsaDeptId(String filterPEqUsaDeptId) {
		this.filterPEqUsaDeptId = filterPEqUsaDeptId;
	}
	public String getFilterPEqUsaDeptDesc() {
		return filterPEqUsaDeptDesc;
	}
	public void setFilterPEqUsaDeptDesc(String filterPEqUsaDeptDesc) {
		this.filterPEqUsaDeptDesc = filterPEqUsaDeptDesc;
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
	public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
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
	public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
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
	public String getPmSchedId() {
		return pmSchedId;
	}
	public void setPmSchedId(String pmSchedId) {
		this.pmSchedId = pmSchedId;
		super.setAuditKey(pmSchedId);
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
	
}
