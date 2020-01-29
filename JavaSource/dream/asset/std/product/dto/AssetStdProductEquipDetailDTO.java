package dream.asset.std.product.dto;

import common.bean.BaseDTO;

/**
 * 생산설비 - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class AssetStdProductEquipDetailDTO extends BaseDTO
{
	/** 생산품목 ID */
	private String productId				= "";
	/** 생산설비 ID*/
	private String assetStdProductEquipId 	= "";
	
	/** 진행순서 */
	private String prodSeq					= "";
	/** 설비 ID*/
	private String equipId				 	= "";
	/** 설비 NO*/
	private String equipNo				 	= "";
	/** 설비 Desc */
	private String equipDesc 				= "";
	/** 설비위치 ID*/
	private String eqLocId				 	= "";
	/** 설비위치 Desc */
	private String eqLocDesc 				= "";
	/** 이전생산 설비 ID*/
	private String pprdEquipId				= "";
	/** 이전생산 설비 Desc */
	private String pprdEquipDesc 			= "";
	/** 비고 */
	private String remark		 			= "";
	
	public String getProductId() {
		return productId;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAssetStdProductEquipId() {
		return assetStdProductEquipId;
	}
	public void setAssetStdProductEquipId(String assetStdProductEquipId) {
		this.assetStdProductEquipId = assetStdProductEquipId;
		super.setAuditKey(assetStdProductEquipId);
	}
	public String getProdSeq() {
		return prodSeq;
	}
	public void setProdSeq(String prodSeq) {
		this.prodSeq = prodSeq;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getPprdEquipId() {
		return pprdEquipId;
	}
	public void setPprdEquipId(String pprdEquipId) {
		this.pprdEquipId = pprdEquipId;
	}
	public String getPprdEquipDesc() {
		return pprdEquipDesc;
	}
	public void setPprdEquipDesc(String pprdEquipDesc) {
		this.pprdEquipDesc = pprdEquipDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
