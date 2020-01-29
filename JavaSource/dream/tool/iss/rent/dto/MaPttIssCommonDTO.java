package dream.tool.iss.rent.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매입고 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttIssCommonDTO extends BaseDTO
{
	/** ID */
	private String ptIssListId         = "";
	
	/** 필터-부서Id */
	private String filterDeptId        = "";
	/** 필터-부서명 */
	private String filterDeptDesc      = "";
	/** 필터-입고일자 From */
	private String filterIssStartDate  = "";
	/** 필터-입고일자 To */
	private String filterIssEndDate    = "";
	/** 필터-수령자Id */
	private String filterRecBy     = "";
	/** 필터-수령자명 */
	private String filterRecName = "";
	/** 필터-품명/규격명 */
	private String filterPartNameSize  = "";

	/** 상태 - 작업현황에서 팝업 노출 시 사용 */
	private String ptIssStatus        = "";
	/** 상태명 - 작업현황에서 팝업 노출 시 사용 */
	private String ptIssStatusDesc    = "";
	
	/** 필터-창고코드 */
	private String filterWcodeId        = "";
	/** 필터-창고명 */
	private String filterWname      = "";
	
    public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}

    public String getPtIssStatus() {
		return ptIssStatus;
	}
	public void setPtIssStatus(String ptIssStatus) {
		this.ptIssStatus = ptIssStatus;
	}
	public String getPtIssStatusDesc() {
		return ptIssStatusDesc;
	}
	public void setPtIssStatusDesc(String ptIssStatusDesc) {
		this.ptIssStatusDesc = ptIssStatusDesc;
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
    public String getFilterPartNameSize()
    {
        return filterPartNameSize;
    }
    public void setFilterPartNameSize(String filterPartNameSize)
    {
        this.filterPartNameSize = filterPartNameSize;
    }
	public String getPtIssListId() {
		return ptIssListId;
	}
	public void setPtIssListId(String ptIssListId) {
		this.ptIssListId = ptIssListId;
		super.setAuditKey(ptIssListId);
	}
	public String getFilterIssStartDate() {
		return filterIssStartDate;
	}
	public void setFilterIssStartDate(String filterIssStartDate) {
		this.filterIssStartDate = filterIssStartDate;
	}
	public String getFilterIssEndDate() {
		return filterIssEndDate;
	}
	public void setFilterIssEndDate(String filterIssEndDate) {
		this.filterIssEndDate = filterIssEndDate;
	}
	public String getFilterRecBy() {
		return filterRecBy;
	}
	public void setFilterRecBy(String filterRecBy) {
		this.filterRecBy = filterRecBy;
	}
	public String getFilterRecName() {
		return filterRecName;
	}
	public void setFilterRecName(String filterRecName) {
		this.filterRecName = filterRecName;
	}
}