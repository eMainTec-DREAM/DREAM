package intf.dream.bee.finder.parts.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.parts.dto.BeeFinderPartsCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderPartsListForm"
 */
public class BeeFinderPartsListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderPartsCommonDTO beeFinderPartsCommonDTO = new BeeFinderPartsCommonDTO();

	public BeeFinderPartsCommonDTO getBeeFinderPartsCommonDTO() {
		return beeFinderPartsCommonDTO;
	}

	public void setBeeFinderPartsCommonDTO(BeeFinderPartsCommonDTO beeFinderPartsCommonDTO) {
		this.beeFinderPartsCommonDTO = beeFinderPartsCommonDTO;
	}

    
}
