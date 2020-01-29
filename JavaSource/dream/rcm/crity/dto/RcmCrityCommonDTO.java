package dream.rcm.crity.dto;

import common.bean.BaseDTO;
/**
 * Criticality Matrix Page - ���� DTO
 * @author kim21017
 * @version $Id: RcmCrityCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class RcmCrityCommonDTO extends BaseDTO
{
	/**Criticality ����Ʈ ID*/
	private String crityListId 				= "";
	/**Filter Critical Matrix �� */
	private String filterCrityListDesc		= "";
	
	private String filterColName	= "";
	
	private String filterRowName	= "";
	
	
	public String getFilterColName() {
		return filterColName;
	}
	public void setFilterColName(String filterColName) {
		this.filterColName = filterColName;
	}
	public String getFilterRowName() {
		return filterRowName;
	}
	public void setFilterRowName(String filterRowName) {
		this.filterRowName = filterRowName;
	}
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
		super.setAuditKey(crityListId);
	}
	public String getFilterCrityListDesc() {
		return filterCrityListDesc;
	}
	public void setFilterCrityListDesc(String filterCrityListDesc) {
		this.filterCrityListDesc = filterCrityListDesc;
	}
	
}
