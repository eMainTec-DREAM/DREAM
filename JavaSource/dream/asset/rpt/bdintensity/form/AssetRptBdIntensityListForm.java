package dream.asset.rpt.bdintensity.form;

import common.struts.BaseForm;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityDetailListDTO;


/**
 * ���� ���尭���� ��� - List Form
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts.form name="assetRptBdIntensityListForm"
 * */
public class AssetRptBdIntensityListForm extends BaseForm {
    
    private AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO = new AssetRptBdIntensityCommonDTO();
    
    private AssetRptBdIntensityDetailListDTO assetRptBdIntensityDetailListDTO = new AssetRptBdIntensityDetailListDTO();

    public AssetRptBdIntensityDetailListDTO getAssetRptBdIntensityDetailListDTO() {
		return assetRptBdIntensityDetailListDTO;
	}

	public void setAssetRptBdIntensityDetailListDTO(AssetRptBdIntensityDetailListDTO assetRptBdIntensityDetailListDTO) {
		this.assetRptBdIntensityDetailListDTO = assetRptBdIntensityDetailListDTO;
	}

	public AssetRptBdIntensityCommonDTO getAssetRptBdIntensityCommonDTO() {
        return assetRptBdIntensityCommonDTO;
    }

    public void setAssetRptBdIntensityCommonDTO(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO) {
        this.assetRptBdIntensityCommonDTO = assetRptBdIntensityCommonDTO;
    }

}