package dream.mgr.lang.dto;

import common.bean.BaseDTO;

/**
 * 언어 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaResDetailDTO extends BaseDTO
{
	/** ID */
	private String langId		= "";
	
	/** 구분 Id */
	private String keyTypeId		= "";
	/** 구분 명 */
	private String keyTypeDesc		= "";
	/** 언어 Id */
	private String lang			= "";
	/** 언어 명 */
	private String langDesc			= "";
	/** 표시명 */
	private String keyName			= "";
	/** 한글 표시명 */
	private String keyNameKo		= "";
	/** 영어 표시명 */
	private String keyNameEn		= "";
	/** 등록 id */
	private String keyNo			= "";
	/** Web 사용여부 */
	private String isUseWeb			= "";
	/** Android 사용여부 */
	private String isUseAndroid		= "";
	/** JS 사용여부 */
	private String isUseJs			= "";
	/** 생성시간 */
	private String creDate			= "";
	/** 수정시간 */
	private String updDate			= "";
	
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
		super.setAuditKey(langId);
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getIsUseWeb() {
		return isUseWeb;
	}
	public void setIsUseWeb(String isUseWeb) {
		this.isUseWeb = isUseWeb;
	}
	public String getIsUseAndroid() {
		return isUseAndroid;
	}
	public void setIsUseAndroid(String isUseAndroid) {
		this.isUseAndroid = isUseAndroid;
	}
	public String getIsUseJs() {
		return isUseJs;
	}
	public void setIsUseJs(String isUseJs) {
		this.isUseJs = isUseJs;
	}
	public String getKeyTypeId() {
		return keyTypeId;
	}
	public void setKeyTypeId(String keyTypeId) {
		this.keyTypeId = keyTypeId;
	}
	public String getKeyTypeDesc() {
		return keyTypeDesc;
	}
	public void setKeyTypeDesc(String keyTypeDesc) {
		this.keyTypeDesc = keyTypeDesc;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyNameKo() {
		return keyNameKo;
	}
	public void setKeyNameKo(String keyNameKo) {
		this.keyNameKo = keyNameKo;
	}
	public String getKeyNameEn() {
		return keyNameEn;
	}
	public void setKeyNameEn(String keyNameEn) {
		this.keyNameEn = keyNameEn;
	}
	
}
