package intf.dream.bee.finder.emp.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.emp.dto.BeeFinderEmpCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderEmpListForm"
 */
public class BeeFinderEmpListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderEmpCommonDTO beeFinderEmpCommonDTO = new BeeFinderEmpCommonDTO();

	public BeeFinderEmpCommonDTO getBeeFinderEmpCommonDTO() {
		return beeFinderEmpCommonDTO;
	}

	public void setBeeFinderEmpCommonDTO(BeeFinderEmpCommonDTO beeFinderEmpCommonDTO) {
		this.beeFinderEmpCommonDTO = beeFinderEmpCommonDTO;
	}

    
}
