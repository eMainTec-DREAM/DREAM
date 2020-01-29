package intf.dream.bee.user.form;

import common.struts.BaseForm;
import intf.dream.bee.user.dto.BeeUserCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeUserListForm"
 */
public class BeeUserListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeUserCommonDTO beeUserCommonDTO = new BeeUserCommonDTO();

	public BeeUserCommonDTO getBeeUserCommonDTO() {
		return beeUserCommonDTO;
	}

	public void setBeeUserCommonDTO(BeeUserCommonDTO beeUserCommonDTO) {
		this.beeUserCommonDTO = beeUserCommonDTO;
	}

    
}
