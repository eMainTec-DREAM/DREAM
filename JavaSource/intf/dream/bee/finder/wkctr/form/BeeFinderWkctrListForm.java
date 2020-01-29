package intf.dream.bee.finder.wkctr.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.wkctr.dto.BeeFinderWkctrCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts.form name="beeFinderWkctrListForm"
 */
public class BeeFinderWkctrListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderWkctrCommonDTO beeFinderWkctrCommonDTO = new BeeFinderWkctrCommonDTO();

	public BeeFinderWkctrCommonDTO getBeeFinderWkctrCommonDTO() {
		return beeFinderWkctrCommonDTO;
	}

	public void setBeeFinderWkctrCommonDTO(BeeFinderWkctrCommonDTO beeFinderWkctrCommonDTO) {
		this.beeFinderWkctrCommonDTO = beeFinderWkctrCommonDTO;
	}

    
}
