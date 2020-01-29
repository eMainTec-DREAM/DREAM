package dream.asset.loc.list.dto;

import common.bean.BaseDTO;

/**
 * ������ġ ����
 * @author  kim21017
 * @version $Id: MaEqLocCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaEqLocCommonDTO extends BaseDTO
{
	/** ��ġ�з�ID */
	private String eqLocId 					= "";
	/** ���� ��ġ */
	private String filterEqLocNo 			= "";
	/** ���� ��ġ�� */
	private String filterEqLocDesc 			= "";
	/** ���� ��ġ�з������ڵ� */
	private String filterLevelType 			= "";
	/** ���� ��ġ�з����и� */
	private String filterLevelTypeDesc 		= "";
	/** ���� ������ġ�з�ID */
	private String filterPEqLocId 			= "";
	/** ���� ������ġ�з��� */
	private String filterPEqLocDesc 		= "";
	/** detail ���� ��ġ�з�ID */
	private String detailPEqLocId 			= "";
	/** detail ���� ��ġ�з��� */
	private String detailPEqLocDesc 		= "";
	
	public String getDetailPEqLocId() {
		return detailPEqLocId;
	}
	public void setDetailPEqLocId(String detailPEqLocId) {
		this.detailPEqLocId = detailPEqLocId;
	}
	public String getDetailPEqLocDesc() {
		return detailPEqLocDesc;
	}
	public void setDetailPEqLocDesc(String detailPEqLocDesc) {
		this.detailPEqLocDesc = detailPEqLocDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
		super.setAuditKey(eqLocId);
	}
	public String getFilterEqLocNo() {
		return filterEqLocNo;
	}
	public void setFilterEqLocNo(String filterEqLocNo) {
		this.filterEqLocNo = filterEqLocNo;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	
	public String getFilterLevelType() {
		return filterLevelType;
	}
	public void setFilterLevelType(String filterLevelType) {
		this.filterLevelType = filterLevelType;
	}
	public String getFilterLevelTypeDesc() {
		return filterLevelTypeDesc;
	}
	public void setFilterLevelTypeDesc(String filterLevelTypeDesc) {
		this.filterLevelTypeDesc = filterLevelTypeDesc;
	}
	public String getFilterPEqLocId() {
		return filterPEqLocId;
	}
	public void setFilterPEqLocId(String filterPEqLocId) {
		this.filterPEqLocId = filterPEqLocId;
	}
	public String getFilterPEqLocDesc() {
		return filterPEqLocDesc;
	}
	public void setFilterPEqLocDesc(String filterPEqLocDesc) {
		this.filterPEqLocDesc = filterPEqLocDesc;
	}
}
