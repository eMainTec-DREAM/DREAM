package dream.asset.std.product.form;

import common.struts.BaseForm;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;

/**
 * ����ǰ�� - ��� form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdProductListForm"
 */
public class AssetStdProductListForm extends BaseForm
{    
    //===============================================================
    /** ����ǰ�� ���� */
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
