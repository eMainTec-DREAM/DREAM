package intf.dream.bee.finder.eqasmb.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.eqasmb.dto.BeeFinderEqAsmbCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderEqAsmbListForm"
 */
public class BeeFinderEqAsmbListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderEqAsmbCommonDTO beeFinderEqAsmbCommonDTO = new BeeFinderEqAsmbCommonDTO();

	public BeeFinderEqAsmbCommonDTO getBeeFinderEqAsmbCommonDTO() {
		return beeFinderEqAsmbCommonDTO;
	}

	public void setBeeFinderEqAsmbCommonDTO(BeeFinderEqAsmbCommonDTO beeFinderEqAsmbCommonDTO) {
		this.beeFinderEqAsmbCommonDTO = beeFinderEqAsmbCommonDTO;
	}

    
}
