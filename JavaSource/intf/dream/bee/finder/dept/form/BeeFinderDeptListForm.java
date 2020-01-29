package intf.dream.bee.finder.dept.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.dept.dto.BeeFinderDeptCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderDeptListForm"
 */
public class BeeFinderDeptListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderDeptCommonDTO beeFinderDeptCommonDTO = new BeeFinderDeptCommonDTO();

	public BeeFinderDeptCommonDTO getBeeFinderDeptCommonDTO() {
		return beeFinderDeptCommonDTO;
	}

	public void setBeeFinderDeptCommonDTO(BeeFinderDeptCommonDTO beeFinderDeptCommonDTO) {
		this.beeFinderDeptCommonDTO = beeFinderDeptCommonDTO;
	}

    
}
