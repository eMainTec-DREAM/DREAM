package dream.invt.prc.form;

import common.struts.BaseForm;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * �������� - ��� form
 * @author  kim21017
 * @version $Id: InvtPrcTpListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcTpListForm"
 */
public class InvtPrcTpListForm extends BaseForm
{    
    //===============================================================
    /** �������� ���� */
    private InvtPrcTpCommonDTO invtPrcTpCommonDTO = new InvtPrcTpCommonDTO();
    
	public InvtPrcTpCommonDTO getInvtPrcTpCommonDTO() {
		return invtPrcTpCommonDTO;
	}

	public void setInvtPrcTpCommonDTO(InvtPrcTpCommonDTO invtPrcTpCommonDTO) {
		this.invtPrcTpCommonDTO = invtPrcTpCommonDTO;
	}
}
