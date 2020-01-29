package dream.asset.rpt.nyearpo.form;

import common.struts.BaseForm;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPOCommonDTO;
import dream.asset.rpt.nyearpo.dto.AssetRptNYearPODetailDTO;

/**
 * N Year Spare Part
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptNYearPOListForm"
 */
public class AssetRptNYearPOListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO = new AssetRptNYearPOCommonDTO();
    
    private AssetRptNYearPODetailDTO assetRptNYearPODetailDTO = new AssetRptNYearPODetailDTO();
    
    public AssetRptNYearPOCommonDTO getAssetRptNYearPOCommonDTO()
    {
        return assetRptNYearPOCommonDTO;
    }

    public void setAssetRptNYearPOCommonDTO(
            AssetRptNYearPOCommonDTO assetRptNYearPOCommonDTO)
    {
        this.assetRptNYearPOCommonDTO = assetRptNYearPOCommonDTO;
    }
    
    public AssetRptNYearPODetailDTO getAssetRptNYearPODetailDTO()
    {
        return assetRptNYearPODetailDTO;
    }

    public void setAssetRptNYearPODetailDTO(
            AssetRptNYearPODetailDTO assetRptNYearPODetailDTO)
    {
        this.assetRptNYearPODetailDTO = assetRptNYearPODetailDTO;
    }
	
}
