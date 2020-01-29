package intf.dream.bee.finder.syscode.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.syscode.dto.BeeFinderSyscodeCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderSyscodeListForm"
 */
public class BeeFinderSyscodeListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderSyscodeCommonDTO beeFinderSyscodeCommonDTO = new BeeFinderSyscodeCommonDTO();

	public BeeFinderSyscodeCommonDTO getBeeFinderSyscodeCommonDTO() {
		return beeFinderSyscodeCommonDTO;
	}

	public void setBeeFinderSyscodeCommonDTO(BeeFinderSyscodeCommonDTO beeFinderSyscodeCommonDTO) {
		this.beeFinderSyscodeCommonDTO = beeFinderSyscodeCommonDTO;
	}

    
}
