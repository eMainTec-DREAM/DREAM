package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 공통 DTO
 * @author  kim21017
 * @version $Id: MaCdSysCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaCdSysCommonDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysMId 				= "";
	/** 필터 코드유형 */
	private String filterListType 			= "";
	/** 필터 코드유형 명 */
	private String filterListTypeDesc		= "";
	/** 필터 코드 분류 */
	private String filterListTypeCateg		= "";
	/** 필터 코드 분류 */
	private String paramListTypeCateg		= "";
	/** 필터 시스템코드 */
	private String filterSyscode 			= "";
	/** 필터 사용여부 */
	private String filterIsuse 				= "";
	
	
	
	public String getFilterSyscode() {
		return filterSyscode;
	}
	public void setFilterSyscode(String filterSyscode) {
		this.filterSyscode = filterSyscode;
	}
	public String getFilterIsuse() {
		return filterIsuse;
	}
	public void setFilterIsuse(String filterIsuse) {
		this.filterIsuse = filterIsuse;
	}
	public String getParamListTypeCateg() {
		return paramListTypeCateg;
	}
	public void setParamListTypeCateg(String paramListTypeCateg) {
		this.paramListTypeCateg = paramListTypeCateg;
	}
	public String getFilterListTypeCateg() {
		return filterListTypeCateg;
	}
	public void setFilterListTypeCateg(String filterListTypeCateg) {
		this.filterListTypeCateg = filterListTypeCateg;
	}
	public String getCdSysMId() {
		return cdSysMId;
	}
	public void setCdSysMId(String cdSysMId) {
		this.cdSysMId = cdSysMId;
	}
	public String getFilterListType() {
		return filterListType;
	}
	public void setFilterListType(String filterListType) {
		this.filterListType = filterListType;
	}
	public String getFilterListTypeDesc() {
		return filterListTypeDesc;
	}
	public void setFilterListTypeDesc(String filterListTypeDesc) {
		this.filterListTypeDesc = filterListTypeDesc;
	}
}
