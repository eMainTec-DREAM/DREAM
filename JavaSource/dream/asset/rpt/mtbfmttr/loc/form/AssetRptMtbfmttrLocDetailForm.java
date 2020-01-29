package dream.asset.rpt.mtbfmttr.loc.form;

import common.struts.BaseForm;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;

/**
 * MTBF,MTTR(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetRptMtbfmttrLocDetailForm"
 */
public class AssetRptMtbfmttrLocDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO = new AssetRptMtbfmttrLocCommonDTO();
    
    private AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO = new AssetRptMtbfmttrLocDetailDTO();
    
    public AssetRptMtbfmttrLocCommonDTO getAssetRptMtbfmttrLocCommonDTO()
    {
        return assetRptMtbfmttrLocCommonDTO;
    }

    public void setAssetRptMtbfmttrLocCommonDTO(
            AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO)
    {
        this.assetRptMtbfmttrLocCommonDTO = assetRptMtbfmttrLocCommonDTO;
    }
    
    public AssetRptMtbfmttrLocDetailDTO getAssetRptMtbfmttrLocDetailDTO()
    {
        return assetRptMtbfmttrLocDetailDTO;
    }

    public void setAssetRptMtbfmttrLocDetailDTO(
            AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO)
    {
        this.assetRptMtbfmttrLocDetailDTO = assetRptMtbfmttrLocDetailDTO;
    }
	
}
