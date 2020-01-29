package dream.part.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtRecCommonDTO extends BaseDTO
{
    /** ID */
    private String prRecListId         = "";
    
    /** ����-�μ�Id */
    private String filterDeptId        = "";
    /** ����-�μ��� */
    private String filterDeptDesc      = "";
    /** ����-�԰����� From */
    private String filterRecStartDate  = "";
    /** ����-�԰����� To */
    private String filterRecEndDate    = "";
    /** ����-�˼���Id */
    private String filterInspector     = "";
    /** ����-�˼��ڸ� */
    private String filterInspectorName = "";
    /** ����-ǰ��/�԰ݸ� */
    private String filterPartNameSize  = "";
    /** ����-�ŷ�óId */
    private String filterVendorId      = "";
    /** ����-�ŷ�ó�� */
    private String filterVendorDesc    = "";
    /** ����-���Ž�û��ȣ */
    private String filterReqNo          = "";
    /** ����-���ֹ�ȣ */
    private String filterPoNo           = "";
    
    /** ���� - �۾���Ȳ���� �˾� ���� �� ��� */
    private String prRecStatus         = "";
    /** ���¸� - �۾���Ȳ���� �˾� ���� �� ��� */
    private String prRecStatusDesc     = "";
    private String partId              = "";
    /**  */
    private String isPartRec           = "";

    /** ����-���� ID */
    private String filterPlantId       = "";
    /** ����-���� DESC */
    private String filterPlantDesc    = "";

    /** Excel Tab No */
    private String exceltabNo         = "";
    /** ����ǰ��ID */
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
