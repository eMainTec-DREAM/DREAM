package dream.asset.categ.list.dto;

import common.bean.BaseDTO;

/**
 * �������� ���� DTO
 * @author  kim21017
 * @version $Id: MaEqCatalogCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqCatalogCommonDTO extends BaseDTO
{
	/** ��������ID */
	private String eqCtgId				= "";
	/** ���񱸺�ID */
	private String eqTypeId				= "";
	/** ���񱸺и� */
	private String eqTypeDesc			= "";
	/** ������������ID */
	private String filterPeqCtgId		= "";
	/** �������������� */
	private String filterPeqCtgDesc		= "";
	/** ������ */
	private String filterEqCtgDesc		= "";
	/** ������ detail�� �θ� ����id */
	private String detailPctgId			= "";
	/** ������ detail�� �θ� ������  */
	private String detailPctgDesc		= "";
	
	public String getDetailPctgId() {
		return detailPctgId;
	}
	public void setDetailPctgId(String detailPctgId) {
		this.detailPctgId = detailPctgId;
	}
	public String getDetailPctgDesc() {
		return detailPctgDesc;
	}
	public void setDetailPctgDesc(String detailPctgDesc) {
		this.detailPctgDesc = detailPctgDesc;
	}
	public String getEqTypeDesc() {
		return eqTypeDesc;
	}
	public void setEqTypeDesc(String eqTypeDesc) {
		this.eqTypeDesc = eqTypeDesc;
	}
	public String getEqTypeId() {
		return eqTypeId;
	}
	public void setEqTypeId(String eqTypeId) {
		this.eqTypeId = eqTypeId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
		super.setAuditKey(eqCtgId);
	}
	public String getFilterPeqCtgId() {
		return filterPeqCtgId;
	}
	public void setFilterPeqCtgId(String filterPeqCtgId) {
		this.filterPeqCtgId = filterPeqCtgId;
	}
	public String getFilterPeqCtgDesc() {
		return filterPeqCtgDesc;
	}
	public void setFilterPeqCtgDesc(String filterPeqCtgDesc) {
		this.filterPeqCtgDesc = filterPeqCtgDesc;
	}
}
