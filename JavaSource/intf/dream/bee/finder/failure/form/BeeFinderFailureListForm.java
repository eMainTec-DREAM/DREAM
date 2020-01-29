package intf.dream.bee.finder.failure.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.failure.dto.BeeFinderFailureCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderFailureListForm"
 */
public class BeeFinderFailureListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderFailureCommonDTO beeFinderFailureCommonDTO = new BeeFinderFailureCommonDTO();

	public BeeFinderFailureCommonDTO getBeeFinderFailureCommonDTO() {
		return beeFinderFailureCommonDTO;
	}

	public void setBeeFinderFailureCommonDTO(BeeFinderFailureCommonDTO beeFinderFailureCommonDTO) {
		this.beeFinderFailureCommonDTO = beeFinderFailureCommonDTO;
	}

    
}
