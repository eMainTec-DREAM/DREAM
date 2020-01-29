package intf.dream.bee.finder.eqctg.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.eqctg.dto.BeeFinderEqctgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderEqctgListForm"
 */
public class BeeFinderEqctgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderEqctgCommonDTO beeFinderEqctgCommonDTO = new BeeFinderEqctgCommonDTO();

	public BeeFinderEqctgCommonDTO getBeeFinderEqctgCommonDTO() {
		return beeFinderEqctgCommonDTO;
	}

	public void setBeeFinderEqctgCommonDTO(BeeFinderEqctgCommonDTO beeFinderEqctgCommonDTO) {
		this.beeFinderEqctgCommonDTO = beeFinderEqctgCommonDTO;
	}

    
}
