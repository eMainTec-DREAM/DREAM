package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���� �������� �� dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrMoldProdDetailDTO extends BaseDTO
{
	/**  id */
	private String eqMoldProductId			= "";
	/** ǰ�� */
	private String productNo			= "";
	/** ǰ�� */
	private String productDesc			= "";
	/** ���� */
	private String ordNo			= "";
	/** ��� */
	private String remark		= "";
	/** �����ȣ */
	private String equipId			= "";
	
	
	public String getEqMoldProductId() {
		return eqMoldProductId;
	}
	public void setEqMoldProductId(String eqMoldProductId) {
		this.eqMoldProductId = eqMoldProductId;
		super.setAuditKey(eqMoldProductId);
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	
}