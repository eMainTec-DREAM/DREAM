package dream.mgr.rpt.logintrylog.dto;

import common.bean.BaseDTO;

/**
 * �α��νõ��α� List Page - ���� DTO
 * @author youngjoo38 
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrRptLoginTryLogCommonDTO extends BaseDTO
{
	/** �α��νõ��α�id (key) */
	private String loginTryLogId 			= "";
	/**Filter ���� FROM */
    private String filterLoginFromDate 		= "";    
    /**Filter ���� TO */
    private String filterLoginToDate   		= "";    
    /** Filter ����� NO */
    private String filterUserId				= "";
    /** Filter �͹̳� Ÿ�� ID */
    private String filterTerminalTypeId 	= "";
    /** Filter �͹̳� Ÿ�� DESC */
    private String filterTerminalTypeDesc	= "";
    /** Filter �͹̳� No */
    private String filterTerminalNo 		= "";
    /** Filter �͹̳� Ver */
    private String filterTerminalVer 		= "";
    
    /** Filter �������� ID */
    private String filterIsSuccess 			= "";
    /** Filter �������� DESC */
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
