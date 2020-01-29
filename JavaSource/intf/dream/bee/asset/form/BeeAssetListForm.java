package intf.dream.bee.asset.form;

import common.struts.BaseForm;
import intf.dream.bee.asset.dto.BeeAssetCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeAssetListForm"
 */
public class BeeAssetListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeAssetCommonDTO beeAssetCommonDTO = new BeeAssetCommonDTO();

	public BeeAssetCommonDTO getBeeAssetCommonDTO() {
		return beeAssetCommonDTO;
	}

	public void setBeeAssetCommonDTO(BeeAssetCommonDTO beeAssetCommonDTO) {
		this.beeAssetCommonDTO = beeAssetCommonDTO;
	}

    
}
