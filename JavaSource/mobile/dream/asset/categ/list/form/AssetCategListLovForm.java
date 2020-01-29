package mobile.dream.asset.categ.list.form;

import common.struts.BaseForm;
import mobile.dream.asset.categ.list.dto.AssetCategListLovDTO;


/**
 * ���������˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="assetCategListLovForm"
 */
public class AssetCategListLovForm extends BaseForm
{
    /** ���������˾� DTO */
    private AssetCategListLovDTO assetCategListLovDTO = new AssetCategListLovDTO();

	public AssetCategListLovDTO getAssetCategListLovDTO() 
	{
		return assetCategListLovDTO;
	}

	public void setAssetCategListLovDTO(AssetCategListLovDTO assetCategListLovDTO) 
	{
		this.assetCategListLovDTO = assetCategListLovDTO;
	}
}
