package dream.mgr.rpt.dto;

import common.bean.BaseDTO;

/**
 * ȭ�����ӷα� List Page - ���� DTO
 * @author euna0207 
 * @version $Id: MgrRptScrnLogCommonDTO.java,v 1.0 2018/08/23 15:19:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class MgrRptScrnLogCommonDTO extends BaseDTO
{
	/** �α��ηα� ID */
	private String loginccLogId	   = "";
	/**Filter ���� FROM */
    private String filterScrnFromDate  = "";    
    /**Filter ���� TO */
    private String filterScrnToDate    = "";    
    /** Filter ����� ID */
    private String filterUserId        = "";
    /** Filter ����� DESC */
    private String filterUserDesc      = "";
    /** Filter �޴� ID */
    private String filterMenuId        = "";
    /** Filter �޴� DESC */
    private String filterMenuDesc      = "";
    /** Filter ȭ�� ID */
    private String filterScrnId        = "";
    /** Filter ȭ�� DESC */
    private String filterScrnDesc      = "";
    
	public String getLoginccLogId() {
		return loginccLogId;
	}
	public void setLoginccLogId(String loginccLogId) {
		this.loginccLogId = loginccLogId;
	}
	public String getFilterScrnFromDate() {
		return filterScrnFromDate;
	}
	public void setFilterScrnFromDate(String filterScrnFromDate) {
		this.filterScrnFromDate = filterScrnFromDate;
	}
	public String getFilterScrnToDate() {
		return filterScrnToDate;
	}
	public void setFilterScrnToDate(String filterScrnToDate) {
		this.filterScrnToDate = filterScrnToDate;
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
	public String getFilterMenuId() {
		return filterMenuId;
	}
	public void setFilterMenuId(String filterMenuId) {
		this.filterMenuId = filterMenuId;
	}
	public String getFilterMenuDesc() {
		return filterMenuDesc;
	}
	public void setFilterMenuDesc(String filterMenuDesc) {
		this.filterMenuDesc = filterMenuDesc;
	}
	public String getFilterScrnId() {
		return filterScrnId;
	}
	public void setFilterScrnId(String filterScrnId) {
		this.filterScrnId = filterScrnId;
	}
	public String getFilterScrnDesc() {
		return filterScrnDesc;
	}
	public void setFilterScrnDesc(String filterScrnDesc) {
		this.filterScrnDesc = filterScrnDesc;
	}
    
}
