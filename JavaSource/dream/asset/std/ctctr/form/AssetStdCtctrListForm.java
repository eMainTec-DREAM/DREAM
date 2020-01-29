package dream.asset.std.ctctr.form;

import common.struts.BaseForm;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;

/**
 * CostCenter - 목록 form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdCtctrListForm"
 */
public class AssetStdCtctrListForm extends BaseForm
{    
    //===============================================================
    /** CostCenter 공통 */
    private AssetStdCtctrCommonDTO assetStdCtctrCommonDTO = new AssetStdCtctrCommonDTO();

    public AssetStdCtctrCommonDTO getAssetStdCtctrCommonDTO()
    {
        return assetStdCtctrCommonDTO;
    }

    public void setAssetStdCtctrCommonDTO(
            AssetStdCtctrCommonDTO assetStdCtctrCommonDTO)
    {
        this.assetStdCtctrCommonDTO = assetStdCtctrCommonDTO;
    };
    
}
