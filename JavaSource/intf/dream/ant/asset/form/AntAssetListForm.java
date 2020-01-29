package intf.dream.ant.asset.form;

import common.struts.BaseForm;
import intf.dream.ant.asset.dto.AntAssetCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="antAssetListForm"
 */
public class AntAssetListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AntAssetCommonDTO antAssetCommonDTO = new AntAssetCommonDTO();

	public AntAssetCommonDTO getAntAssetCommonDTO() {
		return antAssetCommonDTO;
	}

	public void setAntAssetCommonDTO(AntAssetCommonDTO antAssetCommonDTO) {
		this.antAssetCommonDTO = antAssetCommonDTO;
	}

    
}
