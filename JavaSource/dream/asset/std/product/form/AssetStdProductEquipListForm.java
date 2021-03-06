package dream.asset.std.product.form;

import common.struts.BaseForm;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductEquipDetailDTO;
import dream.asset.std.product.dto.AssetStdProductEquipListDTO;
/**
 * ���꼳�� - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="assetStdProductEquipListForm"
 * */

public class AssetStdProductEquipListForm extends BaseForm{
	
	private AssetStdProductCommonDTO assetStdProductCommonDTO = new AssetStdProductCommonDTO();
	private AssetStdProductEquipListDTO assetStdProductEquipListDTO = new AssetStdProductEquipListDTO();
	private AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO = new AssetStdProductEquipDetailDTO();
	
	public AssetStdProductEquipDetailDTO getAssetStdProductEquipDetailDTO() {
		return assetStdProductEquipDetailDTO;
	}
	public void setAssetStdProductEquipDetailDTO(AssetStdProductEquipDetailDTO assetStdProductEquipDetailDTO) {
		this.assetStdProductEquipDetailDTO = assetStdProductEquipDetailDTO;
	}
	public AssetStdProductCommonDTO getAssetStdProductCommonDTO() {
		return assetStdProductCommonDTO;
	}
	public void setAssetStdProductCommonDTO(AssetStdProductCommonDTO assetStdProductCommonDTO) {
		this.assetStdProductCommonDTO = assetStdProductCommonDTO;
	}
	public AssetStdProductEquipListDTO getAssetStdProductEquipListDTO() {
		return assetStdProductEquipListDTO;
	}
	public void setAssetStdProductEquipListDTO(AssetStdProductEquipListDTO assetStdProductEquipListDTO) {
		this.assetStdProductEquipListDTO = assetStdProductEquipListDTO;
	}
	
}
