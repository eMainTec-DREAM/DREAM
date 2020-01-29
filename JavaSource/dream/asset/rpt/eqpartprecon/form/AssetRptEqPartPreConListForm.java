package dream.asset.rpt.eqpartprecon.form;

import common.struts.BaseForm;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConDetailDTO;


/**
 * AssetRptEqPartPreCon Page - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="assetRptEqPartPreConListForm"
 * */
public class AssetRptEqPartPreConListForm extends BaseForm {
    
    private AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO = new AssetRptEqPartPreConCommonDTO();
    private AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO = new AssetRptEqPartPreConDetailDTO();

    public AssetRptEqPartPreConCommonDTO getAssetRptEqPartPreConCommonDTO() {
        return assetRptEqPartPreConCommonDTO;
    }

    public void setAssetRptEqPartPreConCommonDTO(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO) {
        this.assetRptEqPartPreConCommonDTO = assetRptEqPartPreConCommonDTO;
    }

    public AssetRptEqPartPreConDetailDTO getAssetRptEqPartPreConDetailDTO()
    {
        return assetRptEqPartPreConDetailDTO;
    }

    public void setAssetRptEqPartPreConDetailDTO(
            AssetRptEqPartPreConDetailDTO assetRptEqPartPreConDetailDTO)
    {
        this.assetRptEqPartPreConDetailDTO = assetRptEqPartPreConDetailDTO;
    }
    
}