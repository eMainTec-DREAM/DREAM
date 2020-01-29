package intf.dream.bee.iss.form;

import common.struts.BaseForm;
import intf.dream.bee.iss.dto.BeeIssCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeIssListForm"
 */
public class BeeIssListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeIssCommonDTO beeIssCommonDTO = new BeeIssCommonDTO();

	public BeeIssCommonDTO getBeeIssCommonDTO() {
		return beeIssCommonDTO;
	}

	public void setBeeIssCommonDTO(BeeIssCommonDTO beeIssCommonDTO) {
		this.beeIssCommonDTO = beeIssCommonDTO;
	}

    
}
