package dream.consult.consult.menu.dto;

import common.bean.BaseDTO;

/**
 * 버튼 - 상세 DTO
 * @author  kim21017
 * @version $Id: McMenuDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class McMenuDetailDTO extends BaseDTO
{
	/** 메뉴ID */
	private String menuId 			= "";
	/** 메뉴NO */
	private String menuNo 			= "";
	/** 메뉴명 */
	private String menuDesc 		= "";
	/** 상위메뉴ID */
	private String pmenuId 			= "";
	/** 상위메뉴명 */
	private String pmenuDesc 		= "";
	/** 조회순서 */
	private String ordNo 			= "";
	/** 화면ID */
	private String pageId 			= "";
	/** 화면명 */
	private String pageDesc			= "";
	/** 매개변수 */
	private String paramValue		= "";
	/** System Menu 여부 */
	private String isSystem			= "";
	/** 다국어 keyno */
	private String keyNo			= "";
	/** 다국어 keytype */
	private String keyType			= "";
	//key_no validation 시 넘기는 key_type
	private String keyTypeStr		= "";
	/** 사용 여부 */
	private String isUse			= "";
	/** 비고 */
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
