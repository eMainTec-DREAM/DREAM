package intf.dream.bee.finder.ctctr.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.ctctr.dto.BeeFinderCtctrCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="beeFinderCtctrListForm"
 */
public class BeeFinderCtctrListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderCtctrCommonDTO beeFinderCtctrCommonDTO = new BeeFinderCtctrCommonDTO();

	public BeeFinderCtctrCommonDTO getBeeFinderCtctrCommonDTO() {
		return beeFinderCtctrCommonDTO;
	}

	public void setBeeFinderCtctrCommonDTO(BeeFinderCtctrCommonDTO beeFinderCtctrCommonDTO) {
		this.beeFinderCtctrCommonDTO = beeFinderCtctrCommonDTO;
	}

    
}
