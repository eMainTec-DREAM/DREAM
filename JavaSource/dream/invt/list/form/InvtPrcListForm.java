package dream.invt.list.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 목록 form
 * @author  kim21017
 * @version $Id: InvtListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcListForm"
 */
public class InvtPrcListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}

	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
}
