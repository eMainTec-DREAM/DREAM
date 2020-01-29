package intf.dream.bee.approval.form;

import common.struts.BaseForm;
import intf.dream.bee.approval.dto.BeeApprovalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeApprovalListForm"
 */
public class BeeApprovalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeApprovalCommonDTO beeApprovalCommonDTO = new BeeApprovalCommonDTO();

	public BeeApprovalCommonDTO getBeeApprovalCommonDTO() {
		return beeApprovalCommonDTO;
	}

	public void setBeeApprovalCommonDTO(BeeApprovalCommonDTO beeApprovalCommonDTO) {
		this.beeApprovalCommonDTO = beeApprovalCommonDTO;
	}

    
}
