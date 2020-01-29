package dream.asset.std.asset.form;

import common.struts.BaseForm;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;

/**
 * 회계자산 - 목록 form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdAssetListForm"
 */
public class AssetStdAssetListForm extends BaseForm
{    
    //===============================================================
    /** 회계자산 공통 */
    private AssetStdAssetCommonDTO assetStdAssetCommonDTO = new AssetStdAssetCommonDTO();;
    
	public AssetStdAssetCommonDTO getAssetStdAssetCommonDTO() 
	{
		return assetStdAssetCommonDTO;
	}

	public void setAssetStdAssetCommonDTO(AssetStdAssetCommonDTO assetStdAssetCommonDTO) 
	{
		this.assetStdAssetCommonDTO = assetStdAssetCommonDTO;
	}
	
}
