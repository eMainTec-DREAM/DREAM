package intf.dream.bee.finder.docctg.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.docctg.dto.BeeFinderDocCtgCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderDocCtgListForm"
 */
public class BeeFinderDocCtgListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderDocCtgCommonDTO beeFinderDocCtgCommonDTO = new BeeFinderDocCtgCommonDTO();

	public BeeFinderDocCtgCommonDTO getBeeFinderDocCtgCommonDTO() {
		return beeFinderDocCtgCommonDTO;
	}

	public void setBeeFinderDocCtgCommonDTO(BeeFinderDocCtgCommonDTO beeFinderDocCtgCommonDTO) {
		this.beeFinderDocCtgCommonDTO = beeFinderDocCtgCommonDTO;
	}

    
}
