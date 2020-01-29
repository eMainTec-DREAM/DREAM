package dream.asset.rpt.lcc.loc.form;

import common.struts.BaseForm;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;

/**
 * 고장TOP(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptLccLocDetailForm"
 */
public class AssetRptLccLocDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private AssetRptLccLocCommonDTO assetRptLccLocCommonDTO = new AssetRptLccLocCommonDTO();
    
    private AssetRptLccLocDetailDTO assetRptLccLocDetailDTO = new AssetRptLccLocDetailDTO();
    
    public AssetRptLccLocCommonDTO getAssetRptLccLocCommonDTO()
    {
        return assetRptLccLocCommonDTO;
    }

    public void setAssetRptLccLocCommonDTO(
            AssetRptLccLocCommonDTO assetRptLccLocCommonDTO)
    {
        this.assetRptLccLocCommonDTO = assetRptLccLocCommonDTO;
    }
    
    public AssetRptLccLocDetailDTO getAssetRptLccLocDetailDTO()
    {
        return assetRptLccLocDetailDTO;
    }

    public void setAssetRptLccLocDetailDTO(
            AssetRptLccLocDetailDTO assetRptLccLocDetailDTO)
    {
        this.assetRptLccLocDetailDTO = assetRptLccLocDetailDTO;
    }
	
}
