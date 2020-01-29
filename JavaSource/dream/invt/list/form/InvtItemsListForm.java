package dream.invt.list.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="invtItemsListForm"
 */
public class InvtItemsListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
}
