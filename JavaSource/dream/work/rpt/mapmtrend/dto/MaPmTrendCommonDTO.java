package dream.work.rpt.mapmtrend.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class MaPmTrendCommonDTO extends BaseDTO
{
    /** 필터-설비명 */
    private String filterEquipDesc          = "";
    /** 필터-위치id */
    private String filterEqLocId            = "";
    /** 필터-위치명 */
    private String filterEqLocDesc          = "";
    /** 필터-기능코드id(종류) */
    private String filterEqCtgId            = "";
    /** 필터-기능코드명(종류) */
    private String filterEqCtgDesc          = "";
    /** 필터-내/외자 */
    private String filterPlfTypeId          = "";
    /** 필터-내/외자 명 */
    private String filterPlfTypeDesc        = "";
    /** 필터-관리부서id */
    private String filterDeptId             = "";
    /** 필터-관리부서명 */
    private String filterDeptDesc           = "";
    /** 필터-법정설비여부 */
    private String filterIsLawEq            = "";
    /** 필터-관리자(정)id */
    private String filterMainMngId          = "";
    /** 필터-관리자(정)명 */
    private String filterMainMngName        = "";
    /** 필터-관리자(부)id */
    private String filterSubMngId           = "";
    /** 필터-관리자(부)명 */
    private String filterSubMngName         = "";
    /** 필터-제작사 */
    private String filterMaker              = "";
    /** 필터-모델번호 */
    private String filterModelNo            = "";
    /** 필터-점검시작일자 */
    private String filterStartDate          = "";
    /** 필터-점검끝일자 */
    private String filterEndDate            = "";
    /** pmPointId */
    private String pmPointId                = "";
    
    /** 담당자id - 필터 */
    private String filterEmpId          = "";
    /** 담당자명 - 필터 */
    private String filterEmpDesc        = "";
    
    /** pmType */
    private String pmType        = "";
    
    /** selectedWoType */
    private String selectedWoType        = "";
    /** selectedPmType */
    private String selectedPmType        = "";
    
    /** equipId */
    private String equipId        = "";
    
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getSelectedWoType()
    {
        return selectedWoType;
    }
    public void setSelectedWoType(String selectedWoType)
    {
        this.selectedWoType = selectedWoType;
    }
    public String getSelectedPmType()
    {
        return selectedPmType;
    }
    public void setSelectedPmType(String selectedPmType)
    {
        this.selectedPmType = selectedPmType;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getFilterEmpId()
    {
        return filterEmpId;
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
    public String getPmPointId() {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId) {
        this.pmPointId = pmPointId;
    }
}