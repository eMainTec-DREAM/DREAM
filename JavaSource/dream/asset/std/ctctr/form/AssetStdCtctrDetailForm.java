package dream.asset.std.ctctr.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;

/**
 * CostCenter- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="assetStdCtctrDetailForm"
 */
public class AssetStdCtctrDetailForm extends BaseForm
{
    //========================================================================
    /** CostCenter 공통 */ 
    private AssetStdCtctrCommonDTO assetStdCtctrCommonDTO = new AssetStdCtctrCommonDTO();
    //========================================================================
    /** CostCenter 상세 */
    private AssetStdCtctrDetailDTO assetStdCtctrDetailDTO = new AssetStdCtctrDetailDTO();
    
    /** 사용여부 Options */
    private Collection isUseOptions = null;

    public AssetStdCtctrCommonDTO getAssetStdCtctrCommonDTO()
    {
        return assetStdCtctrCommonDTO;
    }

    public void setAssetStdCtctrCommonDTO(
            AssetStdCtctrCommonDTO assetStdCtctrCommonDTO)
    {
        this.assetStdCtctrCommonDTO = assetStdCtctrCommonDTO;
    }

    public AssetStdCtctrDetailDTO getAssetStdCtctrDetailDTO()
    {
        return assetStdCtctrDetailDTO;
    }

    public void setAssetStdCtctrDetailDTO(
            AssetStdCtctrDetailDTO assetStdCtctrDetailDTO)
    {
        this.assetStdCtctrDetailDTO = assetStdCtctrDetailDTO;
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
