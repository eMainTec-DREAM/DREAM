package dream.mgr.rpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �α��ηα� List Page - ���� DTO
 * @author euna0207 
 * @version $Id: MgrRptLoginLogCommonDTO.java,v 1.0 2018/08/23 15:19:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class MgrRptLoginLogCommonDTO extends BaseDTO
{
	/** �α��ηα�id (key) */
	private String loginccLogId 			= "";
	/**Filter ���� FROM */
    private String filterLoginFromDate 		= "";    
    /**Filter ���� TO */
    private String filterLoginToDate   		= "";    
    /** Filter ����� ID */
    private String filterUserId 			= "";
    /** Filter ����� DESC */
    private String filterUserDesc			= "";
    /** Filter �͹̳� Ÿ�� ID */
    private String filterTerminalTypeId 	= "";
    /** Filter �͹̳� Ÿ�� DESC */
    private String filterTerminalTypeDesc	= "";
    /** Filter �͹̳� No ID */
    private String filterTerminalNo 		= "";
    /** Filter �͹̳� Ver ID */
    private String filterTerminalVer 		= "";
    /** Filter �μ� ID */
    private String filterDeptId 			= "";
    /** Filter �μ� DESC */
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
