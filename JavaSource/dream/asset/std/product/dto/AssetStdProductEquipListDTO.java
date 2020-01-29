package dream.asset.std.product.dto;

import common.bean.BaseDTO;
/**
 * ���꼳�� - ���� DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class AssetStdProductEquipListDTO extends BaseDTO
{
	/**   ����ǰ�� ID */
	private String productId				= "";
	/**   ���꼳�� ID*/
	private String assetStdProductEquipId 	= "";
	
	/**   ���� ID*/
	private String equipId 					= "";
	/**   ���� DESC*/
	private String equipDesc			 	= "";
	
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
	public String getProductId() {
		return productId;
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
}
