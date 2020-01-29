package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획목록 공통 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class WoPlanCommonDTO extends BaseDTO
{
	/** 작업계획목록 ID */
	private String wkOrId					= "";
	/** 작업요청 ID */
	private String woReqId					= "";
	/** 스케줄 ID */
	private String pmSchedId				= "";
	/** 작업명 - 필터 */
	private String filterWkOrDesc			= "";
	/** 작업번호 - 필터 */
	private String filterWoNo				= "";
	/** 작업일자 시작일 - 필터 */
	private String filterStartDate			= "";
	/** 작업일자 종료일 - 필터 */
	private String filterEndDate			= "";
	/** 부서id - 필터 */
	private String filterDeptId				= "";
	/** 부서명 - 필터 */
	private String filterDeptDesc			= "";
	/** 설비id - 필터 */
	private String filterEquipId			= "";
	/** 설비id - 필터 */
	private String filterEquipNo			= "";
	/** 설비명 - 필터 */
	private String filterEquipDesc			= "";
	/** 담당자id - 필터 */
	private String filterEmpId				= "";
	/** 담당자명 - 필터 */
	private String filterEmpDesc			= "";
	/** 위치id - 필터 */
	private String filterEqLocId			= "";
	/** 위치명 - 필터 */
	private String filterEqLocDesc			= "";
	/** 종류id - 필터 */
	private String filterEqCtgId			= "";
	/** 종류명 - 필터 */
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
	/** 필터 - 상태코드 */
	private String filterWoPlanStatus		= "";
	/** 필터 - 상태코드명 */
	private String filterWoPlanStatusDesc	= "";
	/** 필터 - 시프트 */
	private String filterShiftId			= "";
	/** 필터 - 시프트 */
	private String filterShiftDesc			= "";
	/** 필터 - 작업그룹Id */
	private String filterWkCtrId			= "";
	/** 필터 - 작업그룹명 */
	private String filterWkCtrDesc			= "";
	/** 필터 - 예방작업# */
	private String filterPmNo				= "";
	/** 선택된 wkorId */
	private String selectedWkorId			= "";
	/** 설비별 분석에서 넘어오는 원인코드 */
	private String caDesc					= "";
	/** 설비별 분석에서 넘어오는 조치코드 */
	private String reDesc					= "";
	/** 설비별 분석에서 넘어오는 원인코드가 없는 것 구분 */
	private String notCaCd					= "";
	/** 설비별 분석에서 넘어오는 조치코드가 없는 것 구분 */
	private String notReCd					= "";
	/** 자가/외주구분 코드 */
	private String selfVendorType       	= "";
	/** 자가/외주구분명 */
	private String selfVendorTypeDesc  		= "";
	/** 거래처ID */
	private String vendorId            		= "";
	/** 거래처 */
	private String vendorDesc          		= "";
	/** 필터 - 거래처명(text value) */
    private String filterVendorName       	= "";
	/** 선택된 작업형태 */
	private String selectedPmType          	= "";
	/** 선택된 작업종류 */
	private String selectedWoType          	= "";
	/** Dashboard 점검 아닌 작업 */
	private String notPmTypeId         		= "";
	/** Dashboard 점검 아닌 작업 */
    private String notWoTypeId         		= "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	   	= "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	   	= "";
	
	private String woNo						= "";
	/** 교정작업 일반측정결과 ID */
	private String wocalibgnlvalueId 		= "";
	/** Filter-고장원인 */
	private String filterCaCd 	   			= "";
	/** Filter-고장원인 */
	private String filterCaCdDesc 	  		= "";
	
	/** Filter-고장*/
	private String filterReCd 	   			= "";
	/** Filter-고장원인 */
	private String filterReCdDesc 	   		= "";
	/** 교정기준값 ID */
	private String pmCalibStdTpId			= "";

	/** 검사일자 */
	private String filterCalStartDate		= "";
	private String filterCalEndDate			= "";
	/** 검사일자 */
	private String filterCalCorpDesc		= "";
	/** 검사기관ID*/
	private String filterCalCorId			= "";
	/** 검사 환경*/
	private String filterCalEnv				= "";
	/** 최종판정*/
	private String filterCalRsltStatDesc	= "";
	/** 최종판정*/
	private String filterCalRsltStatId		= "";
	/** 검사구분*/
	private String filterCalTypeDesc		= "";
	/** 검사구분*/
	private String filterCalTypeId			= "";
	
	/** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    /** 처리사항 ID */
    private String woReqResId         		= "";
    /** 필터-부 담당자 ID */
    private String filterSubEmpId			= "";
    /** 필터-부 담당자 DESC */
    private String filterSubEmpDesc			= "";
    
    public String getFilterSubEmpId() {
		return filterSubEmpId;
	}
	public void setFilterSubEmpId(String filterSubEmpId) {
		this.filterSubEmpId = filterSubEmpId;
	}
	public String getFilterSubEmpDesc() {
		return filterSubEmpDesc;
	}
	public void setFilterSubEmpDesc(String filterSubEmpDesc) {
		this.filterSubEmpDesc = filterSubEmpDesc;
	}
	public String getPmSchedId()
    {
        return pmSchedId;
    }
    public void setPmSchedId(String pmSchedId)
    {
        this.pmSchedId = pmSchedId;
    }
    public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
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
	public String getNotWoTypeId()
    {
        return notWoTypeId;
    }
    public void setNotWoTypeId(String notWoTypeId)
    {
        this.notWoTypeId = notWoTypeId;
    }
    public String getFilterVendorName()
    {
        return filterVendorName;
    }
    public void setFilterVendorName(String filterVendorName)
    {
        this.filterVendorName = filterVendorName;
    }
    public String getFilterCalStartDate() {
		return filterCalStartDate;
	}
	public void setFilterCalStartDate(String filterCalStartDate) {
		this.filterCalStartDate = filterCalStartDate;
	}
	public String getFilterCalEndDate() {
		return filterCalEndDate;
	}
	public void setFilterCalEndDate(String filterCalEndDate) {
		this.filterCalEndDate = filterCalEndDate;
	}
	public String getFilterCalCorpDesc() {
		return filterCalCorpDesc;
	}
	public void setFilterCalCorpDesc(String filterCalCorpDesc) {
		this.filterCalCorpDesc = filterCalCorpDesc;
	}
	public String getFilterCalCorId() {
		return filterCalCorId;
	}
	public void setFilterCalCorId(String filterCalCorId) {
		this.filterCalCorId = filterCalCorId;
	}
	public String getFilterCalEnv() {
		return filterCalEnv;
	}
	public void setFilterCalEnv(String filterCalEnv) {
		this.filterCalEnv = filterCalEnv;
	}
	public String getFilterCalRsltStatDesc() {
		return filterCalRsltStatDesc;
	}
	public void setFilterCalRsltStatDesc(String filterCalRsltStatDesc) {
		this.filterCalRsltStatDesc = filterCalRsltStatDesc;
	}
	public String getFilterCalRsltStatId() {
		return filterCalRsltStatId;
	}
	public void setFilterCalRsltStatId(String filterCalRsltStatId) {
		this.filterCalRsltStatId = filterCalRsltStatId;
	}
	public String getFilterCalTypeDesc() {
		return filterCalTypeDesc;
	}
	public void setFilterCalTypeDesc(String filterCalTypeDesc) {
		this.filterCalTypeDesc = filterCalTypeDesc;
	}
	public String getFilterCalTypeId() {
		return filterCalTypeId;
	}
	public void setFilterCalTypeId(String filterCalTypeId) {
		this.filterCalTypeId = filterCalTypeId;
	}
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getFilterReCd() {
		return filterReCd;
	}
	public void setFilterReCd(String filterReCd) {
		this.filterReCd = filterReCd;
	}
	public String getFilterReCdDesc() {
		return filterReCdDesc;
	}
	public void setFilterReCdDesc(String filterReCdDesc) {
		this.filterReCdDesc = filterReCdDesc;
	}
	public String getFilterCaCd() {
		return filterCaCd;
	}
	public void setFilterCaCd(String filterCaCd) {
		this.filterCaCd = filterCaCd;
	}
	public String getFilterCaCdDesc() {
		return filterCaCdDesc;
	}
	public void setFilterCaCdDesc(String filterCaCdDesc) {
		this.filterCaCdDesc = filterCaCdDesc;
	}
	public String getWocalibgnlvalueId() {
		return wocalibgnlvalueId;
	}
	public void setWocalibgnlvalueId(String wocalibgnlvalueId) {
		this.wocalibgnlvalueId = wocalibgnlvalueId;
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	/** 검교정작업표준교정기 ID */
	private String wocalibstdeqId		= "";
	
	
	public String getWocalibstdeqId() {
		return wocalibstdeqId;
	}
	public void setWocalibstdeqId(String wocalibstdeqId) {
		this.wocalibstdeqId = wocalibstdeqId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
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
	public String getNotPmTypeId()
    {
        return notPmTypeId;
    }
    public void setNotPmTypeId(String notPmTypeId)
    {
        this.notPmTypeId = notPmTypeId;
    }
    public String getSelectedPmType() {
		return selectedPmType;
	}
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	public String getSelectedWoType() {
		return selectedWoType;
	}
	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}
	public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getSelfVendorType()
    {
        return selfVendorType;
    }
    public void setSelfVendorType(String selfVendorType)
    {
        this.selfVendorType = selfVendorType;
    }
    public String getSelfVendorTypeDesc()
    {
        return selfVendorTypeDesc;
    }
    public void setSelfVendorTypeDesc(String selfVendorTypeDesc)
    {
        this.selfVendorTypeDesc = selfVendorTypeDesc;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getNotCaCd() {
		return notCaCd;
	}
	public void setNotCaCd(String notCaCd) {
		this.notCaCd = notCaCd;
	}
	public String getNotReCd() {
		return notReCd;
	}
	public void setNotReCd(String notReCd) {
		this.notReCd = notReCd;
	}
	public String getCaDesc() {
		return caDesc;
	}
	public void setCaDesc(String caDesc) {
		this.caDesc = caDesc;
	}
	public String getReDesc() {
		return reDesc;
	}
	public void setReDesc(String reDesc) {
		this.reDesc = reDesc;
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
	public String getFilterWoNo() {
		return filterWoNo;
	}
	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	public String getSelectedWkorId() {
		return selectedWkorId;
	}
	public void setSelectedWkorId(String selectedWkorId) {
		this.selectedWkorId = selectedWkorId;
	}
	public String getFilterWoPlanStatus() {
		return filterWoPlanStatus;
	}
	public void setFilterWoPlanStatus(String filterWoPlanStatus) {
		this.filterWoPlanStatus = filterWoPlanStatus;
	}
	public String getFilterWoPlanStatusDesc() {
		return filterWoPlanStatusDesc;
	}
	public void setFilterWoPlanStatusDesc(String filterWoPlanStatusDesc) {
		this.filterWoPlanStatusDesc = filterWoPlanStatusDesc;
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
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getFilterWkOrDesc() {
		return filterWkOrDesc;
	}
	public void setFilterWkOrDesc(String filterWkOrDesc) {
		this.filterWkOrDesc = filterWkOrDesc;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
	
}
