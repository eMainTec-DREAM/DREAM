package intf.dream.bee.initcode.form;

import common.struts.BaseForm;
import intf.dream.bee.initcode.dto.BeeInitcodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeInitcodeListForm"
 */
public class BeeInitcodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeInitcodeCommonDTO beeInitcodeCommonDTO = new BeeInitcodeCommonDTO();

	public BeeInitcodeCommonDTO getBeeInitcodeCommonDTO() {
		return beeInitcodeCommonDTO;
	}

	public void setBeeInitcodeCommonDTO(BeeInitcodeCommonDTO beeInitcodeCommonDTO) {
		this.beeInitcodeCommonDTO = beeInitcodeCommonDTO;
	}

    
}
