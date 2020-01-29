package intf.dream.android.online.approval.form;

import common.struts.BaseForm;
import intf.dream.android.online.approval.dto.AnOnApprovalCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnApprovalListForm"
 */
public class AnOnApprovalListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnApprovalCommonDTO anOnApprovalCommonDTO = new AnOnApprovalCommonDTO();

	public AnOnApprovalCommonDTO getAnOnApprovalCommonDTO() {
		return anOnApprovalCommonDTO;
	}

	public void setAnOnApprovalCommonDTO(AnOnApprovalCommonDTO anOnApprovalCommonDTO) {
		this.anOnApprovalCommonDTO = anOnApprovalCommonDTO;
	}

    
}
