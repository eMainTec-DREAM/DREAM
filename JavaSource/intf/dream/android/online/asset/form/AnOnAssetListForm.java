package intf.dream.android.online.asset.form;

import common.struts.BaseForm;
import intf.dream.android.online.asset.dto.AnOnAssetCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnAssetListForm"
 */
public class AnOnAssetListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnAssetCommonDTO anOnAssetCommonDTO = new AnOnAssetCommonDTO();

	public AnOnAssetCommonDTO getAnOnAssetCommonDTO() {
		return anOnAssetCommonDTO;
	}

	public void setAnOnAssetCommonDTO(AnOnAssetCommonDTO anOnAssetCommonDTO) {
		this.anOnAssetCommonDTO = anOnAssetCommonDTO;
	}

    
}
