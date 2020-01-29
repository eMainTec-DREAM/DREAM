package dream.consult.consult.menu.dto;

import common.bean.BaseDTO;

/**
 * ��ư ���� DTO
 * @author  kim21017
 * @version $Id: McMenuCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class McMenuCommonDTO extends BaseDTO
{
	/** �޴� ID */
	private String menuId 				= "";
	/** �޴� �ڵ� */
	private String filterMenuNo 		= "";
	/** ���� �޴� ID */
	private String filterMenuId 		= "";
	/** ���� �޴� �� */
	private String filterMenuDesc 		= "";
	/** ���� �����޴�ID */
	private String filterPMenuId	 	= "";
	/** ���� �����޴�Desc */
	private String filterPMenuDesc	 	= "";
	/** ���� ȭ��� */
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
