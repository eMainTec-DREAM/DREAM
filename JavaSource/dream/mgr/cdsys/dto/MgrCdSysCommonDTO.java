package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 공통 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrCdSysCommonDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysMId 				= "";
	/** 필터 코드유형 */
	private String filterListType 			= "";
	/** 필터 코드유형 명 */
	private String filterListTypeDesc		= "";
	
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
