package intf.dream.bee.cal.form;

import common.struts.BaseForm;
import intf.dream.bee.cal.dto.BeeCalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeCalListForm"
 */
public class BeeCalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeCalCommonDTO beeCalCommonDTO = new BeeCalCommonDTO();

	public BeeCalCommonDTO getBeeCalCommonDTO() {
		return beeCalCommonDTO;
	}

	public void setBeeCalCommonDTO(BeeCalCommonDTO beeCalCommonDTO) {
		this.beeCalCommonDTO = beeCalCommonDTO;
	}

    
}
