package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class WorkListCinsPlanMstrCommonDTO extends BaseDTO
{
    /** 결과 ID */
    private String pmInsDListId               = "";
    /** 결과 NO */
    private String pmInsDListNo               = "";
    
    /** 일상점검 ID */
    private String pmInsDPointId               = "";
    
    /** 작업결과ID */
    private String wkOrId               = "";
    /** 작업요청 ID */
    private String woReqId              = "";
    /** 작업명 - 필터 */
    private String filterWkOrDesc       = "";
    /** 작업번호 - 필터 */
    private String filterWoNo           = "";
    /** 작업일자 시작일 - 필터 */
    private String filterStartDate      = "";
    /** 작업일자 종료일 - 필터 */
    private String filterEndDate        = "";
    /** 부서id - 필터 */
    private String filterDeptId         = "";
    /** 부서명 - 필터 */
    private String filterDeptDesc       = "";
    /** 설비id - 필터 */
    private String filterEquipId        = "";
    /** 설비id - 필터 */
    private String filterEquipNo        = "";
    /** 설비명 - 필터 */
    private String filterEquipDesc      = "";
    /** 담당자id - 필터 */
    private String filterEmpId          = "";
    /** 담당자명 - 필터 */
    private String filterEmpDesc        = "";
    /** 위치id - 필터 */
    private String filterEqLocId        = "";
    /** 위치명 - 필터 */
    private String filterEqLocDesc      = "";
    /** 종류id - 필터 */
    private String filterEqCtgId        = "";
    /** 종류명 - 필터 */
    private String filterEqCtgDesc      = "";
    /** 필터-내/외자 */
    private String filterPlfTypeId      = "";
    /** 필터-내/외자 명 */
    private String filterPlfTypeDesc    = "";
    /** 필터-작업종류 */
    private String filterWoTypeId       = "";
    /** 필터-작업종류 명 */
    private String filterWoTypeDesc     = "";
    /** 필터-작업형태 */
    private String filterPmTypeId       = "";
    /** 필터-작업형태 명 */
    private String filterPmTypeDesc     = "";
    /** 필터-법정설비여부 ID*/
    private String filterIsLawEqId        = "";
    /** 필터-법정설비여부 */
    private String filterIsLawEq        = "";
    /** 필터-관리자(정)id */
    private String filterMainMngId      = "";
    /** 필터-관리자(정)명 */
    private String filterMainMngName    = "";
    /** 필터-관리자(부)id */
    private String filterSubMngId       = "";
    /** 필터-관리자(부)명 */
    private String filterSubMngName     = "";
    /** 필터 - 상태코드 */
    private String filterSchedStatusId  = "";
    /** 필터 - 상태코드명 */
    private String filterSchedStatusDesc= "";
    /** 필터 - 시프트 */
    private String filterShiftId        = "";
    /** 필터 - 시프트 */
    private String filterShiftDesc      = "";
    /** 필터 - 작업그룹Id */
    private String filterWkCtrId        = "";
    /** 필터 - 작업그룹명 */
    private String filterWkCtrDesc      = "";
    /** 필터 - 예방작업# */
    private String filterPmNo           = "";
    /** 선택된 wkorId */
    private String selectedWkorId       = "";
    /** 설비별 분석에서 넘어오는 원인코드 */
    private String caDesc               = "";
    /** 설비별 분석에서 넘어오는 조치코드 */
    private String reDesc               = "";
    /** 설비별 분석에서 넘어오는 원인코드가 없는 것 구분 */
    private String notCaCd              = "";
    /** 설비별 분석에서 넘어오는 조치코드가 없는 것 구분 */
    private String notReCd              = "";
    /** 자가/외주구분 코드 */
    private String selfVendorType       = "";
    /** 자가/외주구분명 */
    private String selfVendorTypeDesc  = "";
    /** 거래처ID */
    private String vendorId            = "";
    /** 거래처 */
    private String vendorDesc          = "";
    /** 선택된 작업형태 */
    private String selectedPmType          = "";
    /** 선택된 작업종류 */
    private String selectedWoType          = "";
    /** Dashboard 점검 아닌 작업 */
    private String notPmTypeId         = "";
    /** Filter-설비유형Id */
    private String filterEqCtgTypeId       = "";
    /** Filter-설비유형 */
    private String filterEqCtgTypeDesc     = "";
    
    private String woNo     = "";
    
    private String workNumber     = "";
    
    // Pop up --------
    /** 팝업 작업일자 */
    private String popupWorkDate        = "";
    /** 팝업 측정시간 */
    private String popupMeasureTime     = "";
    /** 팝업 점검기준 ID */
    private String popupWorkPointId     = "";
    /** 팝업 점검기준 NO */
    private String popupWorkPointNo     = "";
    /** 팝업 점검기준 Desc */
    private String popupWorkPointDesc   = "";
    /** 팝업 PM ID */
    private String popupPmId            = "";
    /** 팝업 PM NO */
    private String popupPmNo            = "";

    /** 점검일정발행여부 - 필터 */
    private String filterIsActive           = "";
    /** 점검완료여부 - 필터 */
    private String filterIsComplete           = "";

    
    public String getFilterIsActive()
    {
        return filterIsActive;
    }
    public void setFilterIsActive(String filterIsActive)
    {
        this.filterIsActive = filterIsActive;
    }
    public String getFilterIsComplete()
    {
        return filterIsComplete;
    }
    public void setFilterIsComplete(String filterIsComplete)
    {
        this.filterIsComplete = filterIsComplete;
    }
    public String getFilterSchedStatusId()
    {
        return filterSchedStatusId;
    }
    public void setFilterSchedStatusId(String filterSchedStatusId)
    {
        this.filterSchedStatusId = filterSchedStatusId;
    }
    public String getFilterSchedStatusDesc()
    {
        return filterSchedStatusDesc;
    }
    public void setFilterSchedStatusDesc(String filterSchedStatusDesc)
    {
        this.filterSchedStatusDesc = filterSchedStatusDesc;
    }
    public String getPmInsDPointId()
    {
        return pmInsDPointId;
    }
    public void setPmInsDPointId(String pmInsDPointId)
    {
        this.pmInsDPointId = pmInsDPointId;
    }
    public String getWorkNumber()
    {
        return workNumber;
    }
    public void setWorkNumber(String workNumber)
    {
        this.workNumber = workNumber;
    }
    public String getPopupWorkPointNo()
    {
        return popupWorkPointNo;
    }
    public void setPopupWorkPointNo(String popupWorkPointNo)
    {
        this.popupWorkPointNo = popupWorkPointNo;
    }
    public String getPopupPmNo()
    {
        return popupPmNo;
    }
    public void setPopupPmNo(String popupPmNo)
    {
        this.popupPmNo = popupPmNo;
    }
    public String getPopupPmId()
    {
        return popupPmId;
    }
    public void setPopupPmId(String popupPmId)
    {
        this.popupPmId = popupPmId;
    }
    public String getPopupWorkDate()
    {
        return popupWorkDate;
    }
    public void setPopupWorkDate(String popupWorkDate)
    {
        this.popupWorkDate = CommonUtil.convertDate(popupWorkDate);
    }
    public String getPopupMeasureTime()
    {
        return popupMeasureTime;
    }
    public void setPopupMeasureTime(String popupMeasureTime)
    {
        this.popupMeasureTime = popupMeasureTime;
    }
    public String getPopupWorkPointId()
    {
        return popupWorkPointId;
    }
    public void setPopupWorkPointId(String popupWorkPointId)
    {
        this.popupWorkPointId = popupWorkPointId;
    }
    public String getPopupWorkPointDesc()
    {
        return popupWorkPointDesc;
    }
    public void setPopupWorkPointDesc(String popupWorkPointDesc)
    {
        this.popupWorkPointDesc = popupWorkPointDesc;
    }
    public String getPmInsDListId()
    {
        return pmInsDListId;
    }
    public void setPmInsDListId(String pmInsDListId)
    {
        this.pmInsDListId = pmInsDListId;
        super.setAuditKey(pmInsDListId);
    }
    public String getPmInsDListNo()
    {
        return pmInsDListNo;
    }
    public void setPmInsDListNo(String pmInsDListNo)
    {
        this.pmInsDListNo = pmInsDListNo;
    }
    public String getFilterIsLawEqId()
    {
        return filterIsLawEqId;
    }
    public void setFilterIsLawEqId(String filterIsLawEqId)
    {
        this.filterIsLawEqId = filterIsLawEqId;
    }
    public String getWoNo() {
        return woNo;
    }
    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }
    /** 검교정작업표준교정기 ID */
    private String wocalibstdeqId       = "";
    
    
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
    
    /* KEY */
    private String pmInsDSchedId = "";
    /* FILTER 계획일자 start*/
    private String filterStartPlanDate = "";
    /* FILTER 계획일자 end*/
    private String filterEndPlanDate = "";
    /* FILTER 생산제품 ID */
    private String filterProductId = "";
    /* FILTER 생산제품 DESC */
    private String filterProductDesc = "";
    /* 생산제품 ID */
    private String productId = "";
    /* 생산제품 DESC */
    private String productDesc = "";

    private String pmId = "";
    
    private String pmEquipId = "";

    public String getPmEquipId()
    {
        return pmEquipId;
    }
    public void setPmEquipId(String pmEquipId)
    {
        this.pmEquipId = pmEquipId;
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getProductId()
    {
        return productId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    public String getProductDesc()
    {
        return productDesc;
    }
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }
    public String getPmInsDSchedId()
    {
        return pmInsDSchedId;
    }
    public void setPmInsDSchedId(String pmInsDSchedId)
    {
        this.pmInsDSchedId = pmInsDSchedId;
        super.setAuditKey(pmInsDSchedId);
    }
    public String getFilterStartPlanDate()
    {
        return filterStartPlanDate;
    }
    public void setFilterStartPlanDate(String filterStartPlanDate)
    {
        this.filterStartPlanDate = filterStartPlanDate;
    }
    public String getFilterEndPlanDate()
    {
        return filterEndPlanDate;
    }
    public void setFilterEndPlanDate(String filterEndPlanDate)
    {
        this.filterEndPlanDate = filterEndPlanDate;
    }
    public String getFilterProductId()
    {
        return filterProductId;
    }
    public void setFilterProductId(String filterProductId)
    {
        this.filterProductId = filterProductId;
    }
    public String getFilterProductDesc()
    {
        return filterProductDesc;
    }
    public void setFilterProductDesc(String filterProductDesc)
    {
        this.filterProductDesc = filterProductDesc;
    }
}
