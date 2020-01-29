package dream.consult.program.menu.dto;

import common.bean.BaseDTO;

/**
 * ��ư ���� DTO
 * @author  kim21017
 * @version $Id: MaMenuMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaMenuMngCommonDTO extends BaseDTO
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
	/** ���� ���񽺱���id */
	private String filterServiceTypeId	 	= "";
	/** ���� ���񽺱��и� */
	private String filterServiceTypeDesc	 	= "";
	/** ���� ȭ�� ID */
	private String filterFileName	 	= "";
	
	
	public String getFilterFileName() {
		return filterFileName;
	}
	public void setFilterFileName(String filterFileName) {
		this.filterFileName = filterFileName;
	}
	public String getFilterServiceTypeId() {
		return filterServiceTypeId;
	}
	public void setFilterServiceTypeId(String filterServiceTypeId) {
		this.filterServiceTypeId = filterServiceTypeId;
	}
	public String getFilterServiceTypeDesc() {
		return filterServiceTypeDesc;
	}
	public void setFilterServiceTypeDesc(String filterServiceTypeDesc) {
		this.filterServiceTypeDesc = filterServiceTypeDesc;
	}
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
