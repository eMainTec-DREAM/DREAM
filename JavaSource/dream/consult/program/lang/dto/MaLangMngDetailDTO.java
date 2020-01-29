package dream.consult.program.lang.dto;

import common.bean.BaseDTO;

/**
 * 다국어 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaLangMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaLangMngDetailDTO extends BaseDTO
{
	/** langId */
	private String langId 		= "";
	/** langId En */
	private String lang 		= "";
	/** langId Ko*/
	private String langDesc 		= "";
	/** 등록 유형 */
	private String keyType 			= "";
	/** 등록 유형 */
	private String keyTypeDesc			= "";
	/** 키 아이디 */
	private String keyNo 			= "";
	/** 키 아이디 */
	private String keyName 			= "";
	private String isCommJsUse 			= "";
	private String remark 			= "";
	private String useWeb 			= "";
	private String useAndroid 			= "";
	
	
	
	
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
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
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getIsCommJsUse() {
		return isCommJsUse;
	}
	public void setIsCommJsUse(String isCommJsUse) {
		this.isCommJsUse = isCommJsUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUseWeb() {
		return useWeb;
	}
	public void setUseWeb(String useWeb) {
		this.useWeb = useWeb;
	}
	public String getUseAndroid() {
		return useAndroid;
	}
	public void setUseAndroid(String useAndroid) {
		this.useAndroid = useAndroid;
	}
	
	
}
