package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.AssetListITCommonDTO;
import dream.asset.list.dto.AssetListITSWDetailDTO;
import dream.asset.list.dto.AssetListITSWListDTO;

/**
 * Detail Form
 * @author youngjoo38
 * @version $Id: AssetListITSWDetailForm.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="assetListITSWDetailForm"
 */
public class AssetListITSWDetailForm extends BaseForm
{
	private AssetListITSWListDTO assetListITSWListDTO = new AssetListITSWListDTO();
	private AssetListITSWDetailDTO assetListITSWDetailDTO = new AssetListITSWDetailDTO();
	
	public AssetListITSWListDTO getAssetListITSWListDTO() {
		return assetListITSWListDTO;
	}
	public void setAssetListITSWListDTO(AssetListITSWListDTO assetListITSWListDTO) {
		this.assetListITSWListDTO = assetListITSWListDTO;
	}
	public AssetListITSWDetailDTO getAssetListITSWDetailDTO() {
		return assetListITSWDetailDTO;
	}
	public void setAssetListITSWDetailDTO(AssetListITSWDetailDTO assetListITSWDetailDTO) {
		this.assetListITSWDetailDTO = assetListITSWDetailDTO;
	}
}
