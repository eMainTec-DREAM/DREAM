package intf.dream.bee.common.form;

import common.struts.BaseForm;
import intf.dream.bee.common.dto.BeeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeCommonListForm"
 */
public class BeeCommonListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeCommonDTO beeCommonDTO = new BeeCommonDTO();

	public BeeCommonDTO getBeeCommonDTO() {
		return beeCommonDTO;
	}

	public void setBeeCommonDTO(BeeCommonDTO beeCommonDTO) {
		this.beeCommonDTO = beeCommonDTO;
	}
}
