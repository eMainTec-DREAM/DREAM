package intf.dream.bee.finder.eqloc.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.eqloc.dto.BeeFinderEqlocCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderEqlocListForm"
 */
public class BeeFinderEqlocListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderEqlocCommonDTO beeFinderEqlocCommonDTO = new BeeFinderEqlocCommonDTO();

	public BeeFinderEqlocCommonDTO getBeeFinderEqlocCommonDTO() {
		return beeFinderEqlocCommonDTO;
	}

	public void setBeeFinderEqlocCommonDTO(BeeFinderEqlocCommonDTO beeFinderEqlocCommonDTO) {
		this.beeFinderEqlocCommonDTO = beeFinderEqlocCommonDTO;
	}

    
}
