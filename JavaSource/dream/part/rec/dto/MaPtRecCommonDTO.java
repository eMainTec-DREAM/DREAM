package dream.part.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매입고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRecCommonDTO extends BaseDTO
{
    /** ID */
    private String prRecListId         = "";
    
    /** 필터-부서Id */
    private String filterDeptId        = "";
    /** 필터-부서명 */
    private String filterDeptDesc      = "";
    /** 필터-입고일자 From */
    private String filterRecStartDate  = "";
    /** 필터-입고일자 To */
    private String filterRecEndDate    = "";
    /** 필터-검수자Id */
    private String filterInspector     = "";
    /** 필터-검수자명 */
    private String filterInspectorName = "";
    /** 필터-품명/규격명 */
    private String filterPartNameSize  = "";
    /** 필터-거래처Id */
    private String filterVendorId      = "";
    /** 필터-거래처명 */
    private String filterVendorDesc    = "";
    /** 필터-구매신청번호 */
    private String filterReqNo          = "";
    /** 필터-발주번호 */
    private String filterPoNo           = "";
    
    /** 상태 - 작업현황에서 팝업 노출 시 사용 */
    private String prRecStatus         = "";
    /** 상태명 - 작업현황에서 팝업 노출 시 사용 */
    private String prRecStatusDesc     = "";
    private String partId              = "";
    /**  */
    private String isPartRec           = "";

    /** 필터-공장 ID */
    private String filterPlantId       = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc    = "";

    /** Excel Tab No */
    private String exceltabNo         = "";
    /** 발주품목ID */
    private String ptPoItemId         = "";
    
    
    public String getPtPoItemId()
    {
        return ptPoItemId;
    }
    public void setPtPoItemId(String ptPoItemId)
    {
        this.ptPoItemId = ptPoItemId;
    }
    public String getFilterReqNo()
    {
        return filterReqNo;
    }
    public void setFilterReqNo(String filterReqNo)
    {
        this.filterReqNo = filterReqNo;
    }
    public String getFilterPoNo()
    {
        return filterPoNo;
    }
    public void setFilterPoNo(String filterPoNo)
    {
        this.filterPoNo = filterPoNo;
    }
    public String getExceltabNo() {
        return exceltabNo;
    }
    public void setExceltabNo(String exceltabNo) {
        this.exceltabNo = exceltabNo;
    }
    public String getIsPartRec()
    {
        return isPartRec;
    }
    public void setIsPartRec(String isPartRec)
    {
        this.isPartRec = isPartRec;
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
    public String getPartId() {
        return partId;
    }
    public void setPartId(String partId) {
        this.partId = partId;
    }
    public String getPrRecStatusDesc() {
        return prRecStatusDesc;
    }
    public void setPrRecStatusDesc(String prRecStatusDesc) {
        this.prRecStatusDesc = prRecStatusDesc;
    }
    public String getPrRecStatus() {
        return prRecStatus;
    }
    public void setPrRecStatus(String prRecStatus) {
        this.prRecStatus = prRecStatus;
    }
    public String getFilterInspectorName()
    {
        return filterInspectorName;
    }
    public void setFilterInspectorName(String filterInspectorName)
    {
        this.filterInspectorName = filterInspectorName;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterRecStartDate()
    {
        return filterRecStartDate;
    }
    public void setFilterRecStartDate(String filterRecStartDate)
    {
        this.filterRecStartDate = CommonUtil.convertDate(filterRecStartDate);
    }
    public String getFilterRecEndDate()
    {
        return filterRecEndDate;
    }
    public void setFilterRecEndDate(String filterRecEndDate)
    {
        this.filterRecEndDate = CommonUtil.convertDate(filterRecEndDate);
    }
    public String getFilterInspector()
    {
        return filterInspector;
    }
    public void setFilterInspector(String filterInspector)
    {
        this.filterInspector = filterInspector;
    }
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
    public String getPrRecListId()
    {
        return prRecListId;
    }
    public void setPrRecListId(String prRecListId)
    {
        this.prRecListId = prRecListId;
        super.setAuditKey(prRecListId);
    }
    public String getFilterVendorId()
    {
        return filterVendorId;
    }
    public void setFilterVendorId(String filterVendorId)
    {
        this.filterVendorId = filterVendorId;
    }
    public String getFilterVendorDesc()
    {
        return filterVendorDesc;
    }
    public void setFilterVendorDesc(String filterVendorDesc)
    {
        this.filterVendorDesc = filterVendorDesc;
    }

}
