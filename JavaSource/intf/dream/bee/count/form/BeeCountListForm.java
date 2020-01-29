package intf.dream.bee.count.form;

import common.struts.BaseForm;
import intf.dream.bee.count.dto.BeeCountCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeCountListForm"
 */
public class BeeCountListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeCountCommonDTO beeCountCommonDTO = new BeeCountCommonDTO();

	public BeeCountCommonDTO getBeeCountCommonDTO() {
		return beeCountCommonDTO;
	}

	public void setBeeCountCommonDTO(BeeCountCommonDTO beeCountCommonDTO) {
		this.beeCountCommonDTO = beeCountCommonDTO;
	}

    
}
