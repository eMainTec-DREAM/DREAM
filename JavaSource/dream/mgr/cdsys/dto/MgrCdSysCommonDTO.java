package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� ���� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrCdSysCommonDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysMId 				= "";
	/** ���� �ڵ����� */
	private String filterListType 			= "";
	/** ���� �ڵ����� �� */
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
