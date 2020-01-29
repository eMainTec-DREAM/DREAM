package dream.consult.consult.menu.dto;

import common.bean.BaseDTO;

/**
 * ��ư - �� DTO
 * @author  kim21017
 * @version $Id: McMenuDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class McMenuDetailDTO extends BaseDTO
{
	/** �޴�ID */
	private String menuId 			= "";
	/** �޴�NO */
	private String menuNo 			= "";
	/** �޴��� */
	private String menuDesc 		= "";
	/** �����޴�ID */
	private String pmenuId 			= "";
	/** �����޴��� */
	private String pmenuDesc 		= "";
	/** ��ȸ���� */
	private String ordNo 			= "";
	/** ȭ��ID */
	private String pageId 			= "";
	/** ȭ��� */
	private String pageDesc			= "";
	/** �Ű����� */
	private String paramValue		= "";
	/** System Menu ���� */
	private String isSystem			= "";
	/** �ٱ��� keyno */
	private String keyNo			= "";
	/** �ٱ��� keytype */
	private String keyType			= "";
	//key_no validation �� �ѱ�� key_type
	private String keyTypeStr		= "";
	/** ��� ���� */
	private String isUse			= "";
	/** ��� */
	private String remark			= "";
	
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getKeyTypeStr() {
		return keyTypeStr;
	}
	public void setKeyTypeStr(String keyTypeStr) {
		this.keyTypeStr = keyTypeStr;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getPmenuId() {
		return pmenuId;
	}
	public void setPmenuId(String pmenuId) {
		this.pmenuId = pmenuId;
	}
	public String getPmenuDesc() {
		return pmenuDesc;
	}
	public void setPmenuDesc(String pmenuDesc) {
		this.pmenuDesc = pmenuDesc;
	}
}
