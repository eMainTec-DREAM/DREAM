package dream.mgr.rpt.dto;

import common.bean.BaseDTO;

/**
 * 화면접속로그 List Page - 공통 DTO
 * @author euna0207 
 * @version $Id: MgrRptScrnLogCommonDTO.java,v 1.0 2018/08/23 15:19:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class MgrRptScrnLogCommonDTO extends BaseDTO
{
	/** 로그인로그 ID */
	private String loginccLogId	   = "";
	/**Filter 일자 FROM */
    private String filterScrnFromDate  = "";    
    /**Filter 일자 TO */
    private String filterScrnToDate    = "";    
    /** Filter 사용자 ID */
    private String filterUserId        = "";
    /** Filter 사용자 DESC */
    private String filterUserDesc      = "";
    /** Filter 메뉴 ID */
    private String filterMenuId        = "";
    /** Filter 메뉴 DESC */
    private String filterMenuDesc      = "";
    /** Filter 화면 ID */
    private String filterScrnId        = "";
    /** Filter 화면 DESC */
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
