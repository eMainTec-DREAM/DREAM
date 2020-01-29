package dream.asset.rpt.lcc.loc.form;

import common.struts.BaseForm;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;

/**
 * ����TOP(��ġ)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptLccLocListForm"
 */
public class AssetRptLccLocListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
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
