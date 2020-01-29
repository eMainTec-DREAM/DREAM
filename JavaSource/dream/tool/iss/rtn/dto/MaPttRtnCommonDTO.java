package dream.tool.iss.rtn.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공기구반납 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPttRtnCommonDTO extends BaseDTO
{
	/** ID */
	private String ptRtnListId         = "";
	
	/** 필터-부서Id */
	private String filterDeptId        = "";
	/** 필터-부서명 */
	private String filterDeptDesc      = "";
	/** 필터-반납일자 From */
	private String filterRtnStartDate  = "";
	/** 필터-반납일자 To */
	private String filterRtnEndDate    = "";
	/** 필터-수령자Id */
	private String filterRecBy     = "";
	/** 필터-수령자명 */
	private String filterRecName = "";
	/** 필터-품명/규격명 */
	private String filterPartNameSize  = "";

	/** 상태 - 작업현황에서 팝업 노출 시 사용 */
	private String ptRtnStatus        = "";
	/** 상태명 - 작업현황에서 팝업 노출 시 사용 */
	private String ptRtnStatusDesc    = "";
	
	/** 필터-창고코드 */
	private String filterWcodeId        = "";
	/** 필터-창고명 */
	private String filterWname      = "";
	
    public String getPtRtnListId() {
		return ptRtnListId;
	}
	public void setPtRtnListId(String ptRtnListId) {
		this.ptRtnListId = ptRtnListId;
		super.setAuditKey(ptRtnListId);
	}
	public String getFilterRtnStartDate() {
		return filterRtnStartDate;
	}
	public void setFilterRtnStartDate(String filterRtnStartDate) {
		this.filterRtnStartDate = filterRtnStartDate;
	}
	public String getFilterRtnEndDate() {
		return filterRtnEndDate;
	}
	public void setFilterRtnEndDate(String filterRtnEndDate) {
		this.filterRtnEndDate = filterRtnEndDate;
	}
	public String getPtRtnStatus() {
		return ptRtnStatus;
	}
	public void setPtRtnStatus(String ptRtnStatus) {
		this.ptRtnStatus = ptRtnStatus;
	}
	public String getPtRtnStatusDesc() {
		return ptRtnStatusDesc;
	}
	public void setPtRtnStatusDesc(String ptRtnStatusDesc) {
		this.ptRtnStatusDesc = ptRtnStatusDesc;
	}
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