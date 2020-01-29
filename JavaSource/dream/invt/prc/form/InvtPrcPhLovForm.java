package dream.invt.prc.form;

import dream.comm.form.MaFinderAcForm;
import dream.invt.prc.dto.InvtPrcPhLovDTO;

/**
 * 구매절차 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="invtPrcPhLovForm"
 */
public class InvtPrcPhLovForm extends MaFinderAcForm
{
    /** Task Map DTO */
    private InvtPrcPhLovDTO invtPrcPhLovDTO = new InvtPrcPhLovDTO();

    public InvtPrcPhLovDTO getInvtPrcPhLovDTO()
    {
        return invtPrcPhLovDTO;
    }

    public void setInvtPrcPhLovDTO(InvtPrcPhLovDTO invtPrcPhLovDTO)
    {
        this.invtPrcPhLovDTO = invtPrcPhLovDTO;
    }

}
