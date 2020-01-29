package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * 
 */
public class WorkListCinsPlanMstrCommonDTO extends BaseDTO
{
    /** ��� ID */
    private String pmInsDListId               = "";
    /** ��� NO */
    private String pmInsDListNo               = "";
    
    /** �ϻ����� ID */
    private String pmInsDPointId               = "";
    
    /** �۾����ID */
    private String wkOrId               = "";
    /** �۾���û ID */
    private String woReqId              = "";
    /** �۾��� - ���� */
    private String filterWkOrDesc       = "";
    /** �۾���ȣ - ���� */
    private String filterWoNo           = "";
    /** �۾����� ������ - ���� */
    private String filterStartDate      = "";
    /** �۾����� ������ - ���� */
    private String filterEndDate        = "";
    /** �μ�id - ���� */
    private String filterDeptId         = "";
    /** �μ��� - ���� */
    private String filterDeptDesc       = "";
    /** ����id - ���� */
    private String filterEquipId        = "";
    /** ����id - ���� */
    private String filterEquipNo        = "";
    /** ����� - ���� */
    private String filterEquipDesc      = "";
    /** �����id - ���� */
    private String filterEmpId          = "";
    /** ����ڸ� - ���� */
    private String filterEmpDesc        = "";
    /** ��ġid - ���� */
    private String filterEqLocId        = "";
    /** ��ġ�� - ���� */
    private String filterEqLocDesc      = "";
    /** ����id - ���� */
    private String filterEqCtgId        = "";
    /** ������ - ���� */
    private String filterEqCtgDesc      = "";
    /** ����-��/���� */
    private String filterPlfTypeId      = "";
    /** ����-��/���� �� */
    private String filterPlfTypeDesc    = "";
    /** ����-�۾����� */
    private String filterWoTypeId       = "";
    /** ����-�۾����� �� */
    private String filterWoTypeDesc     = "";
    /** ����-�۾����� */
    private String filterPmTypeId       = "";
    /** ����-�۾����� �� */
    private String filterPmTypeDesc     = "";
    /** ����-�������񿩺� ID*/
    private String filterIsLawEqId        = "";
    /** ����-�������񿩺� */
    private String filterIsLawEq        = "";
    /** ����-������(��)id */
    private String filterMainMngId      = "";
    /** ����-������(��)�� */
    private String filterMainMngName    = "";
    /** ����-������(��)id */
    private String filterSubMngId       = "";
    /** ����-������(��)�� */
    private String filterSubMngName     = "";
    /** ���� - �����ڵ� */
    private String filterSchedStatusId  = "";
    /** ���� - �����ڵ�� */
    private String filterSchedStatusDesc= "";
    /** ���� - ����Ʈ */
    private String filterShiftId        = "";
    /** ���� - ����Ʈ */
    private String filterShiftDesc      = "";
    /** ���� - �۾��׷�Id */
    private String filterWkCtrId        = "";
    /** ���� - �۾��׷�� */
    private String filterWkCtrDesc      = "";
    /** ���� - �����۾�# */
    private String filterPmNo           = "";
    /** ���õ� wkorId */
    private String selectedWkorId       = "";
    /** ���� �м����� �Ѿ���� �����ڵ� */
    private String caDesc               = "";
    /** ���� �м����� �Ѿ���� ��ġ�ڵ� */
    private String reDesc               = "";
    /** ���� �м����� �Ѿ���� �����ڵ尡 ���� �� ���� */
    private String notCaCd              = "";
    /** ���� �м����� �Ѿ���� ��ġ�ڵ尡 ���� �� ���� */
    private String notReCd              = "";
    /** �ڰ�/���ֱ��� �ڵ� */
    private String selfVendorType       = "";
    /** �ڰ�/���ֱ��и� */
    private String selfVendorTypeDesc  = "";
    /** �ŷ�óID */
    private String vendorId            = "";
    /** �ŷ�ó */
    private String vendorDesc          = "";
    /** ���õ� �۾����� */
    private String selectedPmType          = "";
    /** ���õ� �۾����� */
    private String selectedWoType          = "";
    /** Dashboard ���� �ƴ� �۾� */
    private String notPmTypeId         = "";
    /** Filter-��������Id */
    private String filterEqCtgTypeId       = "";
    /** Filter-�������� */
    private String filterEqCtgTypeDesc     = "";
    
    private String woNo     = "";
    
    private String workNumber     = "";
    
    // Pop up --------
    /** �˾� �۾����� */
    private String popupWorkDate        = "";
    /** �˾� �����ð� */
    private String popupMeasureTime     = "";
    /** �˾� ���˱��� ID */
    private String popupWorkPointId     = "";
    /** �˾� ���˱��� NO */
    private String popupWorkPointNo     = "";
    /** �˾� ���˱��� Desc */
    private String popupWorkPointDesc   = "";
    /** �˾� PM ID */
    private String popupPmId            = "";
    /** �˾� PM NO */
    private String popupPmNo            = "";

    /** �����������࿩�� - ���� */
    private String filterIsActive           = "";
    /** ���˿ϷῩ�� - ���� */
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
    /** �˱����۾�ǥ�ر����� ID */
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
    /* FILTER ��ȹ���� start*/
    private String filterStartPlanDate = "";
    /* FILTER ��ȹ���� end*/
    private String filterEndPlanDate = "";
    /* FILTER ������ǰ ID */
    private String filterProductId = "";
    /* FILTER ������ǰ DESC */
    private String filterProductDesc = "";
    /* ������ǰ ID */
    private String productId = "";
    /* ������ǰ DESC */
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
