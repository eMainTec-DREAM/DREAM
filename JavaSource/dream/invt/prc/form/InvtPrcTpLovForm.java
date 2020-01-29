package dream.invt.prc.form;

import dream.comm.form.MaFinderAcForm;
import dream.invt.prc.dto.InvtPrcTpLovDTO;

/**
 * 구매절차 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="invtPrcTpLovForm"
 */
public class InvtPrcTpLovForm extends MaFinderAcForm
{
    /** Task Map DTO */
    private InvtPrcTpLovDTO invtPrcTpLovDTO = new InvtPrcTpLovDTO();

    public InvtPrcTpLovDTO getInvtPrcTpLovDTO()
    {
        return invtPrcTpLovDTO;
    }

    public void setInvtPrcTpLovDTO(InvtPrcTpLovDTO invtPrcTpLovDTO)
    {
        this.invtPrcTpLovDTO = invtPrcTpLovDTO;
    }

}
