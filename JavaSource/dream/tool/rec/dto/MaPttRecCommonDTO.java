package dream.tool.rec.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매입고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttRecCommonDTO extends BaseDTO
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
	private String filterVendorId     = "";
	/** 필터-거래처명 */
	private String filterVendorDesc   = "";
	/** 상태 - 작업현황에서 팝업 노출 시 사용 */
	private String prRecStatus        = "";
	/** 상태명 - 작업현황에서 팝업 노출 시 사용 */
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
