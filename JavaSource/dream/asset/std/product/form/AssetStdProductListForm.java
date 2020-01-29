package dream.asset.std.product.form;

import common.struts.BaseForm;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;

/**
 * 积魂前格 - 格废 form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdProductListForm"
 */
public class AssetStdProductListForm extends BaseForm
{    
    //===============================================================
    /** 积魂前格 傍烹 */
    private AssetStdProductCommonDTO assetStdProductCommonDTO = new AssetStdProductCommonDTO();

    public AssetStdProductCommonDTO getAssetStdProductCommonDTO()
    {
        return assetStdProductCommonDTO;
    }

    public void setAssetStdProductCommonDTO(
            AssetStdProductCommonDTO assetStdProductCommonDTO)
    {
        this.assetStdProductCommonDTO = assetStdProductCommonDTO;
    };
    
}
