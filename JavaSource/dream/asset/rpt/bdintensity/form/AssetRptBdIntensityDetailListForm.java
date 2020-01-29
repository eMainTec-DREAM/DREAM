package dream.asset.rpt.bdintensity.form;

import common.struts.BaseForm;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityDetailListDTO;

/**
 * 에너지사용량(일별) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptBdIntensityDetailListForm"
 */
public class AssetRptBdIntensityDetailListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private AssetRptBdIntensityCommonDTO wssetRptBdIntensityCommonDTO = new AssetRptBdIntensityCommonDTO();
    
    private AssetRptBdIntensityDetailListDTO assetRptBdIntensityDetailListDTO = new AssetRptBdIntensityDetailListDTO();
    
    public AssetRptBdIntensityCommonDTO getAssetRptBdIntensityCommonDTO()
    {
        return wssetRptBdIntensityCommonDTO;
    }

    public void setAssetRptBdIntensityCommonDTO(
            AssetRptBdIntensityCommonDTO wssetRptBdIntensityCommonDTO)
    {
        this.wssetRptBdIntensityCommonDTO = wssetRptBdIntensityCommonDTO;
    }
    
    public AssetRptBdIntensityDetailListDTO getAssetRptBdIntensityDetailListDTO()
    {
        return assetRptBdIntensityDetailListDTO;
    }

    public void setAssetRptBdIntensityDetailListDTO(
            AssetRptBdIntensityDetailListDTO assetRptBdIntensityDetailListDTO)
    {
        this.assetRptBdIntensityDetailListDTO = assetRptBdIntensityDetailListDTO;
    }
	
}
