package dream.mgr.lang.dto;

import common.bean.BaseDTO;

/**
 * 언어 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaResCommonDTO extends BaseDTO
{
	/** Id */
	private String keyTypeNo				= "";
	/** lang Id */
	private String langId					= "";
	/** Filter-언어id */
	private String filterLangId 			= "";
	/** Filter-언어 명 */
	private String filterLangDesc 			= "";
	/** Filter-표시명 */
	private String filterKeyName			= "";
	/** Filter-등록유형 */
	private String filterKeyTypeId			= "";
	private String filterKeyTypeDesc		= "";
	/** Filter-등록ID */
	private String filterKeyNo			= "";
	
	public String getFilterKeyTypeDesc() {
		return filterKeyTypeDesc;
	}
	public void setFilterKeyTypeDesc(String filterKeyTypeDesc) {
		this.filterKeyTypeDesc = filterKeyTypeDesc;
	}
	public String getFilterKeyTypeId() {
		return filterKeyTypeId;
	}
	public void setFilterKeyTypeId(String filterKeyTypeId) {
		this.filterKeyTypeId = filterKeyTypeId;
	}
	public String getFilterKeyNo() {
		return filterKeyNo;
	}
	public void setFilterKeyNo(String filterKeyNo) {
		this.filterKeyNo = filterKeyNo;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getKeyTypeNo() {
		return keyTypeNo;
	}
	public void setKeyTypeNo(String keyTypeNo) {
		this.keyTypeNo = keyTypeNo;
		super.setAuditKey(keyTypeNo);
	}
	public String getFilterLangId() {
		return filterLangId;
	}
	public void setFilterLangId(String filterLangId) {
		this.filterLangId = filterLangId;
	}
	public String getFilterLangDesc() {
		return filterLangDesc;
	}
	public void setFilterLangDesc(String filterLangDesc) {
		this.filterLangDesc = filterLangDesc;
	}
	public String getFilterKeyName() {
		return filterKeyName;
	}
	public void setFilterKeyName(String filterKeyName) {
		this.filterKeyName = filterKeyName;
	}
}
