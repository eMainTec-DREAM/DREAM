package dream.asset.std.ctctr.form;

import common.struts.BaseForm;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;

/**
 * CostCenter - ��� form
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdCtctrListForm"
 */
public class AssetStdCtctrListForm extends BaseForm
{    
    //===============================================================
    /** CostCenter ���� */
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
