package dream.invt.rpt.moninvtamt.form;

import common.struts.BaseForm;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;


/**
 * InvtRptMonInvtAmt Page - List Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="invtRptMonInvtAmtListForm"
 * */
public class InvtRptMonInvtAmtListForm extends BaseForm {
    
    private InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO = new InvtRptMonInvtAmtCommonDTO();
    private InvtRptMonInvtAmtDetailDTO invtRptMonInvtAmtDetailDTO = new InvtRptMonInvtAmtDetailDTO();

    public InvtRptMonInvtAmtCommonDTO getInvtRptMonInvtAmtCommonDTO() {
        return invtRptMonInvtAmtCommonDTO;
    }

    public void setInvtRptMonInvtAmtCommonDTO(InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO) {
        this.invtRptMonInvtAmtCommonDTO = invtRptMonInvtAmtCommonDTO;
    }

    public InvtRptMonInvtAmtDetailDTO getInvtRptMonInvtAmtDetailDTO()
    {
        return invtRptMonInvtAmtDetailDTO;
    }

    public void setInvtRptMonInvtAmtDetailDTO(
            InvtRptMonInvtAmtDetailDTO invtRptMonInvtAmtDetailDTO)
    {
        this.invtRptMonInvtAmtDetailDTO = invtRptMonInvtAmtDetailDTO;
    }
    
}