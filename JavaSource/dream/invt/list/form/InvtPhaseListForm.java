package dream.invt.list.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;



/**
 * 
 * @author  kim2107
 * @version $Id: InvtPhaseDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="invtPhaseListForm"
 */
public class InvtPhaseListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    //===============================================================
    private InvtDetailDTO invtDetailDTO = new InvtDetailDTO();
    
	public InvtDetailDTO getInvtDetailDTO()
    {
        return invtDetailDTO;
    }
    public void setInvtDetailDTO(InvtDetailDTO invtDetailDTO)
    {
        this.invtDetailDTO = invtDetailDTO;
    }
    public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
}
