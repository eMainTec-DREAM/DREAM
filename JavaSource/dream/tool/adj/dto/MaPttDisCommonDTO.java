package dream.tool.adj.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id: MaPttDisCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPttDisCommonDTO extends BaseDTO
{
    /** KEY ID */
    private String ptdisuselistId   = "";
    /** Filter Title  */
    private String title        = "";

    
    /** 필터-부서Id */
	private String filterDeptId        = "";
	/** 필터-부서명 */
	private String filterDeptDesc      = "";
	/** 필터-폐기일자 From */
	private String filterDisStartDate  = "";
	/** 필터-폐기일자 To */
	private String filterDisEndDate    = "";
	/** 필터-처리자Id */
	private String filterExeBy     = "";
	/** 필터-처리자명 */
	private String filterExeName = "";
	/** 필터-품명/규격명 */
	private String filterPartNameSize  = "";

	/** 상태 -  */
	private String ptDisStatus        = "";
	/** 상태명 - */
	private String ptDisStatusDesc    = "";
	
	/** 필터-창고코드 */
	private String filterWcodeId        = "";
	/** 필터-창고명 */
	private String filterWname      = "";

	public String getPtdisuselistId() {
		return ptdisuselistId;
	}
	public void setPtdisuselistId(String ptdisuselistId) {
		this.ptdisuselistId = ptdisuselistId;
		super.setAuditKey(ptdisuselistId);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getFilterDisStartDate() {
		return filterDisStartDate;
	}
	public void setFilterDisStartDate(String filterDisStartDate) {
		this.filterDisStartDate = filterDisStartDate;
	}
	public String getFilterDisEndDate() {
		return filterDisEndDate;
	}
	public void setFilterDisEndDate(String filterDisEndDate) {
		this.filterDisEndDate = filterDisEndDate;
	}
	public String getFilterExeBy() {
		return filterExeBy;
	}
	public void setFilterExeBy(String filterExeBy) {
		this.filterExeBy = filterExeBy;
	}
	public String getFilterExeName() {
		return filterExeName;
	}
	public void setFilterExeName(String filterExeName) {
		this.filterExeName = filterExeName;
	}
	public String getFilterPartNameSize() {
		return filterPartNameSize;
	}
	public void setFilterPartNameSize(String filterPartNameSize) {
		this.filterPartNameSize = filterPartNameSize;
	}
	public String getPtDisStatus() {
		return ptDisStatus;
	}
	public void setPtDisStatus(String ptDisStatus) {
		this.ptDisStatus = ptDisStatus;
	}
	public String getPtDisStatusDesc() {
		return ptDisStatusDesc;
	}
	public void setPtDisStatusDesc(String ptDisStatusDesc) {
		this.ptDisStatusDesc = ptDisStatusDesc;
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
	
	
}
