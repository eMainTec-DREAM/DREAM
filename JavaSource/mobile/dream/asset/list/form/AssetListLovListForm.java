package mobile.dream.asset.list.form;

import common.struts.BaseForm;
import mobile.dream.asset.list.dto.AssetListLovListDTO;

/**
 * ���� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetListLovListForm"
 */
public class AssetListLovListForm extends BaseForm
{
    /** ���� DTO */
    private AssetListLovListDTO assetListLovListDTO = new AssetListLovListDTO();

	public AssetListLovListDTO getAssetListLovListDTO() 
	{
		return assetListLovListDTO;
	}

	public void setAssetListLovListDTO(AssetListLovListDTO assetListLovListDTO) 
	{
		this.assetListLovListDTO = assetListLovListDTO;
	}
}
