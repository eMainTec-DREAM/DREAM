package intf.dream.bee.auth.form;

import common.struts.BaseForm;
import intf.dream.bee.auth.dto.BeeAuthCommonDTO;

/**
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeAuthListForm"
 */
public class BeeAuthListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeAuthCommonDTO beeAuthCommonDTO = new BeeAuthCommonDTO();

	public BeeAuthCommonDTO getBeeAuthCommonDTO() {
		return beeAuthCommonDTO;
	}

	public void setBeeAuthCommonDTO(BeeAuthCommonDTO beeAuthCommonDTO) {
		this.beeAuthCommonDTO = beeAuthCommonDTO;
	}

    
}
