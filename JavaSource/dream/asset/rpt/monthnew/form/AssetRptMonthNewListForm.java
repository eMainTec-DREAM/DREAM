package dream.asset.rpt.monthnew.form;

import common.struts.BaseForm;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;

/**
 * �űԼ�������Ȳ 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="assetRptMonthNewListForm"
 */
public class AssetRptMonthNewListForm extends BaseForm
{    
    private AssetRptMonthNewListDTO assetRptMonthNewListDTO = new AssetRptMonthNewListDTO();

	public AssetRptMonthNewListDTO getAssetRptMonthNewListDTO() {
		return assetRptMonthNewListDTO;
	}

	public void setAssetRptMonthNewListDTO(AssetRptMonthNewListDTO assetRptMonthNewListDTO) {
		this.assetRptMonthNewListDTO = assetRptMonthNewListDTO;
	}

}
