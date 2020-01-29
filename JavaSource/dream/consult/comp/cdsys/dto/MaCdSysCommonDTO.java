package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� ���� DTO
 * @author  kim21017
 * @version $Id: MaCdSysCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaCdSysCommonDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysMId 				= "";
	/** ���� �ڵ����� */
	private String filterListType 			= "";
	/** ���� �ڵ����� �� */
	private String filterListTypeDesc		= "";
	/** ���� �ڵ� �з� */
	private String filterListTypeCateg		= "";
	/** ���� �ڵ� �з� */
	private String paramListTypeCateg		= "";
	/** ���� �ý����ڵ� */
	private String filterSyscode 			= "";
	/** ���� ��뿩�� */
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
