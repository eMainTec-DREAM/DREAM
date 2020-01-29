package mobile.dream.asset.loc.list.form;

import common.struts.BaseForm;
import mobile.dream.asset.loc.list.dto.AssetLocListLovDTO;

/**
 * 위치분류팝업 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetLocListLovForm"
 */
public class AssetLocListLovForm extends BaseForm
{
    /** 위치분류팝업 DTO */
    private AssetLocListLovDTO assetLocListLovDTO = new AssetLocListLovDTO();

	public AssetLocListLovDTO getAssetLocListLovDTO() 
	{
		return assetLocListLovDTO;
	}

	public void setAssetLocListLovDTO(AssetLocListLovDTO assetLocListLovDTO) 
	{
		this.assetLocListLovDTO = assetLocListLovDTO;
	}
}
