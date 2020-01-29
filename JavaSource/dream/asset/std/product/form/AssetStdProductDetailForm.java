package dream.asset.std.product.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;

/**
 * 积魂前格- 惑技 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdProductDetailForm"
 */
public class AssetStdProductDetailForm extends BaseForm
{
    //========================================================================
    /** 积魂前格 傍烹 */ 
    private AssetStdProductCommonDTO assetStdProductCommonDTO = new AssetStdProductCommonDTO();
    //========================================================================
    /** 积魂前格 惑技 */
    private AssetStdProductDetailDTO assetStdProductDetailDTO = new AssetStdProductDetailDTO();
    
    /** 荤侩咯何 Options */
    private Collection isUseOptions = null;

    public AssetStdProductCommonDTO getAssetStdProductCommonDTO()
    {
        return assetStdProductCommonDTO;
    }

    public void setAssetStdProductCommonDTO(
            AssetStdProductCommonDTO assetStdProductCommonDTO)
    {
        this.assetStdProductCommonDTO = assetStdProductCommonDTO;
    }

    public AssetStdProductDetailDTO getAssetStdProductDetailDTO()
    {
        return assetStdProductDetailDTO;
    }

    public void setAssetStdProductDetailDTO(
            AssetStdProductDetailDTO assetStdProductDetailDTO)
    {
        this.assetStdProductDetailDTO = assetStdProductDetailDTO;
    }

    public Collection getIsUseOptions()
    {
        return isUseOptions;
    }

    public void setIsUseOptions(Collection isUseOptions)
    {
        this.isUseOptions = isUseOptions;
    }
	
}
