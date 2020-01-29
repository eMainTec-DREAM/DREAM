package intf.dream.bee.log.form;

import common.struts.BaseForm;
import intf.dream.bee.log.dto.BeeLogCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeLogForm"
 */
public class BeeLogForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeLogCommonDTO beeLogCommonDTO = new BeeLogCommonDTO();

	public BeeLogCommonDTO getBeeLogCommonDTO() {
		return beeLogCommonDTO;
	}

	public void setBeeLogCommonDTO(BeeLogCommonDTO beeLogCommonDTO) {
		this.beeLogCommonDTO = beeLogCommonDTO;
	}

    
}
