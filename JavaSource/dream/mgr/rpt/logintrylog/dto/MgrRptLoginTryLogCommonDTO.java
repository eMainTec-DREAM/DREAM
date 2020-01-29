package dream.mgr.rpt.logintrylog.dto;

import common.bean.BaseDTO;

/**
 * 로그인시도로그 List Page - 공통 DTO
 * @author youngjoo38 
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrRptLoginTryLogCommonDTO extends BaseDTO
{
	/** 로그인시도로그id (key) */
	private String loginTryLogId 			= "";
	/**Filter 일자 FROM */
    private String filterLoginFromDate 		= "";    
    /**Filter 일자 TO */
    private String filterLoginToDate   		= "";    
    /** Filter 사용자 NO */
    private String filterUserId				= "";
    /** Filter 터미널 타입 ID */
    private String filterTerminalTypeId 	= "";
    /** Filter 터미널 타입 DESC */
    private String filterTerminalTypeDesc	= "";
    /** Filter 터미널 No */
    private String filterTerminalNo 		= "";
    /** Filter 터미널 Ver */
    private String filterTerminalVer 		= "";
    
    /** Filter 성공여부 ID */
    private String filterIsSuccess 			= "";
    /** Filter 성공여부 DESC */
    private String filterIsSuccessDesc 		= "";
    
	public String getFilterIsSuccess() {
		return filterIsSuccess;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public void setFilterIsSuccess(String filterIsSuccess) {
		this.filterIsSuccess = filterIsSuccess;
	}
	public String getFilterIsSuccessDesc() {
		return filterIsSuccessDesc;
	}
	public void setFilterIsSuccessDesc(String filterIsSuccessDesc) {
		this.filterIsSuccessDesc = filterIsSuccessDesc;
	}
	public String getLoginTryLogId() {
		return loginTryLogId;
	}
	public void setLoginTryLogId(String loginTryLogId) {
		this.loginTryLogId = loginTryLogId;
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
