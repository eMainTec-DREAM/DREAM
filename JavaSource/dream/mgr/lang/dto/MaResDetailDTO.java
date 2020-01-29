package dream.mgr.lang.dto;

import common.bean.BaseDTO;

/**
 * ��� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaResDetailDTO extends BaseDTO
{
	/** ID */
	private String langId		= "";
	
	/** ���� Id */
	private String keyTypeId		= "";
	/** ���� �� */
	private String keyTypeDesc		= "";
	/** ��� Id */
	private String lang			= "";
	/** ��� �� */
	private String langDesc			= "";
	/** ǥ�ø� */
	private String keyName			= "";
	/** �ѱ� ǥ�ø� */
	private String keyNameKo		= "";
	/** ���� ǥ�ø� */
	private String keyNameEn		= "";
	/** ��� id */
	private String keyNo			= "";
	/** Web ��뿩�� */
	private String isUseWeb			= "";
	/** Android ��뿩�� */
	private String isUseAndroid		= "";
	/** JS ��뿩�� */
	private String isUseJs			= "";
	/** �����ð� */
	private String creDate			= "";
	/** �����ð� */
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
