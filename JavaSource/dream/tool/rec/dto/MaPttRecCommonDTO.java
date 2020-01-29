package dream.tool.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttRecCommonDTO extends BaseDTO
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
	private String filterVendorId     = "";
	/** ����-�ŷ�ó�� */
	private String filterVendorDesc   = "";
	/** ���� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String prRecStatus        = "";
	/** ���¸� - �۾���Ȳ���� �˾� ���� �� ��� */
	private String prRecStatusDesc    = "";
	
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
