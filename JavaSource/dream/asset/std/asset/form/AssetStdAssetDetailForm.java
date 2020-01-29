package dream.asset.std.asset.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;

/**
 * 회계자산- 상세 Form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdAssetDetailForm"
 */
public class AssetStdAssetDetailForm extends BaseForm
{
    //========================================================================
    /** 회계자산 공통 */ 
    private AssetStdAssetCommonDTO assetStdAssetCommonDTO = new AssetStdAssetCommonDTO();
    //========================================================================
    /** 회계자산 상세 */
    private AssetStdAssetDetailDTO assetStdAssetDetailDTO = new AssetStdAssetDetailDTO();
    
    /** 사용여부 Options */
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
