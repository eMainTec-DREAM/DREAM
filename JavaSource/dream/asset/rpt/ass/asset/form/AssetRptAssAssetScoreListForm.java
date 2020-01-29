package dream.asset.rpt.ass.asset.form;

import common.struts.BaseForm;
import dream.asset.rpt.ass.asset.dto.AssetRptAssAssetScoreCommonDTO;

/**
 * AssetRptAssAssetScore Page - List Form
 * @author nhkim8548
 * @version $Id: AssetRptAssAssetScoreListForm.java,v 1.0 2018/08/23 15:20:40 nhkim8548 Exp $
 * @since 1.0
 * @struts.form name="assetRptAssAssetScoreListForm"
 * */
public class AssetRptAssAssetScoreListForm extends BaseForm {
    
    private AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO = new AssetRptAssAssetScoreCommonDTO();
    
    public AssetRptAssAssetScoreCommonDTO getAssetRptAssAssetScoreCommonDTO()
    {
        return assetRptAssAssetScoreCommonDTO;
    }
    public void setAssetRptAssAssetScoreCommonDTO(AssetRptAssAssetScoreCommonDTO assetRptAssAssetScoreCommonDTO)
    {
        this.assetRptAssAssetScoreCommonDTO = assetRptAssAssetScoreCommonDTO;
    }
}