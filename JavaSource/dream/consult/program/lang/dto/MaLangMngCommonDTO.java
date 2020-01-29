package dream.consult.program.lang.dto;

import common.bean.BaseDTO;

/**
 * 다국어 공통 DTO
 * @author  kim21017
 * @version $Id: MaLangMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaLangMngCommonDTO extends BaseDTO
{
	/** lang id all*/
	private String langId			= "";
	/** lang id ko */
	private String lang 			= "";
	/** lang id en */
	private String keyType 			= "";
	
	
	
	/** 필터 등록 유형 */
	private String filterKeyType 		= "";
	/** 필터 등록 유형 */
	private String filterKeyTypeDesc 	= "";
	/** 필터 한글화면표시명 */
	private String filterKeyName 		= "";
	/**  언어구분 */
	private String filterLang 			= "";
	/** Filter-언어 명 */
	private String filterLangDesc 			= "";
	
	
	
	
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
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getFilterKeyType() {
		return filterKeyType;
	}
	public void setFilterKeyType(String filterKeyType) {
		this.filterKeyType = filterKeyType;
	}
	public String getFilterKeyTypeDesc() {
		return filterKeyTypeDesc;
	}
	public void setFilterKeyTypeDesc(String filterKeyTypeDesc) {
		this.filterKeyTypeDesc = filterKeyTypeDesc;
	}
	public String getFilterKeyName() {
		return filterKeyName;
	}
	public void setFilterKeyName(String filterKeyName) {
		this.filterKeyName = filterKeyName;
	}
	public String getFilterLang() {
		return filterLang;
	}
	public void setFilterLang(String filterLang) {
		this.filterLang = filterLang;
	}
	public String getFilterLangDesc() {
		return filterLangDesc;
	}
	public void setFilterLangDesc(String filterLangDesc) {
		this.filterLangDesc = filterLangDesc;
	}
	
	
	
	
}
