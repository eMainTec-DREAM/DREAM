package dream.mgr.rpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 로그인로그 List Page - 공통 DTO
 * @author euna0207 
 * @version $Id: MgrRptLoginLogCommonDTO.java,v 1.0 2018/08/23 15:19:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class MgrRptLoginLogCommonDTO extends BaseDTO
{
	/** 로그인로그id (key) */
	private String loginccLogId 			= "";
	/**Filter 일자 FROM */
    private String filterLoginFromDate 		= "";    
    /**Filter 일자 TO */
    private String filterLoginToDate   		= "";    
    /** Filter 사용자 ID */
    private String filterUserId 			= "";
    /** Filter 사용자 DESC */
    private String filterUserDesc			= "";
    /** Filter 터미널 타입 ID */
    private String filterTerminalTypeId 	= "";
    /** Filter 터미널 타입 DESC */
    private String filterTerminalTypeDesc	= "";
    /** Filter 터미널 No ID */
    private String filterTerminalNo 		= "";
    /** Filter 터미널 Ver ID */
    private String filterTerminalVer 		= "";
    /** Filter 부서 ID */
    private String filterDeptId 			= "";
    /** Filter 부서 DESC */
    private String filterDeptDesc			= "";
    
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
	public String getLoginccLogId() {
		return loginccLogId;
	}
	public void setLoginccLogId(String loginccLogId) {
		this.loginccLogId = loginccLogId;
	}
	public String getFilterLoginFromDate() {
		return filterLoginFromDate;
	}
	public void setFilterLoginFromDate(String filterLoginFromDate) {
		this.filterLoginFromDate = filterLoginFromDate;
	}
	public String getFilterLoginToDate() {
		return filterLoginToDate;
	}
	public void setFilterLoginToDate(String filterLoginToDate) {
		this.filterLoginToDate = filterLoginToDate;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	public String getFilterTerminalTypeId() {
		return filterTerminalTypeId;
	}
	public void setFilterTerminalTypeId(String filterTerminalTypeId) {
		this.filterTerminalTypeId = filterTerminalTypeId;
	}
	public String getFilterTerminalTypeDesc() {
		return filterTerminalTypeDesc;
	}
	public void setFilterTerminalTypeDesc(String filterTerminalTypeDesc) {
		this.filterTerminalTypeDesc = filterTerminalTypeDesc;
	}
	public String getFilterTerminalNo() {
		return filterTerminalNo;
	}
	public void setFilterTerminalNo(String filterTerminalNo) {
		this.filterTerminalNo = filterTerminalNo;
	}
	public String getFilterTerminalVer() {
		return filterTerminalVer;
	}
	public void setFilterTerminalVer(String filterTerminalVer) {
		this.filterTerminalVer = filterTerminalVer;
	}
	
    
}
