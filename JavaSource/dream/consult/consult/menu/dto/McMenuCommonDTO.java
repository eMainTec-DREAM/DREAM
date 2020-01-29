package dream.consult.consult.menu.dto;

import common.bean.BaseDTO;

/**
 * 버튼 공통 DTO
 * @author  kim21017
 * @version $Id: McMenuCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class McMenuCommonDTO extends BaseDTO
{
	/** 메뉴 ID */
	private String menuId 				= "";
	/** 메뉴 코드 */
	private String filterMenuNo 		= "";
	/** 필터 메뉴 ID */
	private String filterMenuId 		= "";
	/** 필터 메뉴 명 */
	private String filterMenuDesc 		= "";
	/** 필터 상위메뉴ID */
	private String filterPMenuId	 	= "";
	/** 필터 상위메뉴Desc */
	private String filterPMenuDesc	 	= "";
	/** 필터 화면명 */
	private String filterPageDesc	 	= "";
	
	
	
	
	public String getFilterMenuNo() {
		return filterMenuNo;
	}
	public void setFilterMenuNo(String filterMenuNo) {
		this.filterMenuNo = filterMenuNo;
	}
	public String getFilterPageDesc() {
		return filterPageDesc;
	}
	public void setFilterPageDesc(String filterPageDesc) {
		this.filterPageDesc = filterPageDesc;
	}
	public String getFilterPMenuDesc() {
		return filterPMenuDesc;
	}
	public void setFilterPMenuDesc(String filterPMenuDesc) {
		this.filterPMenuDesc = filterPMenuDesc;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	public String getFilterPMenuId() {
		return filterPMenuId;
	}
	public void setFilterPMenuId(String filterPMenuId) {
		this.filterPMenuId = filterPMenuId;
	}
}
