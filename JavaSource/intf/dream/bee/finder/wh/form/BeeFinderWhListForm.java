package intf.dream.bee.finder.wh.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.wh.dto.BeeFinderWhCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="beeFinderWhListForm"
 */
public class BeeFinderWhListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderWhCommonDTO beeFinderWhCommonDTO = new BeeFinderWhCommonDTO();

	public BeeFinderWhCommonDTO getBeeFinderWhCommonDTO() {
		return beeFinderWhCommonDTO;
	}

	public void setBeeFinderWhCommonDTO(BeeFinderWhCommonDTO beeFinderWhCommonDTO) {
		this.beeFinderWhCommonDTO = beeFinderWhCommonDTO;
	}

    
}
