package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 버튼 상세 dto
 * @author  kim21017
 * @version $Id: MaPgMngBtnDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPgMngBtnDetailDTO extends BaseDTO
{
	/** PGBTN_ID */
	private String pgBtnId				= "";
	/** 버튼 id */
	private String buttonId				= "";
	/** 버튼 No */
	private String buttonNo				= "";
	/** 버튼명 */
	private String buttonDesc			= "";
	/** 버튼설명 */
	private String buttonRemark			= "";
	/** 버튼위치 */
	private String buttonLoc			= "";
	/** 버튼위치명 */
	private String buttonLocDesc		= "";
	/** 버튼기능 */
	private String pgButtonDesc			= "";
	/** 비고 */
	private String remark				= "";
	/** 사용여부 */
	private String isUse				= "";
	/** 조회순서 */
	private String ordNo				= "";
	/** 권한 확인 여부 */
	private String isChkauth            = "";
	/** 그룹버튼 시작 지정 */
	private String isSetGroup			= "";
	/** 기본버튼 지정 */
	private String isBasic				= "";
	/**  */
	private String pageId				= "";
	/**  */
	private String keyNo				= "";
	/** 다국어 keytype */
	private String keyType				= "";
	//key_no validation 시 넘기는 key_type
	private String keyTypeStr			= "";
	
	
	

	public String getPgButtonDesc() {
		return pgButtonDesc;
	}
	public void setPgButtonDesc(String pgButtonDesc) {
		this.pgButtonDesc = pgButtonDesc;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyTypeStr() {
		return keyTypeStr;
	}
	public void setKeyTypeStr(String keyTypeStr) {
		this.keyTypeStr = keyTypeStr;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getIsSetGroup() {
		return isSetGroup;
	}
	public void setIsSetGroup(String isSetGroup) {
		this.isSetGroup = isSetGroup;
	}
	public String getIsBasic() {
		return isBasic;
	}
	public void setIsBasic(String isBasic) {
		this.isBasic = isBasic;
	}
	public String getIsChkauth()
    {
        return isChkauth;
    }
    public void setIsChkauth(String isChkauth)
    {
        this.isChkauth = isChkauth;
    }
    public String getButtonNo() {
		return buttonNo;
	}
	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}
	public String getButtonRemark() {
		return buttonRemark;
	}
	public void setButtonRemark(String buttonRemark) {
		this.buttonRemark = buttonRemark;
	}
	public String getPgBtnId() {
		return pgBtnId;
	}
	public void setPgBtnId(String pgBtnId) {
		this.pgBtnId = pgBtnId;
	}
	public String getButtonId() {
		return buttonId;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public String getButtonDesc() {
		return buttonDesc;
	}
	public void setButtonDesc(String buttonDesc) {
		this.buttonDesc = buttonDesc;
	}
	public String getButtonLoc() {
		return buttonLoc;
	}
	public void setButtonLoc(String buttonLoc) {
		this.buttonLoc = buttonLoc;
	}
	public String getButtonLocDesc() {
		return buttonLocDesc;
	}
	public void setButtonLocDesc(String buttonLocDesc) {
		this.buttonLocDesc = buttonLocDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
}