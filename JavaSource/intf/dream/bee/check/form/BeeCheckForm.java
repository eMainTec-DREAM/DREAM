package intf.dream.bee.check.form;

import common.struts.BaseForm;
import intf.dream.bee.check.dto.BeeCheckCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeCheckForm"
 */
public class BeeCheckForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeCheckCommonDTO beeCheckCommonDTO = new BeeCheckCommonDTO();

	public BeeCheckCommonDTO getBeeCheckCommonDTO() {
		return beeCheckCommonDTO;
	}

	public void setBeeCheckCommonDTO(BeeCheckCommonDTO beeCheckCommonDTO) {
		this.beeCheckCommonDTO = beeCheckCommonDTO;
	}

    
}
