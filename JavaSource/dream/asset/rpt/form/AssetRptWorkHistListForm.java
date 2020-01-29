package dream.asset.rpt.form;

import common.struts.BaseForm;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
/**
 * 설비이력(과거) - List Form
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 * @struts.form name="assetRptWorkHistListForm"
 * */

public class AssetRptWorkHistListForm extends BaseForm{
	
	private AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO = new AssetRptWorkHistCommonDTO();

	public AssetRptWorkHistCommonDTO getAssetRptWorkHistCommonDTO() {
		return assetRptWorkHistCommonDTO;
	}

	public void setAssetRptWorkHistCommonDTO(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO) {
		this.assetRptWorkHistCommonDTO = assetRptWorkHistCommonDTO;
	}
	
}
