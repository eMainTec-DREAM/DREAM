package dream.asset.rpt.unit.form;

import common.struts.BaseForm;
import dream.asset.rpt.unit.dto.AssetRptEqUnitsDTO;

/**
 * Form
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="assetRptEqUnitsForm"
 */
public class AssetRptEqUnitsForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private AssetRptEqUnitsDTO assetRptEqUnitsDTO = new AssetRptEqUnitsDTO();
    
	public AssetRptEqUnitsDTO getAssetRptEqUnitsDTO() {
		return assetRptEqUnitsDTO;
	}

	public void setAssetRptEqUnitsDTO(AssetRptEqUnitsDTO assetRptEqUnitsDTO) {
		this.assetRptEqUnitsDTO = assetRptEqUnitsDTO;
	}
	
}
