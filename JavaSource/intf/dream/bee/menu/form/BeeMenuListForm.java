package intf.dream.bee.menu.form;

import common.struts.BaseForm;
import intf.dream.bee.menu.dto.BeeMenuCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeMenuListForm"
 */
public class BeeMenuListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeMenuCommonDTO beeMenuCommonDTO = new BeeMenuCommonDTO();

	public BeeMenuCommonDTO getBeeMenuCommonDTO() {
		return beeMenuCommonDTO;
	}

	public void setBeeMenuCommonDTO(BeeMenuCommonDTO beeMenuCommonDTO) {
		this.beeMenuCommonDTO = beeMenuCommonDTO;
	}

    
}
