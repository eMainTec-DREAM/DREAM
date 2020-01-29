package dream.asset.std.asset.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;

/**
 * ȸ���ڻ�- �� Form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdAssetDetailForm"
 */
public class AssetStdAssetDetailForm extends BaseForm
{
    //========================================================================
    /** ȸ���ڻ� ���� */ 
    private AssetStdAssetCommonDTO assetStdAssetCommonDTO = new AssetStdAssetCommonDTO();
    //========================================================================
    /** ȸ���ڻ� �� */
    private AssetStdAssetDetailDTO assetStdAssetDetailDTO = new AssetStdAssetDetailDTO();
    
    /** ��뿩�� Options */
    private Collection isUseOptions = null;

	public AssetStdAssetCommonDTO getAssetStdAssetCommonDTO() {
		return assetStdAssetCommonDTO;
	}

	public void setAssetStdAssetCommonDTO(AssetStdAssetCommonDTO assetStdAssetCommonDTO) {
		this.assetStdAssetCommonDTO = assetStdAssetCommonDTO;
	}

	public AssetStdAssetDetailDTO getAssetStdAssetDetailDTO() {
		return assetStdAssetDetailDTO;
	}

	public void setAssetStdAssetDetailDTO(AssetStdAssetDetailDTO assetStdAssetDetailDTO) {
		this.assetStdAssetDetailDTO = assetStdAssetDetailDTO;
	}

	public Collection getIsUseOptions() {
		return isUseOptions;
	}

	public void setIsUseOptions(Collection isUseOptions) {
		this.isUseOptions = isUseOptions;
	}
}
