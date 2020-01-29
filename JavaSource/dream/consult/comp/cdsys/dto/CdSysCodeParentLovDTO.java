package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;
/**
 * 시스템코드 부모 LOV DTO
 * @author kim21017
 * @version $Id: CdSysCodeParentLovDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class CdSysCodeParentLovDTO extends BaseDTO
{
	/** 시스템코드*/
	private String cdSysMId				= "";
	/** 시스템코드 list type*/
	private String listType				= "";
	/** 필터 - 시스템코드 list type*/
	private String filterListType		= "";
	/** 필터 - 시스템코드 명 */
	private String filterCdSysMDesc		= "";
	
	
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
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
	public String getFilterCdSysMDesc() {
		return filterCdSysMDesc;
	}
	public void setFilterCdSysMDesc(String filterCdSysMDesc) {
		this.filterCdSysMDesc = filterCdSysMDesc;
	}
	
}
