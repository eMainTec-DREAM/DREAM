package dream.asset.rpt.eqParts.form;

import common.struts.BaseForm;
import dream.asset.rpt.eqParts.dto.AssetRptEqPartsDTO;


/**
 * ���񱸼���ǰ - Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="assetRptEqPartsForm"
 * */
public class AssetRptEqPartsForm extends BaseForm {
    
    private AssetRptEqPartsDTO assetRptEqPartsDTO = new AssetRptEqPartsDTO();

    public AssetRptEqPartsDTO getAssetRptEqPartsDTO() {
        return assetRptEqPartsDTO;
    }

    public void setAssetRptEqPartsDTO(AssetRptEqPartsDTO assetRptEqPartsDTO) {
        this.assetRptEqPartsDTO = assetRptEqPartsDTO;
    }

}