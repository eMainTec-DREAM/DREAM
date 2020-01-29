package intf.dream.cricket.approval.form;

import common.struts.BaseForm;
import intf.dream.cricket.approval.dto.CricketApprovalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketApprovalListForm"
 */
public class CricketApprovalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketApprovalCommonDTO cricketApprovalCommonDTO = new CricketApprovalCommonDTO();

	public CricketApprovalCommonDTO getCricketApprovalCommonDTO() {
		return cricketApprovalCommonDTO;
	}

	public void setCricketApprovalCommonDTO(CricketApprovalCommonDTO cricketApprovalCommonDTO) {
		this.cricketApprovalCommonDTO = cricketApprovalCommonDTO;
	}

    
}
