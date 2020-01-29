package dream.invt.rpt.moninvtamt.form;

import common.struts.BaseForm;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;

/**
 * 월별투자집행금액
 * @author  cjscjs9
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="invtRptMonInvtAmtDetailForm"
 */
public class InvtRptMonInvtAmtDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO = new InvtRptMonInvtAmtCommonDTO();
    
    private InvtRptMonInvtAmtDetailDTO invtRptMonInvtAmtDetailDTO = new InvtRptMonInvtAmtDetailDTO();
    
    public InvtRptMonInvtAmtCommonDTO getInvtRptMonInvtAmtCommonDTO()
    {
        return invtRptMonInvtAmtCommonDTO;
    }

    public void setInvtRptMonInvtAmtCommonDTO(
            InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO)
    {
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
